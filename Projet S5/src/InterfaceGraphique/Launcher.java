package InterfaceGraphique;

import reseau.SocketClient;

public class Launcher {
	
	public static void main(String[]args) throws Exception{
		SocketClient socket = new SocketClient();
		InterfaceLogin Graphic = new InterfaceLogin(socket);
		Graphic.setSize(600,400);
		Graphic.setVisible(true);
		Graphic.setResizable(false);
	}
}