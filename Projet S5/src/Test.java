import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import baseDeDonnees.MessageDAO;
import baseDeDonnees.TicketDAO;
import baseDeDonnees.UtilisateurDAO;
import messages.Message;
import messages.StatutMessage;


/**
 * 
 */

/**
 * @author Damien
 *
 */
public class Test {

	/**
	 * @param args
	 * @throws SQLException
	 */
	public static void main(String[] args) throws SQLException {
		Connection connexion = null;
		try {
			Class.forName("org.hsqldb.jdbcDriver").newInstance();
			String url = "jdbc:hsqldb:file:NeoCampus";
			String user = "sa";
			String passwd = "";
			connexion = (Connection) DriverManager.getConnection(url, user, passwd);

		} catch (Exception e) {
			e.printStackTrace();
		}

		UtilisateurDAO utilisateurDAO = new UtilisateurDAO(connexion);
		TicketDAO ticketDAO = new TicketDAO(connexion);
		
		MessageDAO messageDAO = new MessageDAO(connexion);
		messageDAO.create(new Message(1, "abc", "10/01/2018 00:13", StatutMessage.PAS_RECU_SERVEUR, utilisateurDAO.find(1), ticketDAO.find(1)));
		messageDAO.create(new Message(2, "abcdef", "10/01/2018 00:15", StatutMessage.PAS_RECU_SERVEUR, utilisateurDAO.find(2), ticketDAO.find(1)));
		messageDAO.create(new Message(3, "abcdefgh", "10/01/2018 00:18", StatutMessage.PAS_RECU_SERVEUR, utilisateurDAO.find(1), ticketDAO.find(1)));
		
		Statement statement = connexion.createStatement();
		statement.executeQuery("SHUTDOWN");
		statement.close();

	}
}
