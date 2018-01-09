package reseau;

import java.io.IOException;
import java.net.*;
import java.sql.Connection;

public class Serveur {

	public static void main(String[] args) {

		ServerSocket socket;
		Connection connect = null;

		try {
			socket = new ServerSocket(9999);
		} 
		catch (IOException e) {
			System.out.println("toto");
			e.printStackTrace();
		}
	}

}
