package it.polimi.ingsw.ps19.client;

import java.io.IOException;
import java.io.ObjectInputStream;

import it.polimi.ingsw.ps19.command.toclient.ServerToClientCommand;
import it.polimi.ingsw.ps19.command.toserver.ClientToServerCommand;


/**
 * @author matteo
 * 
 * this class contains the observer of the socket channel inputStream
 *
 */
public class ClientSocketListener implements Runnable{

	private ObjectInputStream inSocket;
	
	private ServerToClientCommandObserver observer;
	
	public ClientSocketListener(ObjectInputStream inSocket) {
		this.inSocket = inSocket;
	}

	
	@Override
	public void run() {
		
		ServerToClientCommand command;
		while(true){
			try {
				command = (ServerToClientCommand)inSocket.readObject();
				//System.out.println("clientsocketlistener: ho letto oggetto e ora lo notifico");
				observer.notifyNewCommand(command);
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void addCommandObserver(ClientCommandHandler clientCommandHandler){
		this.observer = clientCommandHandler;
	}

	
}
