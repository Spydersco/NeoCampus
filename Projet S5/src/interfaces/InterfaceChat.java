package interfaces;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import messages.Message;
import messages.StatutMessage;
import messages.Ticket;
import utilisateurs.Groupe;
import utilisateurs.TypeUtilisateur;
import utilisateurs.Utilisateur;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class InterfaceChat extends JFrame {
	private JTextField textField;
	private List<Groupe> listGroupes;
	private JPanel panelChat;
	private int idCreateurFil;
	private int idGroupe;
	private int idFil;
	private String [] messages; 
	private String [] auteurs; 
	private int [] idCreateurMessage;
	
	public InterfaceChat() {
		 listGroupes = new ArrayList<>();
	     Groupe groupe1 = new Groupe(1, "TP31", 0);
	     listGroupes.add(groupe1);
	     Groupe groupe2 = new Groupe(2, "TP32", 0);
	     listGroupes.add(groupe2);
	     Groupe groupe3 = new Groupe(3, "TP41", 0);
	     listGroupes.add(groupe3);
	         
	     Utilisateur Sebastien = new Utilisateur(1, "Niarfeix", "Sébastien", "1234", TypeUtilisateur.UTILISATEUR);
	     Utilisateur Joshua = new Utilisateur(2, "Salort", "Joshua", "1234", TypeUtilisateur.UTILISATEUR);
	     Utilisateur Damien = new Utilisateur(3, "Raufaste", "Damien", "1234", TypeUtilisateur.UTILISATEUR);
	        
	 
	     Message message1Groupe1 = new Message(1, "Probleme dans la salle U3 105", "27/01/08 - 10:01", Sebastien, StatutMessage.TOUS_LU, 1);
	     Ticket discussion1Groupe1 = new Ticket(1, "Probleme", 1, 1);
	     Message message2Groupe1 = new Message(2, "Gros probleme dans la salle U2 115", "27/01/08 - 10:08", Joshua, StatutMessage.TOUS_LU, 2);
	     Ticket discussion2Groupe1 = new Ticket(2, "Gros probleme",  2,1);
	     Message message3Groupe1 = new Message(3, "Aucun problème", "29/01/08 - 10:08", Damien, StatutMessage.TOUS_LU, 3);
	     Ticket discussion3Groupe1 = new Ticket(3, "Tout va bien", 3,1);        
	     groupe1.addTicket(discussion1Groupe1,discussion2Groupe1, discussion3Groupe1 );
	 

	        
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JTree tree = new JTree();
		GridBagConstraints gbc_tree = new GridBagConstraints();
		gbc_tree.gridwidth = 4;
		gbc_tree.gridheight = 8;
		gbc_tree.insets = new Insets(0, 0, 5, 5);
		gbc_tree.fill = GridBagConstraints.BOTH;
		gbc_tree.gridx = 0;
		gbc_tree.gridy = 0;
		getContentPane().add(tree, gbc_tree);
		
        DefaultMutableTreeNode groupes = new DefaultMutableTreeNode("Groupes");
         
        for(int i = 0; i < this.listGroupes.size(); i++){
            DefaultMutableTreeNode groupe = new DefaultMutableTreeNode(this.listGroupes.get(i).getNom());
            for (int j = 0; j < this.listGroupes.get(i).getTickets().size(); j++){
                DefaultMutableTreeNode groupeFils = new DefaultMutableTreeNode(this.listGroupes.get(i).getTickets().get(j).getTitre());
                groupe.add(groupeFils);
            }
            groupes.add(groupe);
        }
 
        tree = new JTree(groupes);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.CONTIGUOUS_TREE_SELECTION);
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {

			}
		});

		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 10;
		gbc_panel.gridheight = 8;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 4;
		gbc_panel.gridy = 0;
		getContentPane().add(panel, gbc_panel);
		
		panelChat = new JPanel();
		panelChat.setBorder(BorderFactory.createLineBorder(Color.black));
		panelChat.setLayout(new BoxLayout(panelChat, 1));
		JScrollPane scrollChat = new JScrollPane(panelChat, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollChat.setBounds(203, 40, 991, 720);
		getContentPane().add(scrollChat);
		
		JButton btnNewButton_1 = new JButton("Ajouter Ticket");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.gridheight = 2;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 0;
		gbc_btnNewButton_1.gridy = 8;
		getContentPane().add(btnNewButton_1, gbc_btnNewButton_1);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 7;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 6;
		gbc_textField.gridy = 9;
		getContentPane().add(textField, gbc_textField);
		textField.setColumns(10);
		
		JButton btnEnvoyer = new JButton("Envoyer");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 13;
		gbc_btnNewButton.gridy = 9;
		getContentPane().add(btnEnvoyer, gbc_btnNewButton);
		btnEnvoyer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().equals("")){				
				} else {
					afficherMessages();
				}
			}
		});
		
		
		JButton btnDeconnexion = new JButton("Deconnexion");
		GridBagConstraints gbc_btnDeconnexion = new GridBagConstraints();
		gbc_btnDeconnexion.gridwidth = 2;
		gbc_btnDeconnexion.insets = new Insets(0, 0, 0, 5);
		gbc_btnDeconnexion.gridx = 0;
		gbc_btnDeconnexion.gridy = 10;
		getContentPane().add(btnDeconnexion, gbc_btnDeconnexion);
		btnDeconnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
	}
	
	public void afficherMessages(){
		panelChat.removeAll();
		for(int i = 0; i < messages.length; i++){
			JLabel lblAuteurDate = new JLabel();
			JTextArea txtMessage = new JTextArea();
			txtMessage.setLineWrap(true);
			txtMessage.setEditable(false);
	        lblAuteurDate.setMaximumSize(new Dimension(300, 15));
	        lblAuteurDate.setMinimumSize(new Dimension(300, 15));
	        lblAuteurDate.setPreferredSize(new Dimension(300, 15));
	        txtMessage.setMaximumSize(new Dimension(480, 100));
	        txtMessage.setMinimumSize(new Dimension(480, 100));
	        txtMessage.setPreferredSize(new Dimension(480, 100));

	        
			if (idCreateurMessage[i] == idCreateurFil){
				lblAuteurDate.setAlignmentX(Component.LEFT_ALIGNMENT);
				lblAuteurDate.setHorizontalAlignment(SwingConstants.LEFT);
		        txtMessage.setAlignmentX(Component.LEFT_ALIGNMENT);
			} else {
				lblAuteurDate.setAlignmentX(Component.RIGHT_ALIGNMENT);
				lblAuteurDate.setHorizontalAlignment(SwingConstants.RIGHT);
		        txtMessage.setAlignmentX(Component.RIGHT_ALIGNMENT);
			}
			lblAuteurDate.setText(auteurs[i]);
	        panelChat.add(lblAuteurDate);
			txtMessage.setText(messages[i]);
			
	        panelChat.add(txtMessage);
			panelChat.repaint();
		}
	}
	
	
}
