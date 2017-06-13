package it.polimi.ingsw.ps19.server;

import it.polimi.ingsw.ps19.FamilyMember;
import it.polimi.ingsw.ps19.Match;
import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.command.ChosenPrivilegeCommand;
import it.polimi.ingsw.ps19.command.AskMoveCommand;
import it.polimi.ingsw.ps19.command.InvalidActionCommand;
import it.polimi.ingsw.ps19.command.PlaceIntoCouncilPalaceCommand;
import it.polimi.ingsw.ps19.command.PlaceIntoIndustrialAreaCommand;
import it.polimi.ingsw.ps19.command.PlaceIntoMarketCommand;
import it.polimi.ingsw.ps19.command.RequestClosureCommand;
import it.polimi.ingsw.ps19.command.TakeCardCommand;
import it.polimi.ingsw.ps19.exception.NotApplicableException;
import it.polimi.ingsw.ps19.model.action.Action;
import it.polimi.ingsw.ps19.model.action.TakeCardAction;
import it.polimi.ingsw.ps19.model.area.Floor;
import it.polimi.ingsw.ps19.model.resource.Servant;

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
		
		Action action=calculateTakeCardAction(takeCardCommand);
		
		try {
			handler.applyAction(action);
		} catch (NotApplicableException e) {
			handler.sendToCurrentPlayer(new InvalidActionCommand());
			handler.sendToCurrentPlayer(new AskMoveCommand());
			return;
		}
		
	}

	private Action calculateTakeCardAction(TakeCardCommand takeCardCommand) {
		Player player=match.getCurrentPlayer();
		FamilyMember familyMember=
				player.getFamilyMember(takeCardCommand.getFamilyMember());
		Floor floor=match.getFloor(takeCardCommand.getCardType(),takeCardCommand.getFloor());
		return new TakeCardAction(familyMember,floor,
				new Servant(takeCardCommand.getPaidServants()));
	}

	public void applyCommand(RequestClosureCommand requestClosureCommand) {
		// TODO Auto-generated method stub
		
	}

	public void applyCommand(ChosenPrivilegeCommand chosenPrivilegeCommand) {
		// TODO Auto-generated method stub
		
	}
	
	//Others apply overloaded methods
}
