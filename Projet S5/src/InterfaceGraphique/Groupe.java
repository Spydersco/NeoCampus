import java.util.ArrayList;
import java.util.List;

public class Groupe {

	private int id;
	private String nom;
	private List<Utilisateur> membres;
	private List<FilDeDiscussion> discussions;// ( -> Groupe dans FilDeDiscussion)
	
	public Groupe(int id, String nom) {
		this.id = id;
		this.nom = nom;
		this.membres = new ArrayList<>();
		this.discussions = new ArrayList<>();
	}
	
	//TODO Ajouter exception crée
	public void ajouterUtilisateur(Utilisateur utilisateur){
		if(this.membres.contains(utilisateur)){
			
		} else {
			this.membres.add(utilisateur);
		}
	}
	
	//TODO Ajouter exception crée
		public void supprimerUtilisateur(Utilisateur utilisateur){
			if(!this.membres.contains(utilisateur)){
				
			} else {
				this.membres.remove(utilisateur);
			}
		}
		
		
		public String getNom() {
			return nom;
		}
		
		public List<FilDeDiscussion> getDiscussions() {
			return discussions;
		}
		
		//Test interface client
		public void ajouterDiscussion(FilDeDiscussion fdd){
			this.discussions.add(fdd);
		}
}
