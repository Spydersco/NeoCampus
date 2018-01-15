package InterfaceGraphique;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Color;
import reseau.SocketClient;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class InterfaceLogin extends JFrame {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	private final JButton btnSeConnecter = new JButton("Se Connecter");
	private JTextField iD;
	private JTextField MotDePasse;
	private JLabel lblID;
	private JLabel lblMotDePasse;

	public InterfaceLogin(SocketClient socket) {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Determine la couleur du fond d'écran
		JPanel pan1 = new JPanel();
		pan1.setBackground(Color.WHITE);
		this.setContentPane(pan1);
		getContentPane().setLayout(null);

		// Libellé du iD
		lblID = new JLabel("iD");
		lblID.setBounds(10, 55, 153, 14);
		getContentPane().add(lblID);

		// Champ de saisie du iD
		iD = new JTextField();
		iD.setText("0");
		iD.setColumns(10);
		iD.setBounds(258, 55, 140, 20);
		getContentPane().add(iD);

		// Libellé du mot de passe
		lblMotDePasse = new JLabel("Mot de passe");
		lblMotDePasse.setBounds(10, 120, 153, 14);
		getContentPane().add(lblMotDePasse);

		// Champ de saisie du mot de passe
		MotDePasse = new JPasswordField();
		MotDePasse.setText("admin");
		MotDePasse.setColumns(10);
		MotDePasse.setBounds(258, 117, 140, 20);
		getContentPane().add(MotDePasse);

		// Bouton servant à se connecter, une fois les champs remplis
		btnSeConnecter.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		btnSeConnecter.setAlignmentX(Component.RIGHT_ALIGNMENT);
		btnSeConnecter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (socket.seConnecter(iD.getText(), MotDePasse.getText())) {
					dispose();
					new InterfaceChat(socket);
				} 
				else {
					btnSeConnecter.setText("Reesayer");
				}
			}
		});
		btnSeConnecter.setBounds(218, 200, 206, 39);
		getContentPane().add(btnSeConnecter);
		
	}

}