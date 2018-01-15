package reseau;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.sql.Connection;
import java.util.ListIterator;
import java.util.concurrent.Semaphore;

import baseDeDonnees.GestionBaseDeDonnee;
import messages.Message;
import messages.StatutMessage;
import messages.Ticket;

public class Reception implements Runnable {

	private Socket socket;
	private int idClient;
	private int nbClient;
	private ObjectInput oi = null;
	private Semaphore sem;
	private Semaphore read;
	private GestionBaseDeDonnee gestionBD;

	/**
	 * @param socket
	 * @param idClient
	 * @param connect
	 * @param sem
	 */
	public Reception(Socket socket, int idClient, int nbClient, Connection connect, Semaphore read, Semaphore sem) {
		super();
		this.socket = socket;
		this.idClient = idClient;
		this.nbClient = nbClient;
		this.sem = sem;
		this.read = read;
		this.gestionBD = new GestionBaseDeDonnee(connect);
	}

	public void run() {

		try {
			while (true) {
				try {
					oi = new ObjectInputStream(socket.getInputStream());
					Object objet = oi.readObject();
					read.acquire();
					if(objet.getClass() == Message.class) {
						receptionMessage(objet);
					}
					else if(objet.getClass() == Ticket.class){
						receptionTicket(objet);
					}
					else {
						autre(objet);
					}
					read.release();
					sem.release(nbClient);
				}
				catch (EOFException e) {
						break;
				} 
			}
		}
		catch (IOException | InterruptedException | ClassNotFoundException e){
			e.printStackTrace();
		}
		
	}

	private void autre(Object objet) {
		int id = (int) objet;
		Message msg = gestionBD.getMessageDAO().find(id);
		Ticket tck = gestionBD.getTicketDAO().find(msg.getIdTicket());
		boolean trouve = false;
		// recherche message
		for (ListIterator<Message> it = tck.getMessages().listIterator(); it.hasNext();) {
			Message curr = it.next();
			if (curr.getStatut() == StatutMessage.PAS_TOUS_LU) {
				// recherche lecteur
				for (ListIterator<Integer> it2 = curr.getLecteurs().listIterator(); it.hasNext() && !trouve;) {
					if (it2.next().intValue() == idClient) {
						trouve = true;
					}
				}
				if (!trouve) {
					curr.getLecteurs().add(idClient);
					if (curr.getLecteurs().size() == gestionBD.getGroupeDAO().find(tck.getIdGroupe()).getNbMembres()) {
						curr.setStatut(StatutMessage.TOUS_LU);
					}
				}
			}
		}
	}

	/**
	 * @param objet
	 */
	private void receptionTicket(Object objet) {
		Ticket ticket = (Ticket) objet;
		ticket.setId(gestionBD.idTicketSuivant());
		ticket.getMessages().get(0).setId(gestionBD.idMessageSuivant());
		gestionBD.getTicketDAO().create(ticket);
		gestionBD.getMessageDAO().create(ticket.getMessages().get(0));
		System.out.println(ticket.getMessages());
	}

	/**
	 * @param objet
	 */
	private void receptionMessage(Object objet) {
		Message message = (Message) objet;
		message.afficherMessage();
		message.setId(gestionBD.idMessageSuivant());
		message.setStatut(StatutMessage.PAS_TOUS_LU);
		gestionBD.getMessageDAO().create(message);
	}

}
