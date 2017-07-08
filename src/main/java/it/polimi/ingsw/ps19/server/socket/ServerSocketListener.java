package it.polimi.ingsw.ps19.server.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import it.polimi.ingsw.ps19.constant.NetworkConstants;
import it.polimi.ingsw.ps19.server.ClientHandler;
import it.polimi.ingsw.ps19.server.ServerInterface;


/**
 * The listener for receiving Socket events, this is instantiated by the server and
 * listens for new clients that try to connect to the server via socket
 *
 * @author Mirko
 */
public class ServerSocketListener implements Runnable {
	
	/** The port. */
	private int port;
	
	/** The server. */
	private ServerSocket server;
	
	/** The listening. */
	private boolean listening;
	
	/** The creator. */
	private ServerInterface creator;

	/**
	 * Instantiates a new server socket listener.
	 *
	 * @param serverStarter the server starter
	 */
	public ServerSocketListener(ServerInterface serverStarter) {
		port = NetworkConstants.PORT;
		listening = false;
		creator = serverStarter;
	}

	/**
	 * Instantiates a new server socket listener.
	 *
	 * @param serverStarter the server starter
	 * @param port the port
	 */
	public ServerSocketListener(ServerInterface serverStarter, int port) {
		this.port = port;
		listening = false;
		creator = serverStarter;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
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
	 * this method create a server socket and accept all connection.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
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
	 * this method closed all waiting ClientHandlerSocket and the server socket.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void endListening() throws IOException {
		if (listening) {
			listening = false;
			server.close();
		}
	}

}
