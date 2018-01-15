package messages;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import utilisateurs.Utilisateur;

public class Message implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3903918310813787931L;
	private int id;
	private String corps;
	private String date;
	private int idAuteur;
	private StatutMessage statut;
	private List<Integer> lecteurs = new LinkedList<>();
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
	public Message(int id, String corps, String date, int idAuteur, StatutMessage statut, int idTicket) {
		super();
		this.id = id;
		this.corps = corps;
		this.date = date;
		this.idAuteur = idAuteur;
		this.statut = statut;
		this.idTicket = idTicket;
	}

	public void afficherMessage() {
		System.out.println(this.corps);
	}

	/**
	 * @param lecteurs the lecteurs to set
	 */
	public void setLecteurs(List<Integer> lecteurs) {
		this.lecteurs = lecteurs;
	}

	/**
	 * @return the lecteurs
	 */
	public List<Integer> getLecteurs() {
		return lecteurs;
	}
	
	public void addLecteur(Integer...lecteurs) {
		for(Integer lecteur : lecteurs)
			this.lecteurs.add(lecteur);
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
	public int getAuteur() {
		return idAuteur;
	}

	/**
	 * @param auteur
	 *            the auteur to set
	 */
	public void setAuteur(Utilisateur auteur) {
		this.idAuteur = idAuteur;
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
		return "Message " + id + "," + corps + "," + date + "," + idAuteur + "," + statut + "," + idTicket;
	}
}
