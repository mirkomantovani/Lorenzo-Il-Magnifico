package it.polimi.ingsw.ps19.client;

import java.io.IOException;
import java.io.ObjectInputStream;

import it.polimi.ingsw.ps19.command.ClientToServerCommand;


/**
 * @author matteo
 * 
 * this class contains the observer of the socket channel inputStream
 *
 */
public class ClientSocketListener implements Runnable{

	private ObjectInputStream inSocket;
	
	public ClientSocketListener(ObjectInputStream inSocket) {
		this.inSocket = inSocket;
	}

	
	@Override
	public void run() {
		
		ClientToServerCommand command;
		while(true){
			try {
				command = (ClientToServerCommand)inSocket.readObject();
				//TODO send a notify!
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	
}
