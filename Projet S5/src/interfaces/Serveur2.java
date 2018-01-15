package interfaces;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import baseDeDonnees.GestionBaseDeDonnee;
import utilisateurs.Groupe;
import utilisateurs.TypeUtilisateur;
import utilisateurs.Utilisateur;

//import com.mysql.jdbc.PreparedStatement;
import javax.swing.JComboBox;

public class Serveur2 {
	private JFrame frame;

	// SQL
	private Connection connect = null;

	// Variables pour l'onglet utilisateur
	private List<JButton> btnUsers = new ArrayList<>();
	private JTextField txtNomUtilisateur;
	private JTextField txtPrenomUtilisateur;
	private JTextField txtIDUtilisateur;
	private JTextField txtMDPUtilisateur;
	private JComboBox<?> typeUtilisateur;
	private JButton btnModifierUtilisateur = new JButton("Modifier");
	private JButton btnSupprimerUtilisateur = new JButton("Supprimer");
	private JButton btnAjouterUtilisateur = new JButton("Ajouter");
	private int i;
	private Utilisateur userChoisi;
	private List<Utilisateur> users;
	private JPanel panelListeUtilisateurs = new JPanel();

	// Variables pour l'onglet groupe
	private List<Groupe> groupes;
	private List<JButton> btnGroupes = new ArrayList<>();
	private List<JRadioButton> membresChoisis = new ArrayList<>();
	private Groupe groupeChoisi;
	private List<JRadioButton> groupeBoutonMembre = new ArrayList<>();
	private List<JRadioButton> groupeBoutonUtilisateursNonPresent = new ArrayList<>();
	private JButton btnModifierGroupe = new JButton("Modifier");
	private JButton btnSupprimerGroupe = new JButton("Supprimer");
	private JButton btnAjouterGroupe = new JButton("Ajouter");
	private JButton btnSupprimerMembres = new JButton("Supprimer ");
	private JButton btnAjouterMembres = new JButton("Ajouter ");
	private JTextField txtNomGroupe;
	private JPanel panelListeMembres;
	private JPanel panelListeUtilisateursNonPresent;

	/**
	 * @wbp.parser.entryPoint
	 */
	public Serveur2() throws SQLException {
		Connection connexion = null;
		try {
			Class.forName("org.hsqldb.jdbcDriver").newInstance();
			String url = "jdbc:hsqldb:file:NeoCampus";
			String user = "sa";
			String passwd = "";
			connexion = (Connection) DriverManager.getConnection(url, user, passwd);

		} catch (Exception e) {
			e.printStackTrace();
		}

		initialize();
		Statement statement = connexion.createStatement();
		statement.executeQuery("SHUTDOWN");
		statement.close();
	}

	public void raz() {
		txtNomUtilisateur.setText("");
		txtNomUtilisateur.setEditable(false);
		txtPrenomUtilisateur.setText("");
		txtPrenomUtilisateur.setEditable(false);
		txtMDPUtilisateur.setText("");
		txtMDPUtilisateur.setEditable(false);
		typeUtilisateur.setSelectedItem(0);
		typeUtilisateur.setEditable(false);
		typeUtilisateur.repaint();
		btnModifierUtilisateur.setEnabled(false);
		btnSupprimerUtilisateur.setEnabled(false);
	}

	public void ajouterBoutonUtilisateur(ActionEvent e) {
		JButton boutonChoisi = (JButton) e.getSource();
		boolean trouve = false;
		Iterator<Utilisateur> iterateur = users.iterator();
		while (!trouve && iterateur.hasNext()) {
			Utilisateur user = iterateur.next();
			if (boutonChoisi.getText().compareTo(user.getPrenom() + "" + user.getNom()) == 0) {
				userChoisi = user;
				trouve = true;
			}
		}
		if (trouve) {
			txtMDPUtilisateur.setText(userChoisi.getMotDePasse());
			txtMDPUtilisateur.setEditable(true);
			txtNomUtilisateur.setText(userChoisi.getNom());
			txtNomUtilisateur.setEditable(true);
			txtPrenomUtilisateur.setText(userChoisi.getPrenom());
			txtPrenomUtilisateur.setEditable(true);
			typeUtilisateur.setSelectedIndex(userChoisi.getType().ordinal());
		}
		btnModifierUtilisateur.setEnabled(true);
		btnSupprimerUtilisateur.setEnabled(true);
		typeUtilisateur.setEnabled(true);
	}

