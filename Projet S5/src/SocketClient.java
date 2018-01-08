import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient {

	private Socket soc;
	private ObjectOutputStream oos;
	private OutputStream os;

	/**
	 * 
	 */
	public SocketClient() {
		super();
		try {
			soc = new Socket("localHost", 9999);
			oos = new ObjectOutputStream(soc.getOutputStream());  
		} 
		catch (UnknownHostException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void connexionUtilisateur(String identifiant, String mdp) {
		try {
			soc.connect(soc.getLocalSocketAddress());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void demandeCreationTicket(Groupe grp, String titre, String premierMessage, Utilisateur auteur) {
		grp.creerTicket(titre, premierMessage, auteur); 
	
		try {
			oos.writeObject(ticket);
			oos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void envoiMessage(String corps) {
		
	}
}
