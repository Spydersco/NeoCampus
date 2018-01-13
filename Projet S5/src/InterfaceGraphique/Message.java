import java.util.Date;

public class Message {

	private int id;
	private String message;
	private Date date;
	private Utilisateur auteur;
	private Statut statut;
	
	public Message(int id, String message, Date date, Utilisateur auteur, Statut statut) {
		this.id = id;
		this.message = message;
		this.date = date;
		this.auteur = auteur;
		this.statut = statut;
	}
	
	public String afficherMessage() {
		return this.message;
	}
	
	
	public int compareTo(Message message) {
		// TP6
		int comparaison = 0;
		if (this.date.before(message.date)){
			comparaison = 1;
		} else if (this.date.after(message.date)){
			comparaison = -1;
		}
		return comparaison;
	}
}
