package baseDeDonnees;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utilisateurs.Groupe;
import utilisateurs.Utilisateur;

public class GroupeDAO extends DAO<Groupe> {

	/**
	 * @param conn
	 */
	public GroupeDAO(Connection conn) {
		super(conn);
	}

	@Override
	public void create(Groupe obj) {
		try {
			PreparedStatement prepare = this.connect
					.prepareStatement("INSERT INTO Groupe (grp_id, grp_nom) VALUES(?, ?)");

			prepare.setInt(1, obj.getId());
			prepare.setString(2, obj.getNom());
			prepare.executeUpdate();
			for (Utilisateur membre : obj.getMembres()) {
				prepare = this.connect.prepareStatement("INSERT INTO Appartenir (grp_id, uti_id) VALUES(?, ?)");
				prepare.setInt(1, obj.getId());
				prepare.setInt(2, membre.getId());
				prepare.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Groupe obj) {
		try {
			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
					.executeUpdate("DELETE FROM Ticket WHERE tic_groupe = " + obj.getId());
			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
					.executeUpdate("DELETE FROM Groupe WHERE grp_id = " + obj.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Groupe obj) {
		try {
			ResultSet res = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
					.executeQuery("SELECT * FROM Groupe WHERE grp_id = " + obj.getId());
			res.updateInt(1, obj.getId());
			res.updateString(2, obj.getNom());
			res.updateRow();
			for (Utilisateur membre : obj.getMembres()) {
				res = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
						.executeQuery("INSERT INTO Appartenir (grp_id, uti_id) VALUES(?, ?)");
				res.updateInt(1, obj.getId());
				res.updateInt(2, membre.getId());
				res.updateRow();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Groupe find(int id) {
		Groupe groupe = new Groupe();
		UtilisateurDAO utilisateurDAO = new UtilisateurDAO(connect);
		TicketDAO ticketDAO = new TicketDAO(connect);
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM Groupe WHERE grp_id = " + id);
			if (result.next())
				groupe = new Groupe(id, result.getString("grp_nom"));
			result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM Appartenir WHERE grp_id = " + id);
			while (result.next())
				groupe.addMembres(utilisateurDAO.find(result.getInt("uti_id")));
			result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM Ticket WHERE tic_groupe = " + id);
			while (result.next())
				groupe.addTicket(ticketDAO.find(result.getInt("tic_id")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return groupe;
	}
}
