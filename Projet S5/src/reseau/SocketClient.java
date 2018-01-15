package reseau;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.DateFormat;
import messages.Message;
import messages.StatutMessage;
import messages.Ticket;
import utilisateurs.Groupe;
import utilisateurs.Utilisateur;

public class SocketClient {

	private Socket soc;
	private ObjectOutputStream oos = null;
	private ObjectInputStream ois = null;

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

	/**
	 * @return the oos
	 */
	public ObjectOutputStream getOos() {
		return oos;
	}

	/**
	 * @return the ois
	 */
	public ObjectInputStream getOis() {
		return ois;
	}

	public boolean seConnecter(String id, String mdp) {
		boolean rep = false;
		try {
			PrintWriter out = new PrintWriter(soc.getOutputStream());
			BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));

			out.println(id);
			out.flush();
			out.println(mdp);
			out.flush();

			rep = in.readLine().equals("true");

		} catch (IOException e) {
			e.printStackTrace();
		}
		return rep;
	}

	public Utilisateur getDonnees() {
		Utilisateur ut = null;
		try {
			ois = new ObjectInputStream(soc.getInputStream());
			ut = (Utilisateur) ois.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(ut.getTickets());
		return ut;
	}

	public void seDeconnecter() {
		try {
			soc.close();
		} catch (IOException e) {
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
			e.printStackTrace();
		}
	}

	public void envoiMessage(String corps, Ticket ticket, Utilisateur auteur) {
		Message leMessage = new Message(ticket.getMessages().size(), corps,
				DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).toString(), auteur.getId(),
				StatutMessage.PAS_RECU_SERVEUR, ticket.getId());
		try {
			oos = new ObjectOutputStream(soc.getOutputStream());
			oos.writeObject(leMessage);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void signifierLecture() {
		// TODO
	}
}
