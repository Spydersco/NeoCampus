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
					"INSERT INTO Message (msg_id, msg_corps, msg_date, msg_statut, msg_auteur) VALUES(?, ?, ?, ?, ?)");

			prepare.setInt(1, obj.getId());
			prepare.setString(2, obj.getCorps());
			prepare.setString(3, obj.getDate());
			prepare.setString(4, obj.getEtat().name());
			prepare.setInt(5, obj.getAuteur().getId());
			prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void delete(Message obj) {
		try {
			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
					.executeUpdate("DELETE FROM Message msg_id = " + obj.getId());

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Message obj) {
		try {
			ResultSet res = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
					.executeQuery("SELECT * FROM Message WHERE msg_id = " + obj.getId());
			res.updateInt(1, obj.getId());
			res.updateString(2, obj.getCorps());
			res.updateString(3, obj.getDate());
			res.updateInt(4, obj.getEtat().ordinal());
			res.updateInt(5, obj.getAuteur().getId());
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
				message = new Message(id, result.getString("msg_corps"), result.getString("msg_date"), StatutMessage.valueOf(result.getString("msg_statut")), utilisateurDAO.find(result.getInt("msg_auteur")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return message;
	}
}