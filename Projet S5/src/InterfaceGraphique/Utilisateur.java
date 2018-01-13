import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

//Ajout d'une liste de groupe permettant de savoir le ou les groupes auxquelles appartient
//chaque utilisateur
public class Utilisateur {
	
	private String id;
	private String motDePasse;
	private String nom;
	private String prenom;
	private TypeUtilisateur type;
	private Set<Groupe> groupes;
	
	public Utilisateur(String id, String motDePasse, String nom, String prenom, TypeUtilisateur type) {
		this.id = id;
		this.motDePasse = motDePasse;
		this.nom = nom;
		this.prenom = prenom;
		this.type = type;
		this.groupes = new TreeSet<>();
	}
	
	public String getId() {
		return id;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}
	
	//TODO Exception pas dans le groupe OU message
	//(Ajout d'un retour de fonction) + null ? id ?
	//TODO Envoyer message au serveur (Infos necessaire a la creation d'un fil -> titre, user, groupe)
	public FilDeDiscussion creerFile(Groupe groupe, String titre, Message message){
		return new FilDeDiscussion(1, titre, groupe, message, this);
	}
}
