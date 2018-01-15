package interfaces;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

public class Serveur {
	private JFrame frame;
	private String retour = "";


	private List<JButton> btnUsers = new ArrayList<>();
	private JTextField txtNomUtilisateur;
	private JTextField txtPrenomUtilisateur;
	private JTextField txtIDUtilisateur;
	private JTextField txtMDPUtilisateur;
	private JButton btnModifierUtilisateur = new JButton("Modifier");
	private JButton btnSupprimerUtilisateur = new JButton("Supprimer");
	private JButton btnCreerUtilisateur = new JButton("Ajouter");
	private int i;
	private String userChoisi;
	private String[] users;
	private JPanel panelListeUtilisateurs = new JPanel();


	private String[] groupes;
	private List<JButton> btnGroupes = new ArrayList<>();
	private List<JRadioButton> membresChoisis = new ArrayList<>();
	private String groupeChoisi;
	private List<JRadioButton> groupeBoutonMembre = new ArrayList<>();
	private List<JRadioButton> groupeBoutonUtilisateursNonPresent = new ArrayList<>();
	private JButton btnModifierGroupe = new JButton("Modifier");
	private JButton btnSupprimerGroupe = new JButton("Supprimer");
	private JButton btnCreerGroupe = new JButton("Ajouter");
	private JButton btnSupprimerMembres = new JButton("Supprimer ");
	private JButton btnAjouterMembres = new JButton("Ajouter");
	private JTextField txtNomGroupe;
	private JPanel panelListeMembres;
	private JPanel panelListeUtilisateursNonPresent;



	public Serveur() throws Exception{

		initialize();
	}


    
	
	public void raz(){
		txtNomUtilisateur.setText("");
		txtNomUtilisateur.setEditable(false);
		txtPrenomUtilisateur.setText("");
		txtPrenomUtilisateur.setEditable(false);
		txtIDUtilisateur.setText("");
		txtIDUtilisateur.setEditable(false);
		txtMDPUtilisateur.setText("");
		txtMDPUtilisateur.setEditable(false);
		btnModifierUtilisateur.setEnabled(false);
		btnSupprimerUtilisateur.setEnabled(false);
	}
	
	
	
	public void afficherBoutonsUtilisateur(){
		for (i = 0; i < btnUsers.size(); i++){
			JButton b = btnUsers.get(i);
			b.setMaximumSize(new Dimension(180, 25));
			b.setMinimumSize(new Dimension(180, 25));
			b.setPreferredSize(new Dimension(180, 25));
			b.setAlignmentX(Component.CENTER_ALIGNMENT);
			panelListeUtilisateurs.add(b);
		}
	}
	
	

