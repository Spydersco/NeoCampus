import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import baseDeDonnees.GroupeDAO;
import baseDeDonnees.UtilisateurDAO;
import utilisateurs.Groupe;
import utilisateurs.Utilisateur;

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
		GroupeDAO groupeDAO = new GroupeDAO(connexion);
		groupeDAO.create(new Groupe(1, "groupe1"));
		Groupe groupe = groupeDAO.find(1);
		System.out.println(groupe.getNom());
		groupeDAO.delete(groupe);
		UtilisateurDAO utilisateurDAO = new UtilisateurDAO(connexion);
		utilisateurDAO.create(new Utilisateur(1, "Netflix", "Sebastien", "azerty"));
		Utilisateur utilisateur = utilisateurDAO.find(1);
		System.out.println(utilisateur.getNom());
		
		Statement statement = connexion.createStatement();
		statement.executeQuery("SHUTDOWN");
		statement.close();
		
	}

}
