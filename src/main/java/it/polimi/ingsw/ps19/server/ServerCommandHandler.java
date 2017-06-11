package it.polimi.ingsw.ps19.server;

import it.polimi.ingsw.ps19.Match;
import it.polimi.ingsw.ps19.Player;

/**
 * @author Mirko
 *
 */
public class ServerCommandHandler {

	private MatchHandler handler;
	private Match match;

	public ServerCommandHandler(MatchHandler matchHandler, Match match) {
		this.handler = matchHandler;
		this.match = match;
	}

	private Player getCurrentPlayer() {
		return match.getCurrentPlayer();
	}

	public void applyCommand( ) {
		
	}
	
	//Others apply overloaded methods
}
