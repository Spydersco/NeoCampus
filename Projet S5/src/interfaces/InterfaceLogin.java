package InterfaceGraphique;
import javax.swing.JFrame;
import java.awt.Image;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.Image;
import java.awt.ComponentOrientation;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;

public class InterfaceLogin extends JFrame {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	private final JButton btnSeConnecter = new JButton("Se Connecter");
	private JTextField Prenom;
	private JTextField Nom;
	private JTextField MotDePasse;
	private JLabel lblPrenom;
	private JLabel lblNom;
	private JLabel lblMotDePasse;
	private JLabel lblimg;
	private JLabel lblJeton;
	private JLabel lblImage;

	public InterfaceLogin() {

		
		// Determine la couleur du fond d'écran
		JPanel pan1 = new JPanel();
		pan1.setBackground(Color.WHITE);
		this.setContentPane(pan1);
		getContentPane().setLayout(null);
		
		
		// Libellé du nom
		lblNom = new JLabel("Nom");
		lblNom.setBounds(10, 55, 153, 14);
		getContentPane().add(lblNom);
		
				
		// Champ de saisie du nom
		Nom = new JTextField();
		Nom.setText("Paul");
		Nom.setColumns(10);
		Nom.setBounds(258, 55, 140, 20);
		getContentPane().add(Nom);
		
		// Libellé du montant du prenom
			lblPrenom = new JLabel("Pr\u00E9nom");
			lblPrenom.setAlignmentY(Component.BOTTOM_ALIGNMENT);
			lblPrenom.setBounds(10, 89, 185, 14);
			getContentPane().add(lblPrenom);

		// Champ de saisie du prenom
		Prenom = new JTextField();
		Prenom.setText("2000");
		Prenom.setBounds(258, 86, 140, 20);
		getContentPane().add(Prenom);
		Prenom.setColumns(10);
		
		// Libellé du mot de passe
		lblMotDePasse = new JLabel("Mot de passe");
		lblMotDePasse.setBounds(10, 120, 153, 14);
		getContentPane().add(lblMotDePasse);

		// Champ de saisie du mot de passe
		MotDePasse = new JTextField();
		MotDePasse.setText("Henry");
		MotDePasse.setColumns(10);
		MotDePasse.setBounds(258, 117, 140, 20);
		getContentPane().add(MotDePasse);


		
		// Bouton servant à se connecter, une fois les champs remplis
				btnSeConnecter.setAlignmentY(Component.BOTTOM_ALIGNMENT);
				btnSeConnecter.setAlignmentX(Component.RIGHT_ALIGNMENT);
				btnSeConnecter.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						InterfaceClient frame = new InterfaceClient();

						frame.setSize(1400, 1000);
						frame.setVisible(true);
						frame.setResizable(false);
						
					
						dispose();
						

					}
				});
				btnSeConnecter.setBounds(218, 200, 206, 39);
				getContentPane().add(btnSeConnecter);

				
	    

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}