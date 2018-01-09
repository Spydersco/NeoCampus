package reseau;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;

public class AccepterConnexion {

	private ServerSocket socketserver = null;
	private Socket socket = null;
	public Thread t1;
	public Connection connect;

	public AccepterConnexion(ServerSocket ss, Connection connect){
		
		this.connect = connect;
		this.socketserver = ss;
    }

	public void run() {

		try {
			while (true) {
				socket = socketserver.accept();
				System.out.println("Un client veut se connecter  ");
				t1 = new Thread(new Login(socket, connect));
				t1.start();
			}
		} 
		catch (IOException e) {
			System.err.println("Erreur serveur");
		}
	}

}
