package it.polimi.ingsw.ps19.server;

import it.polimi.ingsw.ps19.FamilyMember;
import it.polimi.ingsw.ps19.Match;
import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.command.toclient.AskMoveCommand;
import it.polimi.ingsw.ps19.command.toclient.ChooseProductionExchangeEffectsCommand;
import it.polimi.ingsw.ps19.command.toclient.InvalidActionCommand;
import it.polimi.ingsw.ps19.command.toserver.ActivateLeaderCardCommand;
import it.polimi.ingsw.ps19.command.toserver.ChatMessageClientCommand;
import it.polimi.ingsw.ps19.command.toserver.ChosenLeaderCardCommand;
import it.polimi.ingsw.ps19.command.toserver.ChosenPrivilegeCommand;
import it.polimi.ingsw.ps19.command.toserver.ChurchSupportCommand;
import it.polimi.ingsw.ps19.command.toserver.ClientToServerCommand;
import it.polimi.ingsw.ps19.command.toserver.DiscardLeaderCardCommand;
import it.polimi.ingsw.ps19.command.toserver.FinishRoundCommand;
import it.polimi.ingsw.ps19.command.toserver.HarvestCommand;
import it.polimi.ingsw.ps19.command.toserver.InvalidInputCommand;
import it.polimi.ingsw.ps19.command.toserver.PlaceIntoCouncilPalaceCommand;
import it.polimi.ingsw.ps19.command.toserver.PlaceIntoMarketCommand;
import it.polimi.ingsw.ps19.command.toserver.PlayerMoveCommand;
import it.polimi.ingsw.ps19.command.toserver.ProductionActivationCommand;
import it.polimi.ingsw.ps19.command.toserver.ProductionCommand;
import it.polimi.ingsw.ps19.command.toserver.RequestClosureCommand;
import it.polimi.ingsw.ps19.command.toserver.SendCredentialsCommand;
import it.polimi.ingsw.ps19.command.toserver.TakeCardCommand;
import it.polimi.ingsw.ps19.exception.NotApplicableException;
import it.polimi.ingsw.ps19.model.action.Action;
import it.polimi.ingsw.ps19.model.action.CouncilPalaceAction;
import it.polimi.ingsw.ps19.model.action.IndustrialAction;
import it.polimi.ingsw.ps19.model.action.MarketAction;
import it.polimi.ingsw.ps19.model.action.TakeCardAction;
import it.polimi.ingsw.ps19.model.area.Floor;
import it.polimi.ingsw.ps19.model.resource.Servant;
import it.polimi.ingsw.ps19.server.controller.MatchHandler;
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

	public void applyCommand(PlaceIntoMarketCommand placeIntoMarketCommand) throws NotApplicableException {
		
		FamilyMember familyMember;
		familyMember = handler.getCurrentPlayer().getFamilyMember(placeIntoMarketCommand.getFamilyMember());
		
//		System.out.println("questo è il familyMember che deve fare l'azione" + familyMember.toString());
		
		
	try {
		if(familyMember ==null){
			throw new NotApplicableException("You had alredy Used this family member");
			}
		handler.applyAction(new MarketAction(familyMember,match.getBoard().getMarket().getMarktActionSpace(placeIntoMarketCommand.getActionSpace()),
				placeIntoMarketCommand.getPaidServants()));
	} catch (NotApplicableException e) {
		handler.sendToCurrentPlayer(new InvalidActionCommand(e.getNotApplicableCode()));
		handler.sendToCurrentPlayer(new AskMoveCommand());
	}
}
		
	
	

	public void applyCommand(PlaceIntoCouncilPalaceCommand placeIntoCouncilPalaceCommand){
		FamilyMember familyMember = handler.getCurrentPlayer().getFamilyMember(placeIntoCouncilPalaceCommand.getFamilyMember());
		try {
			if(familyMember ==null){
				throw new NotApplicableException("You had already Used this family member");
				}
			handler.applyAction(new CouncilPalaceAction(familyMember,match.getBoard().getCouncilPalace(),
					placeIntoCouncilPalaceCommand.getPaidServants()));
		} catch (NotApplicableException e) {
			handler.sendToCurrentPlayer(new InvalidActionCommand(e.getNotApplicableCode()));
			handler.sendToCurrentPlayer(new AskMoveCommand());
			}
	}
	
	public void applyCommand(TakeCardCommand takeCardCommand){
//		System.out.println("servercommandhandler: apply takecardcommand");
		
		try {
			Action action=calculateTakeCardAction(takeCardCommand);
			System.out.println("servercommandhandler: take card action calculated");
			handler.applyAction(action);
			
		} catch (NotApplicableException e) {
			
			System.out.println("takecard not applicable");
			handler.sendToCurrentPlayer(new InvalidActionCommand(e.getNotApplicableCode()));
			handler.sendToCurrentPlayer(new AskMoveCommand());
			return;
		}
		
	}
	
	public void applyCommand(ProductionCommand command){
		handler.saveProductionParams(command);
		handler.sendToCurrentPlayer(
				new ChooseProductionExchangeEffectsCommand(
						match.getCurrentPlayerProductionChoices(
								command.getFamilyMember(),command.getActionSpace(),command.getPaidServants())));
	}

	private Action calculateTakeCardAction(TakeCardCommand takeCardCommand) throws NotApplicableException {
		System.out.println("calculating takecardaction");
		Player player=match.getCurrentPlayer();
		FamilyMember familyMember=
				player.getFamilyMember(takeCardCommand.getFamilyMember());
		
		if(familyMember==null){
			throw new NotApplicableException("you don't have that family member");
		}
		else{
		Floor floor=match.getFloor(takeCardCommand.getCardType(),takeCardCommand.getFloor());
		System.out.println("creating new take card action");
		
		return new TakeCardAction(familyMember,floor,
				new Servant(takeCardCommand.getPaidServants()));
		}
	}

	public void applyCommand(RequestClosureCommand requestClosureCommand) {
		handler.closeMatch(); 
		
	}

	public void applyCommand(ChosenPrivilegeCommand chosenPrivilegeCommand) {
		handler.addPrivilegeResources(chosenPrivilegeCommand.getChoice());
//			for(ResourceChest rc : chosenPrivilegeCommand.getChoice()){
//				handler.getCurrentPlayer().addResources(rc);
//			}
		
	}

	@Override
	public void notifyNewCommand(ClientToServerCommand command) {
		command.processCommand(this);
	}

	public void applyCommand(DiscardLeaderCardCommand discardLeaderCardCommand) {
		handler.discardLeaderCard(discardLeaderCardCommand.getLeaderName());
	}

	public void applyCommand(ActivateLeaderCardCommand activateLeaderCardCommand) {
		handler.getCurrentPlayer().activateLeaderCard(activateLeaderCardCommand.getLeaderName());;
		
	}

	public void applyCommand(ChurchSupportCommand churchSupportCommand) {
		handler.handleChurchSupportDecision(churchSupportCommand.getPlayerColor(),churchSupportCommand.getDecision());
		
		
	}

	public void applyCommand(SendCredentialsCommand command, ClientHandler clientHandler) {
		handler.handleCredentials(command.getUsername(),command.getPassword(),clientHandler);
	}

	
	public void applyCommand(ChosenLeaderCardCommand command) {
		handler.handleLeaderChoice(command.getName(),command.getPlayerColor());	
	}

	

	public void applyCommand(PlayerMoveCommand playerMoveCommand, ClientHandler clientHandler) {
//		handler.handlePlayerMove(playerMoveCommand.getMove(), clientHandler);
	}

	public void applyCommand(FinishRoundCommand finishRoundCommand) {
		handler.finishRound();
	}
	
	public void applyCommand(InvalidInputCommand invalidInputCommand){
		handler.handleInvalidCommand();
	}

	public void applyCommand(ProductionActivationCommand productionActivationCommand) {
		handler.handleProductionActivation(productionActivationCommand);
	}

	public void applyCommand(SendCredentialsCommand sendCredentialsCommand) {
		// TODO Auto-generated method stub
		
	}

	public void applyCommand(ChatMessageClientCommand chatMessageClientCommand) {
		// TODO Auto-generated method stub
		
	}

	public void applyCommand(HarvestCommand harvestCommand){
		
		/*FamilyMember member = new FamilyMember(handler.getCurrentPlayer().getFamilyMember(
				harvestCommand.getFamilyMember()).getDice(), handler.getCurrentPlayer());*/
		
		FamilyMember member = handler.getCurrentPlayer().getFamilyMember(harvestCommand.getFamilyMember());
		
		if(harvestCommand.getActionSpace() == 1){
			try {
				handler.applyAction(new IndustrialAction(member, match.getBoard().getHarvestArea(),
						match.getBoard().getHarvestArea().getSingleActionSpace(), harvestCommand.getPaidServants()));
			} catch (NotApplicableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				handler.applyAction(new IndustrialAction(member, match.getBoard().getHarvestArea(),
						match.getBoard().getHarvestArea().getMultipleActionSpace(), harvestCommand.getPaidServants()));
			} catch (NotApplicableException e) {
				handler.sendToCurrentPlayer(new InvalidActionCommand(e.getNotApplicableCode()));
				handler.sendToCurrentPlayer(new AskMoveCommand());
			}
		}
		
	}	
	//Others apply overloaded methods
}
