
public class Utilisateur {

	private int iD;
	private String motDePasse;
	private String nom;
	private String prenom;

	/**
	 * @param iD
	 * @param motDePasse
	 * @param nom
	 * @param prenom
	 */
	public Utilisateur(int iD, String motDePasse, String nom, String prenom) {
		super();
		this.iD = iD;
		this.motDePasse = motDePasse;
		this.nom = nom;
		this.prenom = prenom;
	}

	/**
	 * @return the iD
	 */
	public int getiD() {
		return iD;
	}

	/**
	 * @return the motDePasse
	 */
	public String getMotDePasse() {
		return motDePasse;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * @param iD the iD to set
	 */
	public void setiD(int iD) {
		this.iD = iD;
	}

	/**
	 * @param motDePasse the motDePasse to set
	 */
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	
	
}
