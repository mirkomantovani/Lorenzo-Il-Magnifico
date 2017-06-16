package it.polimi.ingsw.ps19.server;

import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.command.toserver.ClientToServerCommand;

/**
 * This interface is used to implement the MVC pattern between the two ClientHandler 
 * concrete classes and the MatchHandler
 * 
 * @author Mirko
 *
 */
public interface MatchHandlerObserver {

	
	/**
	 * this method will check and allow the application of a command sent by a specific player
	 * 
	 * @param command
	 *            : the command received
	 * @param player
	 *            : the player the will use the command
	 * @return true if it is allowed false otherwise
	 */
	boolean isAllowed(ClientToServerCommand command, Player player);

	/**
	 * this method removes from the match the client handler "clientHandler" and
	 * the player
	 * 
	 * @param clientHandler
	 *            : the client handler going to be removed
	 */
	void removeClient(ClientHandler clientHandler);
}
