package reseau;

import java.io.IOException;
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Serveur {

	public static void main(String[] args) {

		ServerSocket socket;
		Connection connect = null;

		try {
			socket = new ServerSocket(2018);
			Class.forName("org.hsqldb.jdbcDriver").newInstance();
			String url = "jdbc:hsqldb:file:NeoCampus";
			String user = "sa";
			String passwd = "";
			connect = (Connection) DriverManager.getConnection(url, user, passwd);
			new Thread(new AccepterConnexion(socket, connect)).start();
		} 
		catch (IOException | InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
