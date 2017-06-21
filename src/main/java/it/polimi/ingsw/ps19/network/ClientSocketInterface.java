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
 * @author matteo
 * 
 * This class connects the client that chooses a socket connection, with the server
 *
 */
public class ClientSocketInterface implements NetworkInterface {

	private ObjectInputStream inSocket;
	private ObjectOutputStream outSocket;
	private Socket socket;
	private ClientSocketListener listener; 
	private Thread hear; // the thread that listens on the socket channel
	

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


	@Override
	public void sendCommand(ClientToServerCommand command) {
		// TODO Auto-generated method stub
		
		System.out.println("clisocket: invio comando");
		try {
			outSocket.writeUnshared(command); 
			outSocket.flush();
			outSocket.reset();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	@Override
	public void notifyClient(ServerToClientCommand command) {
		// TODO Auto-generated method stub
		
	}


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


	@Override
	public void addCommandObserver(ClientCommandHandler handler) {
		listener.addCommandObserver(handler);		
	}


}
