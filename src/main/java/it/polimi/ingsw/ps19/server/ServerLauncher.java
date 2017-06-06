package it.polimi.ingsw.ps19.server;

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

