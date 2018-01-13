package reseau;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;

import baseDeDonnees.UtilisateurDAO;
import messages.Ticket;

public class Login implements Runnable{

	private Socket socket = null;
	private Connection connect;

	public Login(Socket s, Connection connect) {
		this.socket = s;
		this.connect = connect;
	}

	public void run() {
		
		try {
//			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
//			PrintWriter out = new PrintWriter(socket.getOutputStream());
//			
//			out.println("Entrez votre login :");
//			out.flush();
//			in.skip(4);
//			int login = Integer.parseInt(in.readLine());
//
//			out.println("Entrez votre mdp :");
//			out.flush();
//			String mdp = in.readLine();
//
//			System.out.println(mdp);
//			out.println(verifierIdentifiants(login, mdp));
//			out.flush();
	
//			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
//			oos.flush();
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			int[] s = (int[]) ois.readObject();
			ois.close();
		} 
		catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public boolean verifierIdentifiants(int id, String mdp) {
		UtilisateurDAO ut = new UtilisateurDAO(connect);
		return ut != null && ut.find(id).getMotDePasse().equals(mdp);
	}

}
