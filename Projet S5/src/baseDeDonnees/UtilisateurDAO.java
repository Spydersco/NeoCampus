/**
 * 
 */
package baseDeDonnees;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utilisateurs.TypeUtilisateur;
import utilisateurs.Utilisateur;

/**
 * @author Damien
 *
 */
public class UtilisateurDAO extends DAO<Utilisateur> {

	/**
	 * @param conn
	 */
	public UtilisateurDAO(Connection conn) {
		super(conn);
	}

	@Override
	public void create(Utilisateur obj) {
		try {
			PreparedStatement prepare = this.connect.prepareStatement(
					"INSERT INTO Utilisateur (uti_id, uti_nom, uti_prenom, uti_motDePasse, uti_type) VALUES(?, ?, ?, ?, ?)");

			prepare.setInt(1, obj.getId());
			prepare.setString(2, obj.getNom());
			prepare.setString(3, obj.getPrenom());
			prepare.setString(4, obj.getMotDePasse());
			prepare.setString(5, obj.getType().name());
			prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Utilisateur obj) {
		try {
			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
			.executeQuery("DELETE FROM Ticket WHERE tic_auteur = " + obj.getId());
			
			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
			.executeQuery("DELETE FROM Message WHERE msg_auteur = " + obj.getId());
						
			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
			.executeQuery("DELETE FROM Appartenir WHERE uti_id = " + obj.getId());
			
			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
					.executeUpdate("DELETE FROM Utilisateur WHERE uti_id = " + obj.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Utilisateur obj) {
		try {
			ResultSet res = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
					.executeQuery("SELECT * FROM Utilisateur WHERE uti_id = " + obj.getId());
			res.updateInt(1, obj.getId());
			res.updateString(2, obj.getNom());
			res.updateString(3, obj.getPrenom());
			res.updateString(4, obj.getMotDePasse());
			res.updateString(5, obj.getType().name());
			res.updateRow();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Utilisateur find(int id) {
		Utilisateur utilisateur = new Utilisateur();
		TicketDAO ticketDAO = new TicketDAO(connect);
		GroupeDAO groupeDAO = new GroupeDAO(connect);
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM Utilisateur WHERE uti_id = " + id);
			if (result.next()) {
				utilisateur = new Utilisateur(id, result.getString("uti_nom"), result.getString("uti_prenom"),
						result.getString("uti_motDePasse"), TypeUtilisateur.valueOf(result.getString("uti_type")));
			}
			result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM Ticket INNER JOIN Appartenir ON Ticket.tic_groupe = Appartenir.grp_id");
			while (result.next())
				utilisateur.addTicket(ticketDAO.find(result.getInt("tic_id")));
			result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM Appartenir WHERE uti_id=" + id);
			while (result.next())
				utilisateur.addGroupe(groupeDAO.find(result.getInt("grp_id")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return utilisateur;
	}
}
