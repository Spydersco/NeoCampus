import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Ticket {

	private int iD;
	private String titre;
	private Utilisateur auteur;
	private List<Message> messages;

	/**
	 * @param iD
	 * @param titre
	 * @param auteur
	 */
	public Ticket(int iD, String titre, Utilisateur auteur) {
		super();
		this.iD = iD;
		this.titre = titre;
		this.auteur = auteur;
		this.messages = new LinkedList<Message>();
	}

	/**
	 * @return the titre
	 */
	public String getTitre() {
		return titre;
	}

	/**
	 * @return the iD
	 */
	public int getiD() {
		return this.iD;
	}

	/**
	 * @return the auteur
	 */
	public Utilisateur getAuteur() {
		return auteur;
	}

	/**
	 * @param titre the titre to set
	 */
	public void setTitre(String titre) {
		this.titre = titre;
	}

	/**
	 * @param auteur the auteur to set
	 */
	public void setAuteur(Utilisateur auteur) {
		this.auteur = auteur;
	}

	public void creerMessage(String corps) {
		Message monMessage = new Message(messages.size(), corps, new Date(System.currentTimeMillis()));
		messages.add(monMessage);
	}

}
