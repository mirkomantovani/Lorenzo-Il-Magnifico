package it.polimi.ingsw.ps19.server;

import it.polimi.ingsw.ps19.server.controller.MatchHandler;

/**
 * A generic server interface to manage basic operations the server has to do
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
