/**
 * 
 */
package baseDeDonnees;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
					"INSERT INTO utilisateur (uti_id, uti_nom, uti_prenom, uti_identifiant, uti_motDePasse) VALUES(?, ?, ?, ?, ?)");

			prepare.setInt(1, obj.getId());
			prepare.setString(2, obj.getNom());
			prepare.setString(3, obj.getPrenom());
			prepare.setString(4, obj.getIdentifiant());
			prepare.setString(5, obj.getMotDePasse());
			prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void delete(Utilisateur obj) {
		try {
			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
					.executeUpdate("DELETE FROM Utillisateur uti_id = " + obj.getId());

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Utilisateur obj) {
		try {
			ResultSet res = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
					.executeQuery("SELECT * FROM utilisateur WHERE uti_id = " + obj.getId());
			res.updateInt(1, obj.getId());
			res.updateString(2, obj.getNom());
			res.updateString(3, obj.getPrenom());
			res.updateString(4, obj.getIdentifiant());
			res.updateString(5, obj.getMotDePasse());
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public Utilisateur find(int id) {
		Utilisateur utilisateur = new Utilisateur();

		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM utilisateur WHERE uti_id = " + id);
			if (result.next())
				utilisateur = new Utilisateur(id, result.getString("uti_nom"), result.getString("uti_prenom"), result.getString("uti_identifiant"), result.getString("uti_motDePasse"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return utilisateur;
	}
}