	public void afficherBoutonsUtilisateur() {
		for (i = 0; i < btnUsers.size(); i++) {
			JButton b = btnUsers.get(i);
			b.setMaximumSize(new Dimension(180, 25));
			b.setMinimumSize(new Dimension(180, 25));
			b.setPreferredSize(new Dimension(180, 25));
			b.setAlignmentX(Component.CENTER_ALIGNMENT);
			panelListeUtilisateurs.add(b);
		}
	}

	public void ajouterBoutonGroupe(ActionEvent e) {
		txtNomGroupe.setText(((JButton) e.getSource()).getText());
		btnModifierGroupe.setEnabled(true);
		btnSupprimerGroupe.setEnabled(true);
		btnAjouterMembres.setEnabled(true);
		btnSupprimerMembres.setEnabled(true);

		// Enlever les éventuels radios boutons
		for (JRadioButton radio : groupeBoutonMembre) {
			panelListeMembres.remove(radio);
		}

		for (JRadioButton radio : groupeBoutonUtilisateursNonPresent) {
			panelListeUtilisateursNonPresent.remove(radio);
		}
		panelListeMembres.repaint();
		panelListeUtilisateursNonPresent.repaint();

		JButton boutonChoisi = (JButton) e.getSource();
		boolean trouve = false;
		GestionBaseDeDonnee gbd = new GestionBaseDeDonnee(connect);
		Iterator<Groupe> iterateur = groupes.iterator();
		while (!trouve && iterateur.hasNext()) {
			Groupe groupe = iterateur.next();
			if (boutonChoisi.getText().compareTo(groupe.getNom()) == 0) {
				groupeChoisi = groupe;
				trouve = true;
			}
		}
		if (trouve) {

			List<Utilisateur> listeMembres = gbd.listeMembres(groupeChoisi);

			groupeBoutonMembre.clear();
			int k = 0;
			if (!listeMembres.isEmpty()) {
				for (Utilisateur u : listeMembres) {
					JRadioButton rdbtnNewRadioButton = new JRadioButton(u.getNom() + " " + u.getPrenom());
					groupeBoutonMembre.add(rdbtnNewRadioButton);
					rdbtnNewRadioButton.setBounds(6, 5 + (k * 30), 160, 20);
					panelListeMembres.add(rdbtnNewRadioButton);
					k++;
				}
			}
			List<Utilisateur> utilisateursNonPresent = new ArrayList<>();
			utilisateursNonPresent.addAll(users);
			utilisateursNonPresent.removeAll(listeMembres);

			groupeBoutonUtilisateursNonPresent.clear();
			k = 0;
			for (Utilisateur u : utilisateursNonPresent) {
				JRadioButton rdbtnNewRadioButton = new JRadioButton(u.getPrenom() + " " + u.getNom());
				groupeBoutonUtilisateursNonPresent.add(rdbtnNewRadioButton);
				rdbtnNewRadioButton.setBounds(6, 5 + (k * 30), 110, 20);
				panelListeUtilisateursNonPresent.add(rdbtnNewRadioButton);
				k++;
			}
			panelListeMembres.repaint();
			panelListeUtilisateursNonPresent.repaint();
		}
	}

