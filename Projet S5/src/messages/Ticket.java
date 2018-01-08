package messages;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import messages.Message;
import utilisateurs.*;

public class Ticket {

	private int id;
	private String titre;
	private Utilisateur auteur;
	private Groupe groupe;
	private List<Message> messages;

	public Ticket () {
		
	}
	/**
	 * @param id
	 * @param titre
	 * @param auteur
	 */
	public Ticket(int id, String titre, Utilisateur auteur) {
		super();
		this.id = id;
		this.titre = titre;
		this.auteur = auteur;
		this.messages = new LinkedList<Message>();
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the titre
	 */
	public String getTitre() {
		return titre;
	}

	/**
	 * @param titre
	 *            the titre to set
	 */
	public void setTitre(String titre) {
		this.titre = titre;
	}

	/**
	 * @return the auteur
	 */
	public Utilisateur getAuteur() {
		return auteur;
	}

	/**
	 * @param auteur
	 *            the auteur to set
	 */
	public void setAuteur(Utilisateur auteur) {
		this.auteur = auteur;
	}

	/**
	 * @return the groupe
	 */
	public Groupe getGroupe() {
		return groupe;
	}

	/**
	 * @param groupe
	 *            the groupe to set
	 */
	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}

	public void creerMessage(String corps, Utilisateur auteur) {
		Date date = new Date(System.currentTimeMillis());
		Message monMessage = new Message(0, corps, date.toString(), StatutMessage.PAS_RECU_SERVEUR, auteur);
		messages.add(monMessage);
	}

}
