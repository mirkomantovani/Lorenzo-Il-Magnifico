package it.polimi.ingsw.ps19.launchers;

import it.polimi.ingsw.ps19.server.Server;

/**
 * This class launches an instance of server, it has to be launched only once on a machine
 * and can handle multiple ((unlimited)) matches
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