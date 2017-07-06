package it.polimi.ingsw.ps19.server;

import it.polimi.ingsw.ps19.server.controller.MatchHandler;

/**
 * The Interface ServerInterface.
 *
 * @author Mirko
 */
public interface ServerInterface {

	
	/**
	 * Adds the client.
	 *
	 * @param clientHandler the client handler
	 */
	public void addClient(ClientHandler clientHandler);

	/**
	 * Removes the client.
	 *
	 * @param clientHandler the client handler
	 */
	public void removeClient(ClientHandler clientHandler);

	
	/**
	 * Close match.
	 *
	 * @param matchHandler the match handler
	 */
	public void closeMatch(MatchHandler matchHandler);
}
