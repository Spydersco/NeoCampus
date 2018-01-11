package reseau;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import messages.Message;
import messages.StatutMessage;
import messages.Ticket;
import utilisateurs.Groupe;
import utilisateurs.Utilisateur;

public class SocketClient {

	private Socket soc;
	private ObjectOutputStream oos = null;

	public SocketClient () {
		try {
			this.soc = new Socket("localHost", 2018);
			this.oos = new ObjectOutputStream(soc.getOutputStream());
		} catch (UnknownHostException e) {
			System.err.println("Connexion impossible à l'adresse " + soc.getLocalAddress());
		} catch (IOException e) {
			System.err.println("Aucun serveur à l'écoute du port "+ soc.getLocalPort());
		}
	}

	/**
	 * @return the soc
	 */
	public Socket getSoc() {
		return soc;
	}

	public void demandeCreationTicket(Groupe grp, String titre, String premierMessage, Utilisateur auteur) {
		Ticket leTicket = new Ticket(0, titre, auteur);
		leTicket.creerMessage(premierMessage, auteur);
		try {
			oos.writeObject(leTicket);
			oos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void envoiMessage(String corps, Ticket ticket, Utilisateur auteur) {
		Message leMessage = new Message(0, corps, "", StatutMessage.PAS_RECU_SERVEUR, auteur);
		try {
			oos.writeObject("salut a tous les amis");
			oos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
