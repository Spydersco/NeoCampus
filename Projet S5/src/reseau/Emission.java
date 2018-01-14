package reseau;

import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.util.concurrent.Semaphore;

import baseDeDonnees.UtilisateurDAO;

public class Emission implements Runnable{

	private Socket socket;
	private int idClient;
	private Connection connect;
	private ObjectOutput oo = null;
	private Semaphore sem;

	/**
	 * @param socket
	 * @param idClient
	 */
	public Emission(Socket socket, int idClient, Connection connect, Semaphore sem) {
		super();
		this.socket = socket;
		this.idClient = idClient;
		this.connect = connect;
		this.sem = sem;
	}

	public void run() {
			envoyerDonnees(); // Donnnee à la connexion
			while(true) {
				try {
					sem.acquire();
					envoyerDonnees();
					sem.release();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} 
			}
	}

	public void envoyerDonnees() {
		try {
			oo = new ObjectOutputStream(socket.getOutputStream());
			UtilisateurDAO ut = new UtilisateurDAO(connect);
			oo.writeObject(ut.find(idClient));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
