/**
 * 
 */
package utilisateurs;

import java.util.LinkedList;
import java.util.List;

import messages.Ticket;

/**
 * @author Damien
 *
 */
public class Utilisateur {
	private int id;
	private String nom;
	private String prenom;
	private String identifiant;
	private String motDePasse;
	private List<Ticket> tickets = new LinkedList<>();

	public Utilisateur() {

	}

	/**
	 * @param id
	 * @param nom
	 * @param prenom
	 * @param identifiant
	 * @param motDePasse
	 */
	public Utilisateur(int id, String nom, String prenom, String identifiant, String motDePasse) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.identifiant = identifiant;
		this.motDePasse = motDePasse;
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
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom
	 *            the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * @param prenom
	 *            the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * @return the identifiant
	 */
	public String getIdentifiant() {
		return identifiant;
	}

	/**
	 * @param identifiant
	 *            the identifiant to set
	 */
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	/**
	 * @return the motDePasse
	 */
	public String getMotDePasse() {
		return motDePasse;
	}

	/**
	 * @param motDePasse
	 *            the motDePasse to set
	 */
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	/**
	 * @return the tickets
	 */
	public List<Ticket> getTickets() {
		return tickets;
	}

	/**
	 * @param tickets
	 *            the tickets to set
	 */
	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public void addTicket(Ticket ticket) {
		this.tickets.add(ticket);
	}

	public void removeTicket(Ticket ticket) {
		this.tickets.remove(ticket);
	}
}
