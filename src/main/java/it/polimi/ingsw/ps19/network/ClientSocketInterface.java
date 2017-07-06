package it.polimi.ingsw.ps19.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import it.polimi.ingsw.ps19.client.ClientCommandHandler;
import it.polimi.ingsw.ps19.client.ClientSocketListener;
import it.polimi.ingsw.ps19.command.toclient.ServerToClientCommand;
import it.polimi.ingsw.ps19.command.toserver.ClientToServerCommand;
import it.polimi.ingsw.ps19.constant.NetworkConstants;

/**
 * The Class ClientSocketInterface.
 *
 * @author matteo
 * 
 * This class connects the client that chooses a socket connection, with the server
 */
public class ClientSocketInterface implements NetworkInterface {

	/** The in socket. */
	private ObjectInputStream inSocket;
	
	/** The out socket. */
	private ObjectOutputStream outSocket;
	
	/** The socket. */
	private Socket socket;
	
	/** The listener. */
	private ClientSocketListener listener; 
	
	/** The hear. */
	private Thread hear; // the thread that listens on the socket channel
	

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.network.NetworkInterface#connect()
	 */
	public void connect() {
		try {
			socket = new Socket(NetworkConstants.SERVER_IP_ADDRESS, NetworkConstants.PORT);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try{
			outSocket = new ObjectOutputStream(socket.getOutputStream());
			outSocket.flush();
			inSocket = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// create listener
		listener = new ClientSocketListener(inSocket);
	
		hear = new Thread(listener);
		hear.start();
	}


	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.network.NetworkInterface#sendCommand(it.polimi.ingsw.ps19.command.toserver.ClientToServerCommand)
	 */
	@Override
	public void sendCommand(ClientToServerCommand command) {
		
		System.out.println("clisocket: invio comando");
		try {
			outSocket.writeObject(command); 
			outSocket.flush();
			outSocket.reset();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}


	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.network.NetworkInterface#notifyClient(it.polimi.ingsw.ps19.command.toclient.ServerToClientCommand)
	 */
	@Override
	public void notifyClient(ServerToClientCommand command) {
		// TODO Auto-generated method stub
		
	}


	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.network.NetworkInterface#closeConnection()
	 */
	@Override
	public void closeConnection() {
		// TODO Auto-generated method stub
		try{
			socket.close();
			inSocket.close();
			outSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.network.NetworkInterface#addCommandObserver(it.polimi.ingsw.ps19.client.ClientCommandHandler)
	 */
	@Override
	public void addCommandObserver(ClientCommandHandler handler) {
		listener.addCommandObserver(handler);		
	}


}
