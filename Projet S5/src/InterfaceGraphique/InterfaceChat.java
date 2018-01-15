package InterfaceGraphique;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.util.List;
import java.util.ListIterator;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import messages.Message;
import messages.Ticket;
import reseau.SocketClient;
import utilisateurs.Groupe;
import utilisateurs.Utilisateur;

import javax.swing.JScrollPane;

public class InterfaceChat extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2315599737080366500L;
	private JTextField textField;
	private SocketClient reseau;
	private Utilisateur ut;
	private List<Groupe> listGroupes;
	private JPanel panelChat;

	public InterfaceChat(SocketClient reseau) {

		this.reseau = reseau;
		ut = reseau.getDonnees();
		listGroupes = ut.getGroupes();

		System.out.println(listGroupes);

		setVisible(true);
		setSize(1920, 1080);
		getContentPane().setLayout(null);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		DefaultMutableTreeNode groupes = new DefaultMutableTreeNode("Groupes");

		for (int i = 0; i < this.listGroupes.size(); i++) {
			DefaultMutableTreeNode groupe = new DefaultMutableTreeNode(this.listGroupes.get(i));
			for (int j = 0; j < this.listGroupes.get(i).getTickets().size(); j++) {
				DefaultMutableTreeNode groupeFils = new DefaultMutableTreeNode(
						this.listGroupes.get(i).getTickets().get(j));
				groupe.add(groupeFils);
			}
			groupes.add(groupe);
		}
		GridBagConstraints gbc_tree = new GridBagConstraints();
		gbc_tree.gridwidth = 4;
		gbc_tree.gridheight = 8;
		gbc_tree.insets = new Insets(0, 0, 5, 5);
		gbc_tree.fill = GridBagConstraints.BOTH;
		gbc_tree.gridx = 0;
		gbc_tree.gridy = 0;
		JTree tree = new JTree(groupes);

		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.CONTIGUOUS_TREE_SELECTION);
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode choix = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				if (choix == null) {
					return;
				} else {
					Object objetChoix = choix.getUserObject();
					if (choix.isLeaf()) {
						Ticket tck = (Ticket) objetChoix;
						afficherMessages(tck);
					}
				}
			}
		});

		getContentPane().add(tree, gbc_tree);
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
		panelChat.setLayout(new BoxLayout(panelChat, this.getX()));
		JScrollPane scrollChat = new JScrollPane(panelChat, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
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
				if (textField.getText().equals("")) {
				} else {
					// A FAIRE
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
				reseau.seDeconnecter();
				dispose();
				new InterfaceLogin(new SocketClient());
			}
		});

		// frame.addWindowListener(new WindowAdapter() {
		// public void windowsClosing() {
		// reseau.seDeconnecter();
		// }
		// });
		//
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void afficherMessages(Ticket tck) {
		panelChat.removeAll();
		for (ListIterator<Message> it = tck.getMessages().listIterator(); it.hasNext();) {
			Message curr = it.next();
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

			if (tck.getId() == curr.getAuteur()) {
				lblAuteurDate.setAlignmentX(Component.LEFT_ALIGNMENT);
				lblAuteurDate.setHorizontalAlignment(SwingConstants.LEFT);
				txtMessage.setAlignmentX(Component.LEFT_ALIGNMENT);
			} else {
				lblAuteurDate.setAlignmentX(Component.RIGHT_ALIGNMENT);
				lblAuteurDate.setHorizontalAlignment(SwingConstants.RIGHT);
				txtMessage.setAlignmentX(Component.RIGHT_ALIGNMENT);
			}
			lblAuteurDate.setText(curr.getAuteur() + "" + curr.getAuteur());
			panelChat.add(lblAuteurDate);
			txtMessage.setText(curr.getCorps());

			panelChat.add(txtMessage);
			//panelChat.repaint();
		}
	}

}
