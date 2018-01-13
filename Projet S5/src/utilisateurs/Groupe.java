package utilisateurs;

import java.util.LinkedList;
import java.util.List;

import utilisateurs.Utilisateur;
import messages.Ticket;

public class Groupe {

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Groupe " + id + "," + nom;
	}

	private int id;
	private String nom;
	private List<Utilisateur> membres;
	private List<Ticket> tickets;

	public Groupe() {

	}

	/**
	 * @param id
	 * @param nom
	 */
	public Groupe(int id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
		this.membres = new LinkedList<Utilisateur>();
		this.tickets = new LinkedList<Ticket>();
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
	 * @return the membres
	 */
	public List<Utilisateur> getMembres() {
		return membres;
	}

	/**
	 * @param membres
	 *            the membres to set
	 */
	public void setMembres(List<Utilisateur> membres) {
		this.membres = membres;
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

	public void addMembres(Utilisateur... utilisateurs) {
		for (Utilisateur u : utilisateurs)
			this.membres.add(u);
	}

	public void removeMembres(Utilisateur... utilisateurs) {
		for (Utilisateur u : utilisateurs)
			this.membres.remove(u);
	}
	
	public void addTicket(Ticket... tickets) {
		for (Ticket t : tickets)
			this.tickets.add(t);
	}

	public void removeTicket(Ticket... tickets) {
		for (Ticket t : tickets)
			this.tickets.remove(t);
	}

	public void creerTicket(String titre, String corps, Utilisateur auteur) {
		int id = tickets.size();
		Ticket ticket = new Ticket(id, titre, auteur);
		tickets.add(ticket);
		ticket.creerMessage(corps, auteur);
	}

}
