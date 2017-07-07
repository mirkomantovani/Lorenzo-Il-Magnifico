package it.polimi.ingsw.ps19.client;

import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps19.command.toclient.ServerToClientCommand;

/**
 * An asynchronous update interface for receiving notifications
 * about ServerToClientCommand information as the ServerToClientCommand is constructed.
 */
/**
 * @author matteo
 *
 */
public interface ServerToClientCommandObserver {

	/**
	 * This method is called when information about an ServerToClientCommand
	 * which was previously requested using an asynchronous
	 * interface becomes available.
	 *
	 * @param serverToClientCommand the server to client command
	 */
	public void notifyNewCommand(ServerToClientCommand serverToClientCommand);



}
