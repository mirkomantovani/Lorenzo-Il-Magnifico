package it.polimi.ingsw.ps19.server;

import it.polimi.ingsw.ps19.server.controller.MatchHandler;

/**
 * @author Mirko
 *
 */
public interface ServerInterface {

	
	public void addClient(ClientHandler clientHandler);

	public void removeClient(ClientHandler clientHandler);

	
	public void closeMatch(MatchHandler matchHandler);
}
