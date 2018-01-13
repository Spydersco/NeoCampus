import java.util.ArrayList;
import java.util.List;
//Changement du type premierMessage + ajout attribut groupe
public class FilDeDiscussion {

	private int id;
	private String titre;
	private Message premierMessage;
	private Utilisateur createur;
	private List<Message> messages = new ArrayList<>();
	private Groupe groupe;
	
	public FilDeDiscussion(int id, String titre, Groupe groupe, Message premierMessage, Utilisateur createur) {
		this.id = id;
		this.titre = titre;
		this.premierMessage = premierMessage;
		this.createur = createur;
		this.messages.add(premierMessage);
		this.groupe = groupe;
	}
	
	public String getTitre() {
		return titre;
	}
}
