package it.polimi.ingsw.ps19.server;

import it.polimi.ingsw.ps19.Match;
import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.command.PlaceIntoCouncilPalaceCommand;
import it.polimi.ingsw.ps19.command.PlaceIntoIndustrialAreaCommand;
import it.polimi.ingsw.ps19.command.PlaceIntoMarketCommand;
import it.polimi.ingsw.ps19.command.RequestClosureCommand;
import it.polimi.ingsw.ps19.command.TakeCardCommand;

/**
 * This class handles every command arriving from Client to Server, calling methods of MatchHandler
 * The reference to Match is needed to get player objects from the match
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

	public void applyCommand(PlaceIntoMarketCommand placeIntoMarketCommand) {
		
	}
	
	public void applyCommand(PlaceIntoIndustrialAreaCommand placeIntoIndustrialAreaCommand){
		
	}
	
	public void applyCommand(PlaceIntoCouncilPalaceCommand placeIntoCouncilPalaceCommand){
		
	}
	
	public void applyCommand(TakeCardCommand takeCardCommand){
		handler.
	}

	public void applyCommand(RequestClosureCommand requestClosureCommand) {
		// TODO Auto-generated method stub
		
	}
	
	//Others apply overloaded methods
}
