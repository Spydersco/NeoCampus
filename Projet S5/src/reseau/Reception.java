package reseau;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.sql.Connection;
import java.util.concurrent.Semaphore;

import baseDeDonnees.GestionBaseDeDonnee;
import messages.Message;
import messages.StatutMessage;
import messages.Ticket;

public class Reception implements Runnable{

	private Socket socket;
	private int idClient;
	private int nbClient;
	private ObjectInput oi = null;
	private Semaphore sem;
	private GestionBaseDeDonnee gestionBD;

	/**
	 * @param socket
	 * @param idClient
	 * @param connect
	 * @param sem
	 */
	public Reception(Socket socket, int idClient, int nbClient, Connection connect, Semaphore sem) {
		super();
		this.socket = socket;
		this.idClient = idClient;
		this.nbClient = nbClient;
		this.sem = sem;
		this.gestionBD = new GestionBaseDeDonnee(connect);
	}

	public void run() {

		while (true) {
			try {
				oi = new ObjectInputStream(socket.getInputStream());
				Object objet = oi.readObject();
				if(objet.getClass() == Message.class) {
					receptionMessage(objet);
				}
				else {
					receptionTicket(objet);
				}
				sem.release(nbClient);
			} catch (EOFException e) {
				break;
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
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
