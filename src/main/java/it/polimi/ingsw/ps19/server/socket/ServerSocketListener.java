package it.polimi.ingsw.ps19.server.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import it.polimi.ingsw.ps19.constant.NetworkConstants;
import it.polimi.ingsw.ps19.server.ClientHandler;
import it.polimi.ingsw.ps19.server.ClientHandlerSocket;
import it.polimi.ingsw.ps19.server.ServerInterface;


public class ServerSocketListener implements Runnable {
	private int port;
	private ServerSocket server;
	private boolean listening;
	private ServerInterface creator;

	public ServerSocketListener(ServerInterface serverStarter) {
		port = NetworkConstants.PORT;
		listening = false;
		creator = serverStarter;
	}

	public ServerSocketListener(ServerInterface serverStarter, int port) {
		this.port = port;
		listening = false;
		creator = serverStarter;
	}

	@Override
	public void run() {
		try {
			startListening();
		} catch (IOException e) {

			try {
				endListening();
			} catch (IOException e2) {
			}
		}
	}

	/**
	 * this method create a server socket and accept all connection
	 * 
	 * @throws IOException
	 */
	private void startListening() throws IOException {

		server = new ServerSocket(port);
		listening = true;
		int i = 0;// number of clients accepted
		while (listening) {
			Socket socket = server.accept();
			ClientHandler c = new ClientHandlerSocket(socket, i, creator);
			creator.addClient(c);
			i++;//
		}

	}

	/**
	 * this method closed all waiting ClientHandlerSocket and the server socket
	 * 
	 * @throws IOException
	 */
	public void endListening() throws IOException {
		if (listening) {
			listening = false;
			server.close();
		}
	}

}
