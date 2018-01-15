import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import baseDeDonnees.GestionBaseDeDonnee;
import messages.Message;
import messages.StatutMessage;
import messages.Ticket;
import utilisateurs.Groupe;
import utilisateurs.TypeUtilisateur;
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

		Statement statement = connexion.createStatement() ;
		
		

		GestionBaseDeDonnee g = new GestionBaseDeDonnee(connexion);
		
		g.effacerBaseDeDonnées();

		Utilisateur joshua = new Utilisateur(0, "SALORT", "Joshua", "admin", TypeUtilisateur.UTILISATEUR);
		Utilisateur Sebastien = new Utilisateur(1, "Netflix", "Sebastien", "admin", TypeUtilisateur.UTILISATEUR);
		g.getUtilisateurDAO().create(joshua);
		g.getUtilisateurDAO().create(Sebastien);
		
		Ticket tck = new Ticket(0, "blabla", 1, 0);
		Message msg = new Message(0, "salut", "123", joshua.getId(), StatutMessage.PAS_RECU_SERVEUR, 0);
		Message msg2 = new Message(1, "salut comment ca va !", "123", Sebastien.getId(), StatutMessage.PAS_RECU_SERVEUR, 0);
		g.getGroupeDAO().create(new Groupe(0, "L3info", 0));
		g.getGroupeDAO().create(new Groupe(1, "L3stat", 0));
		g.getTicketDAO().create(tck);
		g.getMessageDAO().create(msg);
		g.getMessageDAO().create(msg2);
		g.ajouterMembre(0, 0);
		g.ajouterMembre(1, 0);
		g.ajouterMembre(0, 1);
		g.afficherGroupes();
		g.afficherMessages();
		g.afficherTickets();
		g.afficherUtilisateurs();
		statement.executeQuery("SHUTDOWN");
		statement.close();

	}
}
