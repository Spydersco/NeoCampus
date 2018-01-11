package reseau;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import messages.Ticket;
import utilisateurs.Utilisateur;


public class Client {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		try {
			SocketClient socket = new SocketClient();
			PrintWriter out = new PrintWriter(socket.getSoc().getOutputStream());
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getSoc().getInputStream()));

			boolean logged = false;
			while (!logged) {
				System.out.println(in.readLine());
				String login = sc.nextLine();
				out.println(login);
				out.flush();
	
				System.out.println(in.readLine());
	
				String pass = sc.nextLine();
				out.println(pass);
				out.flush();

				if (in.readLine().equals("true")) {
					logged = true;
				}
				else {
					logged = false;
					System.out.println("Identifiant ou mdp incorrect");
				}
			}

			ObjectInput oi = new ObjectInputStream(socket.getSoc().getInputStream());
			Utilisateur ut = (Utilisateur) oi.readObject();
			System.out.println(ut);
			
			socket.envoiMessage("totoro", new Ticket(), null);
			
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sc.close();
	}
}
