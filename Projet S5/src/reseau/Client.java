package reseau;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Scanner;

import messages.Ticket;
import utilisateurs.Utilisateur;

public class Client {

	public static void main(String[] args) {

		SocketClient socket = new SocketClient();
		try {
//			PrintWriter out = new PrintWriter(socket.getSoc().getOutputStream());
//			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getSoc().getInputStream()));
//			Scanner sc = new Scanner(System.in);
//
//			System.out.println(in.readLine());
//			String login = sc.nextLine();
//			out.println(login);
//			out.flush();
//
//			System.out.println(in.readLine());
//
//			String pass = sc.nextLine();
//			out.println(pass);
//			out.flush();
//
//			System.out.println(in.readLine());

			ObjectOutputStream oos = new ObjectOutputStream(socket.getSoc().getOutputStream());
			ObjectInputStream ois = new ObjectInputStream(socket.getSoc().getInputStream());
			oos.writeObject(new Integer(2134));
			oos.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// sc.close();
	}
}
