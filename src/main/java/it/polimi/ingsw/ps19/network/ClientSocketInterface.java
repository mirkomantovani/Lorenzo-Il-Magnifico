package it.polimi.ingsw.ps19.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import it.polimi.ingsw.ps19.command.ClientToServerCommand;
import it.polimi.ingsw.ps19.command.ServerToClientCommand;

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
		socket = new Socket(/*TODO insert port and address*/);
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
		try {
			outSocket.writeObject(command);
			outSocket.flush();
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

}
