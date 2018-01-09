package reseau;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import utilisateurs.Groupe;
import utilisateurs.Utilisateur;

public class SocketClient {


	public void demandeCreationTicket(Groupe grp, String titre, String premierMessage, Utilisateur auteur) {

	}

	public void envoiMessage(String corps) {

	}
	
	public static void main(String[] zero) {
		Socket soc;
		ObjectOutputStream oos;
		OutputStream os;
		PrintWriter out = null;
		BufferedReader in = null;
		try {
			soc.connect(soc.getLocalSocketAddress());
			out = new PrintWriter(soc.getOutputStream());
			in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Scanner sc = new Scanner(System.in);
		System.out.println(in.readLine());

		int login = sc.nextInt();
		out.println(login);
		out.flush();

		System.out.println(in.readLine());
		String pass = sc.nextLine();
		out.println(pass);
		out.flush();

		if(in.readLine().equals("true"))
			System.out.println("connecté");
	}
}
