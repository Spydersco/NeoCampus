package utilisateurs;
import java.util.LinkedList;
import java.util.List;

import utilisateurs.Utilisateur;
import messages.Ticket;


public class Groupe {

	private int id;
	private String nom;
	private List<Utilisateur> membres;
	private List<Ticket> tickets;

	public Groupe() {
		
	}
	
	/**
	 * @param iD
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
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param id the id to set
	 */
	public void setid(int id) {
		this.id = id;
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
		ticket.creerMessage(corps, auteur);
	}

}
