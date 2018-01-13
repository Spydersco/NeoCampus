/**
 * 
 */
package baseDeDonnees;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import messages.Ticket;

/**
 * @author Damien
 *
 */
public class TicketDAO extends DAO<Ticket> {
	
	/**
	 * @param conn
	 */
	public TicketDAO(Connection conn) {
		super(conn);
	}

	@Override
	public void create(Ticket obj) {
		try {
			PreparedStatement prepare = this.connect.prepareStatement(
					"INSERT INTO ticket (tic_id, tic_titre, tic_groupe) VALUES(?, ?)");

			prepare.setInt(1, obj.getId());
			prepare.setString(2, obj.getTitre());
			prepare.setInt(3, obj.getIdGroupe());
			prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void delete(Ticket obj) {
		try {
			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
					.executeUpdate("DELETE FROM Ticket WHERE tic_id = " + obj.getId());

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Ticket obj) {
		try {
			ResultSet res = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
					.executeQuery("SELECT * FROM Ticket WHERE tic_id = " + obj.getId());
			res.updateInt(1, obj.getId());
			res.updateString(2, obj.getTitre());
			res.updateInt(3, obj.getIdGroupe());
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public Ticket find(int id) {
		Ticket ticket = new Ticket();
		MessageDAO messageDAO = new MessageDAO(connect);
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM Ticket WHERE tic_id = " + id);
			if (result.next())
				ticket = new Ticket(id, result.getString("tic_titre"), result.getInt("tic_groupe"));
			result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM Message WHERE msg_ticket = " + id);
			while (result.next())
				ticket.addMessage(messageDAO.find(result.getInt("msg_id")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ticket;
	}
}