/**
 * 
 */
package baseDeDonnees;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Damien
 *
 */
public class GestionBaseDeDonnee {
	Connection connect;
	GroupeDAO groupeDAO;
	MessageDAO messageDAO;
	TicketDAO ticketDAO;
	UtilisateurDAO utilisateurDAO;

	public GestionBaseDeDonnee(Connection connect) {
		this.connect = connect;
		this.groupeDAO = new GroupeDAO(connect);
		this.messageDAO = new MessageDAO(connect);
		this.ticketDAO = new TicketDAO(connect);
		this.utilisateurDAO = new UtilisateurDAO(connect);
	}

	public void afficherGroupes() {
		try {
			ResultSet res = connect.createStatement().executeQuery("SELECT * FROM Groupe");
			while (res.next()) {
				System.out.println(groupeDAO.find(res.getInt(1)).toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void afficherMessages() {
		try {
			ResultSet res = connect.createStatement().executeQuery("SELECT * FROM Message");
			while (res.next()) {
				System.out.println(messageDAO.find(res.getInt(1)).toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void afficherTickets() {
		try {
			ResultSet res = connect.createStatement().executeQuery("SELECT * FROM Ticket");
			while (res.next()) {
				System.out.println(ticketDAO.find(res.getInt(1)).toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void afficherUtilisateurs() {
		try {
			ResultSet res = connect.createStatement().executeQuery("SELECT * FROM Utilisateur");
			while (res.next()) {
				System.out.println(utilisateurDAO.find(res.getInt(1)).toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void EffacerBaseDeDonnées() {
		try {
			connect.createStatement().executeQuery("DELETE FROM Message");
			connect.createStatement().executeQuery("DELETE FROM Ticket");
			connect.createStatement().executeQuery("DELETE FROM Utilisateur");
			connect.createStatement().executeQuery("DELETE FROM Groupe");
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int idGroupeSuivant () {
		try {
			ResultSet res = connect.createStatement().executeQuery("SELECT * FROM Groupe");
			int compteur = 1;
			while(res.next())
				compteur++;
			return compteur;
		}catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public int idMessageSuivant () {
		try {
			ResultSet res = connect.createStatement().executeQuery("SELECT * FROM Message");
			int compteur = 1;
			while(res.next())
				compteur++;
			return compteur;
		}catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
		
	public int idTicketSuivant () {
		try {
			ResultSet res = connect.createStatement().executeQuery("SELECT * FROM Ticket");
			int compteur = 1;
			while(res.next())
				compteur++;
			return compteur;
		}catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public int idUtilisateurSuivant () {
		try {
			ResultSet res = connect.createStatement().executeQuery("SELECT * FROM Utilisateur");
			int compteur = 1;
			while(res.next())
				compteur++;
			return compteur;
		}catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
}
