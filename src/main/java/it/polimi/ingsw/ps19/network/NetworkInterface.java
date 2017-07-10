package it.polimi.ingsw.ps19.network;


import it.polimi.ingsw.ps19.client.ClientCommandHandler;
import it.polimi.ingsw.ps19.command.toclient.ServerToClientCommand;
import it.polimi.ingsw.ps19.command.toserver.ClientToServerCommand;


/**
 * The Interface NetworkInterface.
 * This class represents the generic network interface to hide the type of client connection
 * to the server
 */
public interface NetworkInterface{

	
	/**
	 * Connect.
	 *
	 * @throws Exception the exception
	 */
	void connect() throws Exception;

	
	/**
	 * Send command.
	 *
	 * @param command the command
	 * @throws Exception the exception
	 */
	void sendCommand( ClientToServerCommand command) throws Exception;


	/**
	 * Notify client.
	 *
	 * @param command the command
	 */
	void notifyClient(ServerToClientCommand command);

	
	/**
	 * Close connection.
	 */
	void closeConnection();


	/**
	 * Adds the command observer.
	 *
	 * @param handler the handler
	 */
	void addCommandObserver(ClientCommandHandler handler);

}