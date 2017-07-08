package it.polimi.ingsw.ps19.client;

import java.io.IOException;
import java.io.ObjectInputStream;

import it.polimi.ingsw.ps19.command.toclient.ServerToClientCommand;
import it.polimi.ingsw.ps19.command.toserver.ClientToServerCommand;


/**
 * The listener interface for receiving clientSocket events.
 * The class that is interested in processing a clientSocket
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addClientSocketListener</code> method. When
 * the clientSocket event occurs, that object's appropriate
 * method is invoked.
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
