/**
 * 
 */
package baseDeDonnees;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import messages.Message;
import messages.StatutMessage;

/**
 * @author Damien
 *
 */
public class MessageDAO extends DAO<Message> {

	/**
	 * @param conn
	 */
	public MessageDAO(Connection conn) {
		super(conn);
	}

	@Override
	public void create(Message obj) {
		try {
			PreparedStatement prepare = this.connect.prepareStatement(
					"INSERT INTO Message (msg_id, msg_corps, msg_date, msg_statut, msg_auteur, msg_ticket) VALUES(?, ?, ?, ?, ?, ?)");

			prepare.setInt(1, obj.getId());
			prepare.setString(2, obj.getCorps());
			prepare.setString(3, obj.getDate());
			prepare.setString(4, obj.getStatut().name());
			prepare.setInt(5, obj.getAuteur().getId());
			prepare.setInt(6, obj.getIdTicket());
			prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Message obj) {
		try {
			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
					.executeQuery("DELETE FROM Lire WHERE msg_id = " + obj.getId());
			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
					.executeUpdate("DELETE FROM Message WHERE msg_id = " + obj.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Message obj) {
		try {
			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
					.executeUpdate("UPDATE Message SET " + "msg_corps = '" + obj.getCorps() + "', " + "msg_date = '"
							+ obj.getDate() + "', " + "msg_statut = '" + obj.getStatut().name() + "', "
							+ "msg_auteur = '" + obj.getAuteur().getId() + "', " + "msg_idTicket = '"
							+ obj.getIdTicket() + "', " + "WHERE msg_id = " + obj.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Message find(int id) {
		Message message = new Message();
		UtilisateurDAO utilisateurDAO = new UtilisateurDAO(connect);
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM Message WHERE msg_id = " + id);
			if (result.next())
				message = new Message(id, result.getString("msg_corps"), result.getString("msg_date"),
						utilisateurDAO.find(result.getInt("msg_auteur")),
						StatutMessage.valueOf(result.getString("msg_statut")), result.getInt("msg_ticket"));
			result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM Lire WHERE msg_id = " + id);
			while (result.next()) {
				message.addLecteur(result.getInt("uti_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return message;
	}
}