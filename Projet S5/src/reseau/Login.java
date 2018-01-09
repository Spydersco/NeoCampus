package reseau;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;

import baseDeDonnees.UtilisateurDAO;

public class Login implements Runnable{

	private Socket socket = null;
	private PrintWriter out = null;
	private BufferedReader in = null;
	private Connection connect;

	public Login(Socket s, Connection connect) {
		this.socket = s;
		this.connect = connect;
	}

	public void run() {
		
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream());
			
			out.println("Entrez votre login :");
			out.flush();
			int login = in.read();

			out.println("Entrez votre mdp :");
			out.flush();
			String mdp = in.readLine();

			out.println(verifierIdentifiants(login, mdp));
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public boolean verifierIdentifiants(int id, String mdp) {
		UtilisateurDAO ut = new UtilisateurDAO(connect);
		return true;
		//return ut.find(id).getMotDePasse() == mdp;
	}

}
