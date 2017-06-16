package it.polimi.ingsw.ps19.server;

import it.polimi.ingsw.ps19.FamilyMember;
import it.polimi.ingsw.ps19.Match;
import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.command.AskMoveCommand;
import it.polimi.ingsw.ps19.command.toclient.AskPrivilegeChoiceCommand;
import it.polimi.ingsw.ps19.command.toclient.InvalidActionCommand;
import it.polimi.ingsw.ps19.command.toserver.ActivateLeaderCardCommand;
import it.polimi.ingsw.ps19.command.toserver.ChosenPrivilegeCommand;
import it.polimi.ingsw.ps19.command.toserver.ClientToServerCommand;
import it.polimi.ingsw.ps19.command.toserver.DiscardLeaderCardCommand;
import it.polimi.ingsw.ps19.command.toserver.ExcommunicationDecisionCommand;
import it.polimi.ingsw.ps19.command.toserver.PlaceIntoCouncilPalaceCommand;
import it.polimi.ingsw.ps19.command.toserver.PlaceIntoIndustrialAreaCommand;
import it.polimi.ingsw.ps19.command.toserver.PlaceIntoMarketCommand;
import it.polimi.ingsw.ps19.command.toserver.RequestClosureCommand;
import it.polimi.ingsw.ps19.command.toserver.TakeCardCommand;
import it.polimi.ingsw.ps19.exception.NotApplicableException;
import it.polimi.ingsw.ps19.model.action.Action;
import it.polimi.ingsw.ps19.model.action.CouncilPalaceAction;
import it.polimi.ingsw.ps19.model.action.IndustrialAction;
import it.polimi.ingsw.ps19.model.action.MarketAction;
import it.polimi.ingsw.ps19.model.action.TakeCardAction;
import it.polimi.ingsw.ps19.model.area.Floor;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;
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
			break;
		case "second" : try {
				handler.applyAction(new MarketAction(familyMember,match.getBoard().getMarket().getSecondMarket()));
			} catch (NotApplicableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "third" : try {
				handler.applyAction(new MarketAction(familyMember,match.getBoard().getMarket().getThirdMarket()));
			} catch (NotApplicableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "fourth" : try {
				handler.applyAction(new MarketAction(familyMember,match.getBoard().getMarket().getFirstMarket()));
		} catch (NotApplicableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
			break;
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
		case "multipleHarvestArea": try {
				handler.applyAction(new IndustrialAction(familyMember,match.getBoard().getHarvestArea(), match.getBoard().getHarvestArea().getMultipleActionSpace()));
		} catch (NotApplicableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		break;
		case "multipleProductionArea" : try {
			handler.applyAction(new IndustrialAction(familyMember,match.getBoard().getProductionArea(), match.getBoard().getProductionArea().getMultipleActionSpace()));
		} catch (NotApplicableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		case "singleHarvestArea" : try {
			handler.applyAction(new IndustrialAction(familyMember,match.getBoard().getProductionArea(), match.getBoard().getHarvestArea().getSingleActionSpace()));
		} catch (NotApplicableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
		case "singleProductionArea" : try {
			handler.applyAction(new IndustrialAction(familyMember,match.getBoard().getProductionArea(), match.getBoard().getProductionArea().getSingleActionSpace()));
		} catch (NotApplicableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
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
		
			for(ResourceChest rc : chosenPrivilegeCommand.getChoice()){
				handler.getCurrentPlayer().addResources(rc);
			}
		
	}

	@Override
	public void notifyNewCommand(ClientToServerCommand command) {
		command.processCommand(this);
	}

	public void applyCommand(DiscardLeaderCardCommand discardLeaderCardCommand) {
		handler.sendToCurrentPlayer(new AskPrivilegeChoiceCommand(1));
		handler.getCurrentPlayer().removeLeaderCard(discardLeaderCardCommand.getLeaderName());
		
		
	}

	public void applyCommand(ActivateLeaderCardCommand activateLeaderCardCommand) {
		// TODO Auto-generated method stub
		
	}

	public void applyCommand(ExcommunicationDecisionCommand excommunicationDecisionCommand) {
		// TODO Auto-generated method stub
		
	}

	
	
	//Others apply overloaded methods
}