	private void initialize() {
		GestionBaseDeDonnee gbd = new GestionBaseDeDonnee(connect);
		users = gbd.getUtilisateurs();
		groupes = gbd.getGroupes();
		frame = new JFrame();
		frame.setBounds(1200, 900, 1200, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);

		// Creation du panel contenant les deux onglets (Utilisateur et groupe)
		JTabbedPane onglets = new JTabbedPane(JTabbedPane.TOP);
		onglets.setBounds(10, 11, 1174, 849);
		frame.getContentPane().add(onglets);

		// Panel permettant l'affichage de l'interface de l'onglet utilisateur
		JPanel panelUtilisateur = new JPanel();
		panelUtilisateur.setBorder(BorderFactory.createLineBorder(Color.black));
		onglets.addTab("Utilisateur", panelUtilisateur);
		panelUtilisateur.setLayout(null);

		// Panel contenant la liste des boutons utilisateur
		panelListeUtilisateurs.setBorder(BorderFactory.createLineBorder(Color.black));
		panelListeUtilisateurs.setLayout(new BoxLayout(panelListeUtilisateurs, 1));

		// Label et zone de saisie du champ Nom de l'utilisateur
		JLabel lblNomUtilisateur = new JLabel("Nom");
		lblNomUtilisateur.setBounds(400, 171, 90, 15);
		txtNomUtilisateur = new JTextField();
		txtNomUtilisateur.setBounds(329, 197, 180, 20);
		txtNomUtilisateur.setEditable(false);
		panelUtilisateur.add(lblNomUtilisateur);
		panelUtilisateur.add(txtNomUtilisateur);

		// Label et zone de saisie du champ Prenom de l'utilisateur
		JLabel lblPrenomUtilisateur = new JLabel("Prenom");
		lblPrenomUtilisateur.setBounds(577, 171, 90, 15);
		txtPrenomUtilisateur = new JTextField();
		txtPrenomUtilisateur.setBounds(519, 197, 180, 20);
		txtPrenomUtilisateur.setEditable(false);
		panelUtilisateur.add(lblPrenomUtilisateur);
		panelUtilisateur.add(txtPrenomUtilisateur);

		// Label et zone de saisie du champ Id de l'utilisateur
		JLabel lblIdUtilisateur = new JLabel("Identifiant");
		lblIdUtilisateur.setBounds(381, 228, 90, 15);
		txtIDUtilisateur = new JTextField();
		txtIDUtilisateur.setBounds(329, 243, 180, 20);
		txtIDUtilisateur.setEditable(false);
		panelUtilisateur.add(lblIdUtilisateur);
		panelUtilisateur.add(txtIDUtilisateur);

		// Label et zone de saisie du champ Mot de passe de l'utilisateur
		JLabel lblMDPUtilisateur = new JLabel("Mot de passe");
		lblMDPUtilisateur.setBounds(577, 228, 90, 15);
		txtMDPUtilisateur = new JTextField();
		txtMDPUtilisateur.setBounds(519, 243, 180, 20);
		txtMDPUtilisateur.setEditable(false);
		panelUtilisateur.add(lblMDPUtilisateur);
		panelUtilisateur.add(txtMDPUtilisateur);

		// Label et zone de saisie du type d'utilisateur
		JLabel lblTypeUtilisateur = new JLabel("Type utilisateur");
		lblTypeUtilisateur.setBounds(474, 272, 89, 14);
		panelUtilisateur.add(lblTypeUtilisateur);
		String[] types = { "", TypeUtilisateur.AGENT.name(), TypeUtilisateur.UTILISATEUR.name() };
		typeUtilisateur = new JComboBox<String>(types);
		typeUtilisateur.setEnabled(false);
		typeUtilisateur.setBounds(419, 297, 180, 20);
		panelUtilisateur.add(typeUtilisateur);

		// Bouton supprimer utilisateur
		btnSupprimerUtilisateur.setEnabled(false);
		btnSupprimerUtilisateur.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				raz();
				JButton btnUtilisateur = null;
				for (JButton button : btnUsers) {
					if (button.getText().equals(userChoisi.getPrenom() + " " + userChoisi.getNom())) {
						btnUtilisateur = button;
					}
					panelListeUtilisateurs.remove(button);
				}
				panelListeUtilisateurs.repaint();
				btnUsers.remove(btnUtilisateur);
				gbd.getUtilisateurDAO().delete(gbd.getUtilisateurDAO().find(userChoisi.getId()));
				afficherBoutonsUtilisateur();
			}
		});
		btnSupprimerUtilisateur.setBounds(264, 440, 101, 23);
		panelUtilisateur.add(btnSupprimerUtilisateur);

		// Bouton Modifier Utilisateur
		btnModifierUtilisateur.setEnabled(false);
		btnModifierUtilisateur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(txtNomUtilisateur.getText().isEmpty() || txtPrenomUtilisateur.getText().isEmpty()
						|| txtIDUtilisateur.getText().isEmpty() || txtMDPUtilisateur.getText().isEmpty()
						|| (typeUtilisateur.getSelectedIndex() == 0))) {
					gbd.getUtilisateurDAO()
							.update(new Utilisateur(userChoisi.getId(), txtNomUtilisateur.getText(),
									txtPrenomUtilisateur.getText(), txtMDPUtilisateur.getText(),
									TypeUtilisateur.valueOf(typeUtilisateur.getSelectedItem().toString())));
				}
			}
		});

		btnModifierUtilisateur.setBounds(264, 406, 89, 23);
		panelUtilisateur.add(btnModifierUtilisateur);

		// Création des boutons Utilisateurs
		for (Iterator<Utilisateur> iterator = users.iterator(); iterator.hasNext();) {
			Utilisateur user = iterator.next();
			btnUsers.add(new JButton(user.getPrenom() + " " + user.getNom()));
			btnUsers.get(i).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ajouterBoutonUtilisateur(e);
				}
			});
		}
		afficherBoutonsUtilisateur();

		// Ajout de la scroll bar
		JScrollPane scrollUtilisateurs = new JScrollPane(panelListeUtilisateurs,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollUtilisateurs.setBounds(10, 11, 230, 754);
		panelUtilisateur.add(scrollUtilisateurs);

		// Bouton Creer Utilisateur
		btnAjouterUtilisateur.setEnabled(false);
		btnAjouterUtilisateur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtNomUtilisateur.getText().isEmpty() || txtPrenomUtilisateur.getText().isEmpty()
						|| txtMDPUtilisateur.getText().isEmpty() || (typeUtilisateur.getSelectedIndex() == 0)) {
				} else {
					Utilisateur nouvelUtilisateur = new Utilisateur(gbd.idUtilisateurSuivant(),
							txtNomUtilisateur.getText(), txtPrenomUtilisateur.getText(), txtMDPUtilisateur.getText(),
							TypeUtilisateur.valueOf(typeUtilisateur.getSelectedItem().toString()));
					gbd.getUtilisateurDAO().create(nouvelUtilisateur);
					JButton boutonNouvelUtilisateur = new JButton(
							txtPrenomUtilisateur.getText() + " " + txtNomUtilisateur.getText());
					raz();
					boutonNouvelUtilisateur.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							ajouterBoutonUtilisateur(e);
						}
					});
					boutonNouvelUtilisateur.setMaximumSize(new Dimension(180, 25));
					boutonNouvelUtilisateur.setMinimumSize(new Dimension(180, 25));
					boutonNouvelUtilisateur.setPreferredSize(new Dimension(180, 25));
					boutonNouvelUtilisateur.setAlignmentX(Component.CENTER_ALIGNMENT);
					btnUsers.add(boutonNouvelUtilisateur);
					panelListeUtilisateurs.add(boutonNouvelUtilisateur);

					panelListeUtilisateurs.repaint();
					btnAjouterUtilisateur.setEnabled(false);
				}
			}
		});
		btnAjouterUtilisateur.setBounds(264, 372, 89, 23);
		panelUtilisateur.add(btnAjouterUtilisateur);

		// Panel permettant l'affichage de l'interface de l'onglet Groupe
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
		panelMembresPresent.setBounds(250, 140, 450, 222);
		panelGroupe.add(panelMembresPresent);
		panelMembresPresent.setLayout(null);

		JLabel lblMembres = new JLabel("Membres ");
		lblMembres.setHorizontalAlignment(SwingConstants.CENTER);
		lblMembres.setBounds(25, 10, 240, 15);
		panelMembresPresent.add(lblMembres);

		panelListeMembres = new JPanel();
		panelListeMembres.setBorder(BorderFactory.createLineBorder(Color.black));
		panelListeMembres.setBounds(30, 60, 240, 140);
		panelMembresPresent.add(panelListeMembres);
		panelListeMembres.setLayout(null);

		JPanel panelUtilisateurNonPresent = new JPanel();
		panelUtilisateurNonPresent.setBorder(BorderFactory.createLineBorder(Color.black));
		panelUtilisateurNonPresent.setBounds(250, 384, 450, 222);
		panelGroupe.add(panelUtilisateurNonPresent);
		panelUtilisateurNonPresent.setLayout(null);

		JLabel lblUtilisateurs = new JLabel("Utilisateurs");
		lblUtilisateurs.setHorizontalAlignment(SwingConstants.CENTER);
		lblUtilisateurs.setBounds(30, 10, 240, 20);
		panelUtilisateurNonPresent.add(lblUtilisateurs);

		panelListeUtilisateursNonPresent = new JPanel();
		panelListeUtilisateursNonPresent.setBorder(BorderFactory.createLineBorder(Color.black));
		panelListeUtilisateursNonPresent.setBounds(30, 60, 240, 140);
		panelUtilisateurNonPresent.add(panelListeUtilisateursNonPresent);
		panelListeUtilisateursNonPresent.setLayout(null);

		btnSupprimerGroupe.setEnabled(false);
		btnSupprimerGroupe.setBounds(684, 86, 100, 20);
		btnSupprimerGroupe.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (JRadioButton radio : groupeBoutonMembre) {
					panelListeMembres.remove(radio);
				}

				for (JRadioButton radio : groupeBoutonUtilisateursNonPresent) {
					panelListeUtilisateursNonPresent.remove(radio);
				}

				panelListeMembres.repaint();
				panelListeUtilisateursNonPresent.repaint();
				groupeBoutonMembre.clear();
				groupeBoutonUtilisateursNonPresent.clear();

				JButton selection = null;
				for (JButton button : btnGroupes) {
					if (button.getText().equals(txtNomGroupe.getText())) {
						selection = button;
					}
					panelListeGroupes.remove(button);
				}
				panelListeGroupes.repaint();
				btnGroupes.remove(selection);
				gbd.getGroupeDAO().delete(gbd.getGroupeDAO().find(groupeChoisi.getId()));

				for (int i = 0; i < btnGroupes.size(); i++) {
					btnGroupes.get(i).setBounds(10, 11 + (i * 34), 90, 23);
					panelListeGroupes.add(btnGroupes.get(i));
				}
				panelListeGroupes.repaint();

				txtNomGroupe.setText("");
				txtNomGroupe.setEditable(false);
				btnSupprimerGroupe.setEnabled(false);
				btnModifierGroupe.setEnabled(false);
			}
		});
		panelGroupe.add(btnSupprimerGroupe);

		btnModifierGroupe.setEnabled(false);
		btnModifierGroupe.setBounds(684, 55, 100, 20);
		btnModifierGroupe.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gbd.getGroupeDAO().update(new Groupe(groupeChoisi.getId(), txtNomGroupe.getText(),
						gbd.getGroupeDAO().find(groupeChoisi.getId()).getNbMembres()));
			}
		});
		panelGroupe.add(btnModifierGroupe);

		btnAjouterGroupe.setEnabled(false);
		btnAjouterGroupe.setBounds(684, 24, 100, 20);
		btnAjouterGroupe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!txtNomGroupe.getText().isEmpty()) {
					gbd.getGroupeDAO().create(new Groupe(gbd.idGroupeSuivant(), txtNomGroupe.getText(), 0));

					btnAjouterGroupe.setEnabled(false);
					JButton boutonNouveauGroupe = new JButton(txtNomGroupe.getText());
					boutonNouveauGroupe.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							ajouterBoutonGroupe(e);
						}
					});
					boutonNouveauGroupe.setBounds(10, 11 + ((groupes.size() - 1) * 35), 90, 20);
					btnGroupes.add(boutonNouveauGroupe);
					panelListeGroupes.add(boutonNouveauGroupe);
					panelListeGroupes.repaint();
				}
			}
		});
		panelGroupe.add(btnAjouterGroupe);
		
		for (Iterator<Groupe> iterateur = groupes.iterator(); iterateur.hasNext();) {
			Groupe groupe = iterateur.next();
			btnGroupes.add(new JButton(groupe.getNom()));
			btnGroupes.get(i).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ajouterBoutonGroupe(e);
				}
			});
			btnGroupes.get(i).setBounds(10, 11 + (i * 35), 90, 20);
			panelListeGroupes.add(btnGroupes.get(i));
		}
		btnSupprimerGroupe.setEnabled(true);
		btnModifierGroupe.setEnabled(true);

		btnSupprimerMembres.setEnabled(false);
		btnSupprimerMembres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (JRadioButton radio : groupeBoutonMembre) {
					if (radio.isSelected()) {
						membresChoisis.add(radio);
						radio.setSelected(false);
					}
				}

				if (!membresChoisis.isEmpty()) {

					for (JRadioButton radio : groupeBoutonMembre) {
						panelListeMembres.remove(radio);
					}

					for (JRadioButton radio : membresChoisis) {
						groupeBoutonMembre.remove(radio);
						groupeBoutonUtilisateursNonPresent.add(radio);
					}

					for (int k = 0; k < groupeBoutonMembre.size(); k++) {
						groupeBoutonMembre.get(k).setBounds(6, 5 + (k * 30), 160, 20);
						panelListeMembres.add(groupeBoutonMembre.get(k));
					}

					for (int k = groupeBoutonUtilisateursNonPresent.size()
							- membresChoisis.size(); k < groupeBoutonUtilisateursNonPresent.size(); k++) {
						groupeBoutonUtilisateursNonPresent.get(k).setBounds(6, 5 + (k * 30), 110, 20);
						panelListeUtilisateursNonPresent.add(groupeBoutonUtilisateursNonPresent.get(k));
					}
					membresChoisis.clear();
					panelListeUtilisateursNonPresent.repaint();
					panelMembresPresent.repaint();
				}
			}
		});
		btnSupprimerMembres.setBounds(299, 90, 123, 60);
		panelMembresPresent.add(btnSupprimerMembres);

		btnAjouterMembres.setEnabled(false);
		btnAjouterMembres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (JRadioButton radio : groupeBoutonUtilisateursNonPresent) {
					if (radio.isSelected()) {
						membresChoisis.add(radio);
						radio.setSelected(false);
					}
				}

				if (!membresChoisis.isEmpty()) {
					for (JRadioButton radio : groupeBoutonUtilisateursNonPresent) {
						panelListeUtilisateursNonPresent.remove(radio);
					}

					for (JRadioButton radio : membresChoisis) {
						groupeBoutonUtilisateursNonPresent.remove(radio);
						groupeBoutonMembre.add(radio);
					}

					for (int k = 0; k < groupeBoutonUtilisateursNonPresent.size(); k++) {
						groupeBoutonUtilisateursNonPresent.get(k).setBounds(6, 5 + (k * 30), 110, 20);
						panelListeUtilisateursNonPresent.add(groupeBoutonUtilisateursNonPresent.get(k));
					}

					for (int k = groupeBoutonMembre.size() - membresChoisis.size(); k < groupeBoutonMembre
							.size(); k++) {
						groupeBoutonMembre.get(k).setBounds(6, 5 + (k * 30), 160, 20);
						panelListeMembres.add(groupeBoutonMembre.get(k));
					}
					membresChoisis.clear();
					panelListeUtilisateursNonPresent.repaint();
					panelMembresPresent.repaint();
				}
			}
		});
		btnAjouterMembres.setBounds(299, 90, 123, 60);
		panelUtilisateurNonPresent.add(btnAjouterMembres);
	}
}
