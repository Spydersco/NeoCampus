/**
 * 
 */
package baseDeDonnees;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utilisateurs.Groupe;
import utilisateurs.TypeUtilisateur;
import utilisateurs.Utilisateur;

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

	/**
	 * @return the groupeDAO
	 */
	public GroupeDAO getGroupeDAO() {
		return groupeDAO;
	}



	/**
	 * @return the messageDAO
	 */
	public MessageDAO getMessageDAO() {
		return messageDAO;
	}



	/**
	 * @return the ticketDAO
	 */
	public TicketDAO getTicketDAO() {
		return ticketDAO;
	}



	/**
	 * @return the utilisateurDAO
	 */
	public UtilisateurDAO getUtilisateurDAO() {
		return utilisateurDAO;
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
	
	public void executerRequete(String requete) {
		try {
			connect.createStatement().executeQuery(requete);
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void effacerBaseDeDonn�es() {
		try {
			connect.createStatement().executeQuery("DELETE FROM Lire");
			connect.createStatement().executeQuery("DELETE FROM Message");
			connect.createStatement().executeQuery("DELETE FROM Ticket");
			connect.createStatement().executeQuery("DELETE FROM Appartenir");
			connect.createStatement().executeQuery("DELETE FROM Utilisateur");
			connect.createStatement().executeQuery("DELETE FROM Groupe");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int idGroupeSuivant() {
		try {
			ResultSet res = connect.createStatement().executeQuery("SELECT * FROM Groupe");
			int max = 0;
			while(res.next()) {
				int id = res.getInt("grp_id");
				if (id > max)
					max = id;
			}
			return max+1;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int idMessageSuivant() {
		try {
			ResultSet res = connect.createStatement().executeQuery("SELECT * FROM Message");
			int max = 0;
			while(res.next()) {
				int id = res.getInt("msg_id");
				if (id > max)
					max = id;
			}
			return max+1;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int idTicketSuivant() {
		try {
			ResultSet res = connect.createStatement().executeQuery("SELECT * FROM Ticket");
			int max = 0;
			while(res.next()) {
				int id = res.getInt("tic_id");
				if (id > max)
					max = id;
			}
			return max+1;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int idUtilisateurSuivant() {
		try {
			ResultSet res = connect.createStatement().executeQuery("SELECT * FROM Utilisateur");
			int max = 0;
			while(res.next()) {
				int id = res.getInt("uti_id");
				if (id > max)
					max = id;
			}
			return max+1;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public void supprimerUtilisateur(int id) {
		UtilisateurDAO uDAO = new UtilisateurDAO(connect);
		Utilisateur utilisateur = uDAO.find(id);
		utilisateur.setPrenom("Utilisateur");
		utilisateur.setNom("supprim�");
		utilisateur.setMotDePasse(null);
		uDAO.update(utilisateur);
	}

	public void creerUtilisateur(String prenom, String nom, String motDePasse, TypeUtilisateur type) {
		UtilisateurDAO uDAO = new UtilisateurDAO(connect);
		uDAO.create(new Utilisateur(idUtilisateurSuivant(), prenom, nom, motDePasse, type));
	}

	public void creerGroupe(String nom) {
		GroupeDAO gDAO = new GroupeDAO(connect);
		gDAO.create(new Groupe(idGroupeSuivant(), nom, 0));
	}

	public void supprimerGroupe(int id) {
		GroupeDAO gDAO = new GroupeDAO(connect);
		gDAO.delete(gDAO.find(id));
	}

	public void ajouterMembre(int idGroupe, int idMembre) {
		try {
			PreparedStatement prepare = connect
					.prepareStatement("INSERT INTO Appartenir (grp_id, uti_id) VALUES (?, ?)");
			prepare.setInt(1, idGroupe);
			prepare.setInt(2, idMembre);
			prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void supprimerMembre(int idGroupe, int idMembre) {
		try {
			connect.createStatement()
					.executeQuery("DELETE FROM Appartenir WHERE grp_id = " + idGroupe + "AND uti_id = " + idMembre);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addLecteur (int idMessage, int idLecteur) {
		try {
			PreparedStatement prepare = connect
					.prepareStatement("INSERT INTO Lire (msg_id, uti_id) VALUES (?, ?)");
			prepare.setInt(1, idMessage);
			prepare.setInt(2, idLecteur);
			prepare.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
