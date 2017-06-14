package it.polimi.ingsw.ps19.server;

import it.polimi.ingsw.ps19.FamilyMember;
import it.polimi.ingsw.ps19.Match;
import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.command.AskMoveCommand;
import it.polimi.ingsw.ps19.command.ChosenPrivilegeCommand;
import it.polimi.ingsw.ps19.command.ClientToServerCommand;
import it.polimi.ingsw.ps19.command.InvalidActionCommand;
import it.polimi.ingsw.ps19.command.PlaceIntoCouncilPalaceCommand;
import it.polimi.ingsw.ps19.command.PlaceIntoIndustrialAreaCommand;
import it.polimi.ingsw.ps19.command.PlaceIntoMarketCommand;
import it.polimi.ingsw.ps19.command.RequestClosureCommand;
import it.polimi.ingsw.ps19.command.TakeCardCommand;
import it.polimi.ingsw.ps19.exception.NotApplicableException;
import it.polimi.ingsw.ps19.model.action.Action;
import it.polimi.ingsw.ps19.model.action.CouncilPalaceAction;
import it.polimi.ingsw.ps19.model.action.IndustrialAction;
import it.polimi.ingsw.ps19.model.action.MarketAction;
import it.polimi.ingsw.ps19.model.action.TakeCardAction;
import it.polimi.ingsw.ps19.model.area.Floor;
import it.polimi.ingsw.ps19.model.resource.Servant;
import it.polimi.ingsw.ps19.server.observers.CommandObserver;

/**
 * This class handles every command arriving from Client to Server, calling methods of MatchHandler
 * The reference to Match is needed to get player objects from the match
 * @author Mirko
 *
 */
public class ServerCommandHandler implements CommandObserver {

	private MatchHandler handler;
	private Match match;

	public ServerCommandHandler(MatchHandler matchHandler, Match match) {
		this.handler = matchHandler;
		this.match = match;
		System.out.println("Server command handler: sono stato creato");
	}

	private Player getCurrentPlayer() {
		return match.getCurrentPlayer();
	}

	public void applyCommand(PlaceIntoMarketCommand placeIntoMarketCommand) {
		
		FamilyMember familyMember = handler.getCurrentPlayer().getFamilyMember(placeIntoMarketCommand.getFamilyMember());
		
		switch(placeIntoMarketCommand.getActionSpace()){
		case "first" : try {
				handler.applyAction(new MarketAction(familyMember,match.getBoard().getMarket().getFirstMarket()));
			} catch (NotApplicableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		case "second" : try {
				handler.applyAction(new MarketAction(familyMember,match.getBoard().getMarket().getSecondMarket()));
			} catch (NotApplicableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		case "third" : try {
				handler.applyAction(new MarketAction(familyMember,match.getBoard().getMarket().getThirdMarket()));
			} catch (NotApplicableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		case "fourth" : try {
				handler.applyAction(new MarketAction(familyMember,match.getBoard().getMarket().getFirstMarket()));
		} catch (NotApplicableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		default : try {
			handler.applyAction(new MarketAction(familyMember,match.getBoard().getMarket().getFirstMarket()));
		} catch (NotApplicableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	}

	
	public void applyCommand(PlaceIntoIndustrialAreaCommand placeIntoIndustrialAreaCommand) {
		FamilyMember familyMember = handler.getCurrentPlayer().getFamilyMember(placeIntoIndustrialAreaCommand.getFamilyMember());
		switch(placeIntoIndustrialAreaCommand.getIndustrialArea()){
		case "bigHarvestArea": try {
				handler.applyAction(new IndustrialAction(familyMember,match.getBoard().getHarvestArea()));
			} catch (NotApplicableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		case "bigProductionArea" : try {
			handler.applyAction(new IndustrialAction(familyMember,match.getBoard().getProductionArea()));
		} catch (NotApplicableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		default : try {
			handler.applyAction(new IndustrialAction(familyMember,match.getBoard().getHarvestArea()));
		} catch (NotApplicableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	}
	
	public void applyCommand(PlaceIntoCouncilPalaceCommand placeIntoCouncilPalaceCommand){
		FamilyMember familyMember = handler.getCurrentPlayer().getFamilyMember(placeIntoCouncilPalaceCommand.getFamilyMember());
		try {
			handler.applyAction(new CouncilPalaceAction(familyMember,match.getBoard().getCouncilPalace()));
		} catch (NotApplicableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		handler.closeMatch(); 
		
	}

	public void applyCommand(ChosenPrivilegeCommand chosenPrivilegeCommand) {

		
	}

	@Override
	public void notifyNewCommand(ClientToServerCommand command) {
		command.processCommand(this);
	}

	
	
	//Others apply overloaded methods
}
