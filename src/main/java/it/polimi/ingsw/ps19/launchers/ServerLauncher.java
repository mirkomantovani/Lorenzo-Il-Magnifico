package it.polimi.ingsw.ps19.launchers;

import it.polimi.ingsw.ps19.server.Server;

/**
 * @author Mirko
 *
 */
public class ServerLauncher {

	private ServerLauncher() {
	}

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

