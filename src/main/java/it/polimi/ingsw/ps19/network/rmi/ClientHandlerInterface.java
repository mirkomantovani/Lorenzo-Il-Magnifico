package it.polimi.ingsw.ps19.network.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import it.polimi.ingsw.ps19.command.ClientToServerCommand;

public interface ClientHandlerInterface extends Remote{
	
	/**
	 * This method adds the client among all the other waiting clients 
	 * 
	 * @param client
	 * @throws RemoteException
	 */
	public void addClient(int port) throws RemoteException;   //Questo metodo prende solo la porta e non il client? perchè dovrebbe prendere solo la porta?
																			//per come l'hanno impostata così ha senso..
	/**
	 * This method notifies the server if the client sends a command
	 * 
	 * @param command
	 * @throws RemoteException
	 */
	public void notifyServer(ClientToServerCommand command) throws RemoteException;
	
	
	/**
	 * This method closes the connection
	 * 
	 * @throws RemoteException
	 */
	public void	closedByServer() throws RemoteException;
	
	
	
}
