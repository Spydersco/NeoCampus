package utilisateurs;

import java.util.LinkedList;
import java.util.List;

import utilisateurs.Utilisateur;
import messages.Ticket;

public class Groupe {

	private int id;
	private String nom;
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
		Ticket ticket = new Ticket(id, titre, this.id);
		tickets.add(ticket);
		ticket.creerMessage(corps, auteur);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Groupe " + id + "," + nom;
	}

}
