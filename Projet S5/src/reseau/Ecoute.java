package reseau;

import java.io.IOException;
import java.io.ObjectInputStream;

import utilisateurs.Utilisateur;

public class Ecoute implements Runnable{

	private SocketClient socket;

	public Ecoute(SocketClient socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		while(true) {
			try {
				ObjectInputStream ois = socket.getOis();
				Utilisateur maj = (Utilisateur) ois.readObject();
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
