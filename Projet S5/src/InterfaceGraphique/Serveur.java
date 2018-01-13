import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.plaf.synth.SynthSeparatorUI;

public class Serveur {

	private JFrame frame;
	private List<Utilisateur> users = new ArrayList<>();
	private List<JButton> btnUsers = new ArrayList<>();
	private JTextField txtNom;
	private JTextField txtPrenom;
	private JTextField txtID;
	private JTextField txtMDP;
	private Utilisateur userChoisi;
	private int i;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Serveur window = new Serveur();
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
	public Serveur() {
		Utilisateur Joshua = new Utilisateur("User1", "Allo", "Salort", "Joshua", TypeUtilisateur.CAMPUS);
		Utilisateur Damien = new Utilisateur("User2", "Allo", "Raufaste", "Damien", TypeUtilisateur.CAMPUS);
		Utilisateur Sebastien = new Utilisateur("User3", "Allo", "Niarfeix", "Sebastien", TypeUtilisateur.CAMPUS);
		this.users.add(Joshua);
		this.users.add(Damien);
		this.users.add(Sebastien);

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(1200, 900, 1200, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 1174, 849);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Utilisateur", panel);
		panel.setLayout(null);
		
		JScrollPane panel_2 = new JScrollPane();
		panel_2.setBounds(10, 11, 196, 799);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setBounds(255, 200, 89, 14);
		panel.add(lblNom);
		
		JLabel lblPrenom = new JLabel("Prenom");
		lblPrenom.setBounds(255, 250, 89, 14);
		panel.add(lblPrenom);
		
		JLabel lblId = new JLabel("Identifiant");
		lblId.setBounds(255, 300, 89, 14);
		panel.add(lblId);
		
		JLabel lblMDP = new JLabel("Mot de passe");
		lblMDP.setBounds(255, 350, 89, 14);
		panel.add(lblMDP);

		txtNom = new JTextField();
		txtNom.setColumns(10);
		txtNom.setBounds(400, 197, 180, 20);
		txtNom.setEditable(false);
		panel.add(txtNom);
		
		txtPrenom = new JTextField();
		txtPrenom.setColumns(10);
		txtPrenom.setBounds(400, 247, 180, 20);
		txtPrenom.setEditable(false);
		panel.add(txtPrenom);
		
		txtID = new JTextField();
		txtID.setColumns(10);
		txtID.setBounds(400, 297, 180, 20);
		txtID.setEditable(false);
		panel.add(txtID);
		
		txtMDP = new JTextField();
		txtMDP.setColumns(10);
		txtMDP.setBounds(400, 347, 180, 20);
		txtMDP.setEditable(false);
		panel.add(txtMDP);
		
		//Boutons Utilisateurs
		for (this.i = 0; this.i < users.size(); this.i++){
			btnUsers.add(new JButton(this.users.get(i).getId()));
			btnUsers.get(i).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JButton boutonChoisi = (JButton) e.getSource();
//					System.out.println("On est la");
//					for (Utilisateur u : users){
//						for (int j = 0; j < u.getId().length()-1; j++){
//							System.out.print(u.getId().toCharArray()[j]);
//							System.out.print(" ");
//						}
//						System.out.println();
//					}
//
//					
//					for (JButton button : btnUsers){
//						for (int j = 0; j < button.getText().length()-1; j++){
//							System.out.print(button.getText().toCharArray()[j]);
//							System.out.print(" ");
//						}
//						System.out.println();
//					}
					
					boolean trouve = false;
					int j = 0;
					while(!trouve && j < users.size()){
						
//						System.out.println("j : "+j+"; users.get(j).getId() : "+users.get(j).getId()+"; boutonChoisi.getText() : "+boutonChoisi.getText()+";");
						System.out.println("J : "+j+" Test : "+(boutonChoisi.getText() == users.get(j).getId()));
						if (boutonChoisi.getText() == users.get(j).getId()){
							userChoisi = users.get(j);
							trouve = true;
						}
						j++;
					}
					System.out.println(trouve);
					System.out.println();

					if (trouve){
						txtNom.setText(userChoisi.getNom());
						txtNom.setEditable(true);
						txtPrenom.setText(userChoisi.getPrenom());
						txtPrenom.setEditable(true);
						txtID.setText(userChoisi.getId());
						txtID.setEditable(true);
						txtMDP.setText(userChoisi.getMotDePasse());
						txtMDP.setEditable(true);
					}
				}
			});
			btnUsers.get(i).setBounds(10, 11+(i*34), 176, 23);
			panel_2.add(btnUsers.get(i));
		}
				
		//Bouton supprimer
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			//TODO Popup confirmation
			public void actionPerformed(ActionEvent e) {
				txtNom.setText("");
				txtPrenom.setText("");
				txtID.setText("");
				txtMDP.setText("");
				users.remove(userChoisi);
				JButton btnUtilisateur = null;
				for (JButton button : btnUsers){
					if (button.getText() == userChoisi.getId()){
						btnUtilisateur = button;
					}
					panel_2.remove(button);
				}
				panel_2.revalidate();
				panel_2.repaint();
				btnUsers.remove(btnUtilisateur);
				
				for (i = 0; i < btnUsers.size(); i++){
					JButton b = btnUsers.get(i);
					b.setBounds(10, 11+(i*34), 176, 23);
					panel_2.add(b);
				}
			}
		});
		btnSupprimer.setBounds(255, 741, 101, 23);
		panel.add(btnSupprimer);
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Unicité id
				if (txtNom.getText().isEmpty() || txtPrenom.getText().isEmpty() || txtID.getText().isEmpty() || txtMDP.getText().isEmpty()){
					//TODO Popup
				} else {
					String ancienID = userChoisi.getId();
					for (Utilisateur u : users){
						if(u.getId() == ancienID){
							u.setId(txtID.getText());
							u.setMotDePasse(txtMDP.getText());
							u.setNom(txtNom.getText());
							u.setPrenom(txtPrenom.getText());
						}
					}							
					//Trouver et modifier le texte du bouton de l'utilisateur modifié
					for (JButton button : btnUsers){
						if (button.getText() == ancienID){
							button.setText(txtID.getText());
						}
					}
				}
			}
		});
		btnModifier.setBounds(577, 741, 89, 23);
		panel.add(btnModifier);
		
		JButton btnCreer = new JButton("Creer");
		btnCreer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtNom.getText().isEmpty() || txtPrenom.getText().isEmpty() || txtID.getText().isEmpty() || txtMDP.getText().isEmpty()){
					//TODO Popup
				} else {
					Utilisateur nouvelUtilisateur = new Utilisateur(txtID.getText(), txtMDP.getText(), txtNom.getText(), txtPrenom.getText(), TypeUtilisateur.CAMPUS);
					JButton btnNouvelUtilisateur = new JButton(nouvelUtilisateur.getId());
					btnNouvelUtilisateur.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							JButton boutonChoisi = (JButton) e.getSource();
							boolean trouve = false;
							int j = 0;
							while(!trouve || j > users.size()){
								if (boutonChoisi.getText() == users.get(j).getId()){
									userChoisi = users.get(j);
									trouve = true;
								}
								j++;
							}
							txtNom.setText(userChoisi.getNom());
							txtPrenom.setText(userChoisi.getPrenom());
							txtID.setText(userChoisi.getId());
							txtMDP.setText(userChoisi.getMotDePasse());
						}
					});
					
					btnNouvelUtilisateur.setBounds(10, 11+(users.size()*34), 176, 23);
					btnUsers.add(btnNouvelUtilisateur);
					users.add(nouvelUtilisateur);
					panel_2.add(btnNouvelUtilisateur);
					panel_2.revalidate();
					panel_2.repaint();
				}
			}
		});
		btnCreer.setBounds(919, 741, 89, 23);
		panel.add(btnCreer);
			
//		JScrollPane panelPane = new JScrollPane(panel_2);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Groupe", panel_1);
	}
}