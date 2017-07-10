package it.polimi.ingsw.ps19.client;

import java.io.IOException;
import java.io.ObjectInputStream;

import it.polimi.ingsw.ps19.command.toclient.ServerToClientCommand;


/**
 *
 * @author matteo
 * 
 * this class contains the observer of the socket channel inputStream
 */
public class ClientSocketListener implements Runnable{

	/** The in socket. */
	private ObjectInputStream inSocket;
	
	/** The observer. */
	private ServerToClientCommandObserver observer;
	
	/**
	 * Instantiates a new client socket listener.
	 *
	 * @param inSocket the in socket
	 */
	public ClientSocketListener(ObjectInputStream inSocket) {
		this.inSocket = inSocket;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		
		ServerToClientCommand command;
		while(true){
			try {
				command = (ServerToClientCommand)inSocket.readObject();
				//System.out.println("clientsocketlistener: ho letto oggetto e ora lo notifico");

				observer.notifyNewCommand(command);
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
				break;
			}
		}
	}
	
	/**
	 * Adds the command observer.
	 *
	 * @param clientCommandHandler the client command handler
	 */
	public void addCommandObserver(ClientCommandHandler clientCommandHandler){
		this.observer = clientCommandHandler;
	}

	
}
