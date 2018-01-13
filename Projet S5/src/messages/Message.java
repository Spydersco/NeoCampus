package messages;

import utilisateurs.Utilisateur;

public class Message {

	private int id;
	private String corps;
	private String date;
	private Utilisateur auteur;
	private StatutMessage statut;
	private int idTicket;

	public Message() {

	}

	/**
	 * @param id
	 * @param corps
	 * @param date
	 * @param auteur
	 * @param statut
	 * @param idTicket
	 */
	public Message(int id, String corps, String date, Utilisateur auteur, StatutMessage statut, int idTicket) {
		super();
		this.id = id;
		this.corps = corps;
		this.date = date;
		this.auteur = auteur;
		this.statut = statut;
		this.idTicket = idTicket;
	}

	public void afficherMessage() {
		System.out.println(this.corps);
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
	 * @return the corps
	 */
	public String getCorps() {
		return corps;
	}

	/**
	 * @param corps
	 *            the corps to set
	 */
	public void setCorps(String corps) {
		this.corps = corps;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(String date) {
		this.date = date;
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
	 * @return the statut
	 */
	public StatutMessage getStatut() {
		return statut;
	}

	/**
	 * @param statut the statut to set
	 */
	public void setStatut(StatutMessage statut) {
		this.statut = statut;
	}

	/**
	 * @return the idTicket
	 */
	public int getIdTicket() {
		return idTicket;
	}

	/**
	 * @param idTicket the idTicket to set
	 */
	public void setIdTicket(int idTicket) {
		this.idTicket = idTicket;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Message " + id + "," + corps + "," + date + "," + auteur + "," + statut + "," + idTicket;
	}
}
