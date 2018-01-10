import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


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
		
		statement.executeQuery("SHUTDOWN");
		statement.close();

	}
}
