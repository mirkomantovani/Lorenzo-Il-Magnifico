package it.polimi.ingsw.ps19.launchers;

import it.polimi.ingsw.ps19.server.Server;

/**
 * The Class ServerLauncher.
 *
 * @author Mirko
 */
public class ServerLauncher {

	/**
	 * Instantiates a new server launcher.
	 */
	private ServerLauncher() {
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		Thread t = null;
		try {
			Server server = Server.getInstance();
			t = new Thread(server);
			t.start();
		} catch (Exception e) {
			t.interrupt();
		}
	}
}