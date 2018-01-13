import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class Client {

	private JFrame frame;
	private List<Groupe> listGroupes;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client window = new Client();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Client() {
		listGroupes = new ArrayList<>();
		Groupe groupe1 = new Groupe(1, "TP31");
		listGroupes.add(groupe1);
		Groupe groupe2 = new Groupe(2, "TP32");
		listGroupes.add(groupe2);
		Groupe groupe3 = new Groupe(3, "TP41");
		listGroupes.add(groupe3);
		
		Utilisateur Joshua = new Utilisateur("User1", "Allo", "Salort", "Joshua", TypeUtilisateur.CAMPUS);
		Utilisateur Damien = new Utilisateur("User2", "Allo", "Raufaste", "Damien", TypeUtilisateur.CAMPUS);
		Utilisateur Sebastien = new Utilisateur("User3", "Allo", "Niarfeix", "Sebastien", TypeUtilisateur.CAMPUS);

		Message message1Groupe1 = new Message(1, "Probleme dans la salle U3 105", new Date(), Joshua, Statut.GRIS);
		FilDeDiscussion discussion1Groupe1 = new FilDeDiscussion(1, "probleme", groupe1, message1Groupe1, Joshua);
		Message message2Groupe1 = new Message(2, "Gros probleme dans la salle U2 115", new Date(), Joshua, Statut.GRIS);
		FilDeDiscussion discussion2Groupe1 = new FilDeDiscussion(2, "Gros probleme", groupe1, message2Groupe1, Joshua);
		Message message3Groupe1 = new Message(3, "Aucun problème", new Date(), Joshua, Statut.GRIS);
		FilDeDiscussion discussion3Groupe1 = new FilDeDiscussion(3, "Tout va bien", groupe1, message3Groupe1, Joshua);
		
		groupe1.ajouterDiscussion(discussion1Groupe1);
		groupe1.ajouterDiscussion(discussion2Groupe1);
		groupe1.ajouterDiscussion(discussion3Groupe1);

		Message message1Groupe2 = new Message(4, "L'ordinateur salle U3 115 fume", new Date(), Damien, Statut.GRIS);
		FilDeDiscussion discussion1Groupe2 = new FilDeDiscussion(4, "Probleme ordinateur", groupe2, message1Groupe2, Damien);
		Message message2Groupe2 = new Message(5, "L'ordinateur qui fumé a mis feu à la salle", new Date(), Damien, Statut.GRIS);
		FilDeDiscussion discussion2Groupe2 = new FilDeDiscussion(5, "Feu dans une salle", groupe2, message2Groupe2, Damien);
		groupe2.ajouterDiscussion(discussion1Groupe2);
		groupe2.ajouterDiscussion(discussion2Groupe2);

		Message message1Groupe3 = new Message(6, "Je ne sais pas codé un tableau", new Date(), Sebastien, Statut.GRIS);
		FilDeDiscussion discussion1Groupe3 = new FilDeDiscussion(6, "Probleme Java", groupe2, message1Groupe3, Sebastien);
		groupe3.ajouterDiscussion(discussion1Groupe3);

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(1200, 900, 1200, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 196, 849);
		frame.getContentPane().add(scrollPane);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(216, 13, 582, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JTree tree;
		DefaultMutableTreeNode groupes = new DefaultMutableTreeNode("Groupes");
		
		for(int i = 0; i < this.listGroupes.size(); i++){
			DefaultMutableTreeNode groupe = new DefaultMutableTreeNode(this.listGroupes.get(i).getNom());
			for (int j = 0; j < this.listGroupes.get(i).getDiscussions().size(); j++){
				DefaultMutableTreeNode groupeFils = new DefaultMutableTreeNode(this.listGroupes.get(i).getDiscussions().get(j).getTitre());
				groupe.add(groupeFils);
			}
			groupes.add(groupe);
		}

		tree = new JTree(groupes);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.CONTIGUOUS_TREE_SELECTION);
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode choix = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				if (choix == null){
					return;
				} else {
					Object objetChoix = choix.getUserObject();
					if (choix.isLeaf()){
						lblNewLabel.setText((String) objetChoix);
					}
				}
			}
		});
		scrollPane.setViewportView(tree);
		
		JTextArea textArea = new JTextArea();
		JScrollPane scrollTexte = new JScrollPane(textArea);
		scrollTexte.setBounds(216, 774, 615, 62);
		frame.getContentPane().add(scrollTexte);

		JButton button = new JButton("Envoyer");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
			}
		});
		button.setBounds(927, 790, 89, 23);
		frame.getContentPane().add(button);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(216, 38, 968, 725);
		frame.getContentPane().add(scrollPane_1);
		
		frame.setResizable(false);
	}
}
