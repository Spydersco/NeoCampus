package reseau;

import java.io.IOException;
import java.net.ServerSocket;
import java.sql.Connection;

public class Serveur {

	public static void main(String[] zero) {

		ServerSocket socket;
		Connection connect;

		try {
			socket = new ServerSocket(9999);
			Thread t = new Thread(new AccepterConnexion(socket, connect));
			t.start();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
