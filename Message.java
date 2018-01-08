import java.util.Date;

public class Message {

	private int iD;
	private String corps;
	private Date date;
	private Utilisateur auteur;
	private StatutMessage etat;

	/**
	 * @param iD
	 * @param corps
	 * @param date
	 */
	public Message(int iD, String corps, Date date) {
		super();
		this.iD = iD;
		this.corps = corps;
		this.date = date;
	}

	/**
	 * @return the iD
	 */
	public int getiD() {
		return iD;
	}

	/**
	 * @return the corps
	 */
	public String getCorps() {
		return corps;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @return the auteur
	 */
	public Utilisateur getAuteur() {
		return auteur;
	}

	/**
	 * @return the etat
	 */
	public StatutMessage getEtat() {
		return etat;
	}

	/**
	 * @param iD
	 *            the iD to set
	 */
	public void setiD(int iD) {
		this.iD = iD;
	}

	/**
	 * @param corps
	 *            the corps to set
	 */
	public void setCorps(String corps) {
		this.corps = corps;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @param auteur
	 *            the auteur to set
	 */
	public void setAuteur(Utilisateur auteur) {
		this.auteur = auteur;
	}

	/**
	 * @param etat
	 *            the etat to set
	 */
	public void setEtat(StatutMessage etat) {
		this.etat = etat;
	}

	public void afficherMessage() {
		System.out.println(this.corps);
	}

}
