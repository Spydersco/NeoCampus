package messages;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import messages.Message;
import utilisateurs.*;

public class Ticket implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2976968391653307366L;
	private int id;
	private String titre;
	private int idAuteur;
	private int idGroupe;
	private List<Message> messages;

	public Ticket() {

	}

	/**
	 * @param id
	 * @param titre
	 * @param idAuteur
	 * @param idGroupe
	 */
	public Ticket(int id, String titre, int idAuteur, int idGroupe) {
		super();
		this.id = id;
		this.titre = titre;
		this.idAuteur = idAuteur;
		this.idGroupe = idGroupe;
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
	 * @return the idAuteur
	 */
	public int getIdAuteur() {
		return idAuteur;
	}

	/**
	 * @param idAuteur
	 *            the idAuteur to set
	 */
	public void setIdAuteur(int idAuteur) {
		this.idAuteur = idAuteur;
	}

	/**
	 * @return the idGroupe
	 */
	public int getIdGroupe() {
		return idGroupe;
	}

	/**
	 * @param idGroupe
	 *            the idGroupe to set
	 */
	public void setIdGroupe(int idGroupe) {
		this.idGroupe = idGroupe;
	}

	/**
	 * @return the messages
	 */
	public List<Message> getMessages() {
		return messages;
	}

	/**
	 * @param messages
	 *            the messages to set
	 */
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	
	public void addMessage(Message... messages) {
		for (Message msg : messages)
			this.messages.add(msg);
	}
	
	public void removeMessage(Message... messages) {
		for (Message msg : messages)
			this.messages.remove(msg);
	}

	public void creerMessage(String corps, Utilisateur auteur) {
		Date date = new Date();
		DateFormat shortDateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT,DateFormat.SHORT);
		Message monMessage = new Message(0, corps, shortDateFormat.format(date), auteur.getId(), StatutMessage.PAS_RECU_SERVEUR, this.id);
		messages.add(monMessage);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Ticket [id=" + id + ", titre=" + titre + ", idAuteur=" + idAuteur + ", idGroupe=" + idGroupe
				+ ", messages=" + messages + "]";
	}


}
