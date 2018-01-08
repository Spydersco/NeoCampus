package messages;

import utilisateurs.Utilisateur;

public class Message {

	private int id;
	private String corps;
	private String date;
	private Utilisateur auteur;
	private StatutMessage etat;

	public Message() {

	}

	/**
	 * @param id
	 * @param corps
	 * @param date
	 * @param auteur
	 */
	public Message(int id, String corps, String date, StatutMessage etat, Utilisateur auteur) {
		super();
		this.id = id;
		this.corps = corps;
		this.date = date;
		this.auteur = auteur;
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
	 * @return the etat
	 */
	public StatutMessage getEtat() {
		return etat;
	}

	/**
	 * @param etat
	 *            the etat to set
	 */
	public void setEtat(StatutMessage etat) {
		this.etat = etat;
	}

}
