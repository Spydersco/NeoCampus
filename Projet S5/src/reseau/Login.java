package reseau;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.util.concurrent.Semaphore;

import baseDeDonnees.UtilisateurDAO;

public class Login implements Runnable {

	private Socket socket = null;
	private Connection connect;
	private boolean logged;
	private int idClient;
	private Semaphore mutex;
	private Semaphore sem;
	private int nbrClient;

	public Login(Socket s, Connection connect, int nbrClient, Semaphore mutex, Semaphore sem) {
		this.socket = s;
		this.connect = connect;
		this.logged = false;
		this.nbrClient = nbrClient;
		this.mutex = mutex;
		this.sem = sem;
	}

	public void run() {

		BufferedReader in;
		try {
			PrintWriter out = new PrintWriter(socket.getOutputStream());
			in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));

			while (!logged) {
				out.println("Entrez votre login :");
				out.flush();
				this.idClient = Integer.parseInt(in.readLine());

				out.println("Entrez votre mdp :");
				out.flush();
				String mdp = in.readLine();
				System.out.println(mdp);
				logged = verifierIdentifiants(mdp);
				System.out.println(logged);
				out.println(logged);
				out.flush();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		incrementerClient();

		new Thread(new Emission(socket, idClient, connect, sem)).start();
		new Thread(new Reception(socket, idClient, nbrClient, connect, sem)).start();
	}

	private void incrementerClient() {
		try {
			mutex.acquire();
			nbrClient++;
			mutex.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean verifierIdentifiants(String mdp) {
		UtilisateurDAO ut = new UtilisateurDAO(connect);
		String chaine = ut.find(idClient).getMotDePasse();
		if (chaine == null)
			return false;
		return chaine.equals(mdp);
	}

}
