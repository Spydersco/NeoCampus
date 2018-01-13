import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import baseDeDonnees.GestionBaseDeDonnee;
import baseDeDonnees.UtilisateurDAO;
import utilisateurs.Utilisateur;

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

		GestionBaseDeDonnee gbd = new GestionBaseDeDonnee(connexion);
		UtilisateurDAO uDAO = new UtilisateurDAO(connexion);
		gbd.EffacerBaseDeDonnées();
		gbd.afficherGroupes();
		gbd.afficherGroupes();
		gbd.afficherTickets();
		gbd.afficherUtilisateurs();
		Statement statement = connexion.createStatement();
		statement.executeQuery("SHUTDOWN");
		statement.close();

	}
}
