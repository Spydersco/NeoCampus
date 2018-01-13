package reseau;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;

import baseDeDonnees.UtilisateurDAO;
import messages.Message;

public class Login implements Runnable{

	private Socket socket = null;
	private Connection connect;
	private boolean logged;
	private int idClient;

	public Login(Socket s, Connection connect) {
		this.socket = s;
		this.connect = connect;
		this.logged = false;
	}

	public void run() {
		
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			PrintWriter out = new PrintWriter(socket.getOutputStream());
			
			while (!logged) {
				out.println("Entrez votre login :");
				out.flush();
				this.idClient = Integer.parseInt(in.readLine());
	
				out.println("Entrez votre mdp :");
				out.flush();
				String mdp = in.readLine();
				System.out.println(mdp);
				logged = verifierIdentifiants(mdp);
				out.println(logged);
				out.flush();
			}
			envoyerDonnees();
			ObjectInput oi = new ObjectInputStream(socket.getInputStream());
			Message message = (Message) oi.readObject();
			message.afficherMessage();
		} 
		catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public boolean verifierIdentifiants(String mdp) {
		UtilisateurDAO ut = new UtilisateurDAO(connect);
		String chaine = ut.find(idClient).getMotDePasse(); 
		if(chaine == null)
			return false;
		return chaine.equals(mdp);
	}

	public void envoyerDonnees() {
		try {
			ObjectOutput oo = new ObjectOutputStream(socket.getOutputStream());
			UtilisateurDAO ut = new UtilisateurDAO(connect);
			oo.writeObject(ut.find(idClient));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
