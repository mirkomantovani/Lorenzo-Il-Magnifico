package it.polimi.ingsw.ps19.network.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import it.polimi.ingsw.ps19.command.toserver.ClientToServerCommand;

/**
 * The Interface ClientHandlerInterface.
 */
public interface ClientHandlerInterface extends Remote{
	
	/**
	 * This method adds the client among all the other waiting clients .
	 *
	 * @param port the port
	 * @throws RemoteException the remote exception
	 */
	public void addClient(int port) throws RemoteException;   //Questo metodo prende solo la porta e non il client? perchè dovrebbe prendere solo la porta?
																			//per come l'hanno impostata così ha senso..
	/**
																			 * This method notifies the server if the client sends a command.
																			 *
																			 * @param command the command
																			 * @throws RemoteException the remote exception
																			 */
	public void notifyServer(ClientToServerCommand command) throws RemoteException;
	
	
	/**
	 * This method closes the connection.
	 *
	 * @throws RemoteException the remote exception
	 */
	public void	closedByServer() throws RemoteException;
	
	
	
}
