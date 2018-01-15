package reseau;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.util.Scanner;
import messages.Message;
import messages.StatutMessage;
import messages.Ticket;
import utilisateurs.Groupe;
import utilisateurs.Utilisateur;

public class SocketClient {

	private Socket soc;
	private ObjectOutputStream oos = null;

	public SocketClient() {
		try {
			this.soc = new Socket("localHost", 2018);
		} catch (UnknownHostException e) {
			System.err.println("Connexion impossible à l'adresse " + soc.getLocalAddress());
		} catch (IOException e) {
			System.err.println("Aucun serveur à l'écoute du port " + soc.getLocalPort());
		}
	}

	/**
	 * @return the soc
	 */
	public Socket getSoc() {
		return soc;
	}

	public Utilisateur seConnecter() {
		Utilisateur ut = null;
		Scanner sc = new Scanner(System.in);
		try {
			PrintWriter out = new PrintWriter(soc.getOutputStream());
			BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));

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
				} else {
					logged = false;
					System.out.println("Identifiant ou mdp incorrect");
				}
			}

			ObjectInput oi = new ObjectInputStream(soc.getInputStream());
			ut = (Utilisateur) oi.readObject();
			System.out.println(ut.getTickets());

		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sc.close();
		demandeCreationTicket("blabla", "Heeee, Salut à tous !", ut, ut.getGroupes().get(0));
		// envoiMessage("C'est David Lafarge Pokemon !", ut.getTickets().get(0), ut);
		return ut;
	}

	public void seDeconnecter() {
		try {
			soc.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void demandeCreationTicket(String titre, String premierMessage, Utilisateur auteur, Groupe groupe) {
		Ticket leTicket = new Ticket(auteur.getTickets().size(), titre, auteur.getId(), groupe.getId());
		leTicket.creerMessage(premierMessage, auteur);
		auteur.addTicket(leTicket);
		try {
			oos = new ObjectOutputStream(soc.getOutputStream());
			oos.writeObject(leTicket);
			oos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void envoiMessage(String corps, Ticket ticket, Utilisateur auteur) {
		Message leMessage = new Message(ticket.getMessages().size(), corps,
				DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).toString(), auteur,
				StatutMessage.PAS_RECU_SERVEUR, ticket.getId());
		try {
			oos = new ObjectOutputStream(soc.getOutputStream());
			oos.writeObject(leMessage);
			oos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