	public void ajouterBoutonGroupe(ActionEvent e){
		txtNomGroupe.setText(((JButton)e.getSource()).getText());
		btnModifierGroupe.setEnabled(true);
		btnSupprimerGroupe.setEnabled(true);
		btnAjouterMembres.setEnabled(true);
		btnSupprimerMembres.setEnabled(true);
		

		for (JRadioButton radio : groupeBoutonMembre){
			panelListeMembres.remove(radio);
		}
		
		for (JRadioButton radio : groupeBoutonUtilisateursNonPresent){
			panelListeUtilisateursNonPresent.remove(radio);
		}
		panelListeMembres.repaint();
		panelListeUtilisateursNonPresent.repaint();
		
		JButton boutonChoisi = (JButton) e.getSource();			
		boolean trouve = false;
		int j = 0;
		while(!trouve && j < groupes.length){
			if (boutonChoisi.getText().compareTo(groupes[j])==0){
				groupeChoisi = groupes[j];
				trouve = true;
			}
			j++;
		}
		
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(1200, 900, 1200, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);

		JTabbedPane onglets = new JTabbedPane(JTabbedPane.TOP);
		onglets.setBounds(10, 11, 1174, 849);
		frame.getContentPane().add(onglets);
		
	
		JPanel panelUtilisateur = new JPanel();
		panelUtilisateur.setBorder(BorderFactory.createLineBorder(Color.black));
		onglets.addTab("Utilisateur", panelUtilisateur);
		panelUtilisateur.setLayout(null);

		panelListeUtilisateurs.setBorder(BorderFactory.createLineBorder(Color.black));
		panelListeUtilisateurs.setLayout(new BoxLayout(panelListeUtilisateurs, 1));
		

		JLabel lblNomUtilisateur = new JLabel("Nom");
		lblNomUtilisateur.setBounds(347, 125, 90, 15);
		txtNomUtilisateur = new JTextField();
		txtNomUtilisateur.setBounds(272, 151, 180, 20);
		txtNomUtilisateur.setEditable(false);
		panelUtilisateur.add(lblNomUtilisateur);
		panelUtilisateur.add(txtNomUtilisateur);

		JLabel lblPrenomUtilisateur = new JLabel("Prenom");
		lblPrenomUtilisateur.setBounds(545, 125, 90, 15);
		txtPrenomUtilisateur = new JTextField();
		txtPrenomUtilisateur.setBounds(487, 151, 180, 20);
		txtPrenomUtilisateur.setEditable(false);
		panelUtilisateur.add(lblPrenomUtilisateur);
		panelUtilisateur.add(txtPrenomUtilisateur);
		
		JLabel lblIdUtilisateur = new JLabel("Identifiant");
		lblIdUtilisateur.setBounds(331, 196, 90, 15);
		txtIDUtilisateur = new JTextField();
		txtIDUtilisateur.setBounds(272, 222, 180, 20);
		txtIDUtilisateur.setEditable(false);
		panelUtilisateur.add(lblIdUtilisateur);
		panelUtilisateur.add(txtIDUtilisateur);
		
		JLabel lblMDPUtilisateur = new JLabel("Mot de passe");
		lblMDPUtilisateur.setBounds(533, 196, 90, 15);
		txtMDPUtilisateur = new JTextField();
		txtMDPUtilisateur.setBounds(487, 222, 180, 20);
		txtMDPUtilisateur.setEditable(false);
		panelUtilisateur.add(lblMDPUtilisateur);
		panelUtilisateur.add(txtMDPUtilisateur);
				
		btnSupprimerUtilisateur.setEnabled(false);
		btnSupprimerUtilisateur.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnSupprimerUtilisateur.setBounds(272, 394, 101, 23);
		panelUtilisateur.add(btnSupprimerUtilisateur);
		
		btnModifierUtilisateur.setEnabled(false);
		btnModifierUtilisateur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnModifierUtilisateur.setBounds(272, 360, 89, 23);
		panelUtilisateur.add(btnModifierUtilisateur);
		
		JScrollPane scrollUtilisateurs = new JScrollPane(panelListeUtilisateurs, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollUtilisateurs.setBounds(10, 11, 230, 754);
		panelUtilisateur.add(scrollUtilisateurs);

		btnCreerUtilisateur.setEnabled(false);
		btnCreerUtilisateur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
			}
		});
		btnCreerUtilisateur.setBounds(272, 326, 89, 23);
		panelUtilisateur.add(btnCreerUtilisateur);
			
				
		
		
		JPanel panelGroupe = new JPanel();
		panelGroupe.setBorder(BorderFactory.createLineBorder(Color.black));
		onglets.addTab("Groupe", panelGroupe);
		panelGroupe.setLayout(null);
		
		JPanel panelListeGroupes = new JPanel();
		panelListeGroupes.setBorder(BorderFactory.createLineBorder(Color.black));
		panelListeGroupes.setBounds(10, 10, 200, 760);
		panelGroupe.add(panelListeGroupes);
		panelListeGroupes.setLayout(null);
	
		JLabel lblNomDuGroupe = new JLabel("Nom du groupe");
		lblNomDuGroupe.setBounds(320, 70, 90, 15);
		panelGroupe.add(lblNomDuGroupe);
		txtNomGroupe = new JTextField();
		txtNomGroupe.setEditable(false);
		txtNomGroupe.setBounds(470, 70, 180, 20);
		panelGroupe.add(txtNomGroupe);

		JPanel panelMembresPresent = new JPanel();
		panelMembresPresent.setBorder(BorderFactory.createLineBorder(Color.black));
		panelMembresPresent.setBounds(250, 140, 450, 254);
		panelGroupe.add(panelMembresPresent);
		panelMembresPresent.setLayout(null);
		
		JLabel lblMembres = new JLabel("Ensembles des membres");
		lblMembres.setHorizontalAlignment(SwingConstants.CENTER);
		lblMembres.setBounds(25, 10, 240, 15);
		panelMembresPresent.add(lblMembres);
			
		panelListeMembres = new JPanel();
		panelListeMembres.setBorder(BorderFactory.createLineBorder(Color.black));
		panelListeMembres.setBounds(30, 60, 240, 177);
		panelMembresPresent.add(panelListeMembres);
		panelListeMembres.setLayout(null);

		btnSupprimerGroupe.setEnabled(false);
		btnSupprimerGroupe.setBounds(953, 67, 100, 20);
		btnSupprimerGroupe.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			
			}
		});
		panelGroupe.add(btnSupprimerGroupe);

		
		btnModifierGroupe.setEnabled(false);
		btnModifierGroupe.setBounds(830, 67, 100, 20);
		btnModifierGroupe.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			
			}
		});
		panelGroupe.add(btnModifierGroupe);

	
		btnCreerGroupe.setEnabled(false);
		btnCreerGroupe.setBounds(705, 67, 100, 20);
		btnCreerGroupe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		panelGroupe.add(btnCreerGroupe);
		
		if (!(groupes[0].equals(""))){
			for (this.i = 0; this.i < groupes.length; this.i++){		
				btnGroupes.add(new JButton(this.groupes[i]));
				btnGroupes.get(i).addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ajouterBoutonGroupe(e);
					}
				});
				btnGroupes.get(i).setBounds(10, 11+(i*35), 90, 20);
				panelListeGroupes.add(btnGroupes.get(i));
			}
			btnSupprimerGroupe.setEnabled(true);
			btnModifierGroupe.setEnabled(true);
		}
		
		btnSupprimerMembres.setEnabled(false);
		btnSupprimerMembres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for(JRadioButton radio : groupeBoutonMembre){
					if(radio.isSelected()){
						membresChoisis.add(radio);
						radio.setSelected(false);
					}
				}

				if (!membresChoisis.isEmpty()){	
					
					for (JRadioButton radio : groupeBoutonMembre){
						panelListeMembres.remove(radio);
					}
					
					for (JRadioButton radio : membresChoisis){
						groupeBoutonMembre.remove(radio);
						groupeBoutonUtilisateursNonPresent.add(radio);
					}
							
					for (int k = 0; k < groupeBoutonMembre.size(); k++){
						groupeBoutonMembre.get(k).setBounds(6, 5+(k*30), 160, 20);
						panelListeMembres.add(groupeBoutonMembre.get(k));
					}
					
					for (int k = groupeBoutonUtilisateursNonPresent.size()-membresChoisis.size(); k < groupeBoutonUtilisateursNonPresent.size(); k++){
						groupeBoutonUtilisateursNonPresent.get(k).setBounds(6, 5+(k*30), 110, 20);
						panelListeUtilisateursNonPresent.add(groupeBoutonUtilisateursNonPresent.get(k));
					}
					membresChoisis.clear();
					panelListeUtilisateursNonPresent.repaint();
					panelMembresPresent.repaint();
				}
			}
		});
		btnSupprimerMembres.setBounds(280, 71, 135, 66);
		panelMembresPresent.add(btnSupprimerMembres);
		
		JPanel panelUtilisateurNonPresent = new JPanel();
		panelUtilisateurNonPresent.setBounds(250, 412, 450, 254);
		panelGroupe.add(panelUtilisateurNonPresent);
		panelUtilisateurNonPresent.setBorder(BorderFactory.createLineBorder(Color.black));
		panelUtilisateurNonPresent.setLayout(null);
		
		JLabel lblUtilisateurs = new JLabel("Ensemble des utilisateurs");
		lblUtilisateurs.setHorizontalAlignment(SwingConstants.CENTER);
		lblUtilisateurs.setBounds(30, 10, 240, 20);
		panelUtilisateurNonPresent.add(lblUtilisateurs);
		
		panelListeUtilisateursNonPresent = new JPanel();
		panelListeUtilisateursNonPresent.setBorder(BorderFactory.createLineBorder(Color.black));
		panelListeUtilisateursNonPresent.setBounds(30, 60, 240, 177);
		panelUtilisateurNonPresent.add(panelListeUtilisateursNonPresent);
		panelListeUtilisateursNonPresent.setLayout(null);		
		
		
		btnAjouterMembres.setEnabled(false);
		btnAjouterMembres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
			}
		});
		btnAjouterMembres.setBounds(280, 78, 146, 71);
		panelUtilisateurNonPresent.add(btnAjouterMembres);		
	}
}