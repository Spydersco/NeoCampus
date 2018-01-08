import java.util.LinkedList;
import java.util.List;


public class Groupe {

	private int iD;
	private String nom;
	private List<Utilisateur> membres;
	private List<Ticket> tickets;

	/**
	 * @param iD
	 * @param nom
	 */
	public Groupe(int iD, String nom) {
		super();
		this.iD = iD;
		this.nom = nom;
		this.membres = new LinkedList<Utilisateur>(); 
		this.tickets = new LinkedList<Ticket>();
	}

	/**
	 * @return the iD
	 */
	public int getiD() {
		return iD;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param iD the iD to set
	 */
	public void setiD(int iD) {
		this.iD = iD;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	public void addMembres(Utilisateur ...utilisateurs) {
		for(Utilisateur u : utilisateurs)
			this.membres.add(u);
	}

	public void removeMembres(Utilisateur ...utilisateurs) {
		for(Utilisateur u : utilisateurs)
			this.membres.remove(u);
	}

	public void creerTicket(String titre, String corps, Utilisateur auteur) {
		int id = tickets.size();
		Ticket ticket = new Ticket(id, titre, auteur);
		tickets.add(ticket);
		ticket.creerMessage(corps);
	}

}
