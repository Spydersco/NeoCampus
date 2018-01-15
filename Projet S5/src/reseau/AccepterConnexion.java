package reseau;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.util.concurrent.Semaphore;

public class AccepterConnexion implements Runnable{

	private ServerSocket socketserver = null;
	private Socket socket = null;
	public Thread t1;
	public Connection connect;

	public AccepterConnexion(ServerSocket ss, Connection connect){
		
		this.connect = connect;
		this.socketserver = ss;
    }

	public void run() {
		Semaphore mutex = new Semaphore(1);
		Semaphore mutex_read = new Semaphore(1);
		Semaphore sem = new Semaphore(0);
		int nbrClient = 0;
		try {
			while (true) {
				socket = socketserver.accept();
				System.out.println("Un client veut se connecter  ");
				t1 = new Thread(new Login(socket, connect, nbrClient, mutex, mutex_read, sem));
				t1.start();
			}
		} 
		catch (IOException e) {
			System.err.println("Erreur serveur");
		}
	}

}
