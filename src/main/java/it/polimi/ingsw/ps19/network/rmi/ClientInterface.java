package it.polimi.ingsw.ps19.network.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import it.polimi.ingsw.ps19.command.toclient.ServerToClientCommand;

/**
 * RMI client interface.
 *
 * @author Jimmy
 */
public interface ClientInterface extends Remote {
	
	/**
	 * This method will notify the client whenever a new command is sent from the server.
	 *
	 * @param command the command
	 * @throws RemoteException the remote exception
	 */
	public void notifyClient(ServerToClientCommand command) throws RemoteException;
	
	/**
	 * This will be used in order to close the communication.
	 *
	 * @throws RemoteException the remote exception
	 */
	public void closeConnection() throws RemoteException;
	
	
}
