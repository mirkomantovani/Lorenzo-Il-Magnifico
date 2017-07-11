package it.polimi.ingsw.ps19.server;

import it.polimi.ingsw.ps19.command.toclient.AskMoveCommand;
import it.polimi.ingsw.ps19.command.toclient.ChatMessageServerCommand;
import it.polimi.ingsw.ps19.command.toclient.ChooseProductionExchangeEffectsCommand;
import it.polimi.ingsw.ps19.command.toclient.InvalidActionCommand;
import it.polimi.ingsw.ps19.command.toclient.InvalidCommand;
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
import it.polimi.ingsw.ps19.command.toserver.ReconnectionAnswerCommand;
import it.polimi.ingsw.ps19.command.toserver.RequestClosureCommand;
import it.polimi.ingsw.ps19.command.toserver.SatanChoiceCommand;
import it.polimi.ingsw.ps19.command.toserver.SendCredentialsCommand;
import it.polimi.ingsw.ps19.command.toserver.TakeCardCommand;
import it.polimi.ingsw.ps19.exception.NotApplicableException;
import it.polimi.ingsw.ps19.model.FamilyMember;
import it.polimi.ingsw.ps19.model.Match;
import it.polimi.ingsw.ps19.model.Player;
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
 * This class handles every command arriving from Client to Server, calling
 * methods of MatchHandler The reference to Match is needed to get player
 * objects from the match.
 *
 * @author Mirko
 */
public class ServerCommandHandler implements CommandObserver {

	/** The handler. */
	private MatchHandler handler;
	
	/** The match. */
	private Match match;

	/**
	 * Instantiates a new server command handler.
	 *
	 * @param matchHandler the match handler
	 * @param match the match
	 */
	public ServerCommandHandler(MatchHandler matchHandler, Match match) {
		this.handler = matchHandler;
		this.match = match;
	}


	/**
	 * Apply command.
	 *
	 * @param placeIntoMarketCommand the place into market command
	 * @throws NotApplicableException the not applicable exception
	 */
	public void applyCommand(PlaceIntoMarketCommand placeIntoMarketCommand) throws NotApplicableException {
		try {
			FamilyMember familyMember;
			familyMember = handler.getCurrentPlayer().getFamilyMember(placeIntoMarketCommand.getFamilyMember());


			try {
				if (familyMember == null) {
					throw new NotApplicableException("You had alredy Used this family member");
				}
				handler.applyAction(new MarketAction(familyMember,
						match.getBoard().getMarket().getMarktActionSpace(placeIntoMarketCommand.getActionSpace()),
						placeIntoMarketCommand.getPaidServants()));
			} catch (NotApplicableException e) {
				handler.sendToCurrentPlayer(new InvalidActionCommand(e.getNotApplicableCode()));
				handler.sendToCurrentPlayer(new AskMoveCommand());
			}
		} catch (Exception e) {
			handler.sendToCurrentPlayer(new InvalidCommand());
			handler.sendToCurrentPlayer(new AskMoveCommand());
		}
	}

	/**
	 * Apply command.
	 *
	 * @param placeIntoCouncilPalaceCommand the place into council palace command
	 */
	public void applyCommand(PlaceIntoCouncilPalaceCommand placeIntoCouncilPalaceCommand) {
		try {
			FamilyMember familyMember = handler.getCurrentPlayer()
					.getFamilyMember(placeIntoCouncilPalaceCommand.getFamilyMember());
			try {
				if (familyMember == null) {
					throw new NotApplicableException("You had already Used this family member");
				}
				handler.applyAction(new CouncilPalaceAction(familyMember, match.getBoard().getCouncilPalace(),
						placeIntoCouncilPalaceCommand.getPaidServants()));
			} catch (NotApplicableException e) {
				handler.sendToCurrentPlayer(new InvalidActionCommand(e.getNotApplicableCode()));
				handler.sendToCurrentPlayer(new AskMoveCommand());
			}
		} catch (Exception e) {
			handler.sendToCurrentPlayer(new InvalidCommand());
			handler.sendToCurrentPlayer(new AskMoveCommand());
		}
	}

	/**
	 * Apply command.
	 *
	 * @param takeCardCommand the take card command
	 */
	public void applyCommand(TakeCardCommand takeCardCommand) {
		try {
			try {
				Action action = calculateTakeCardAction(takeCardCommand);
				handler.applyAction(action);

			} catch (NotApplicableException e) {

				handler.sendToCurrentPlayer(new InvalidActionCommand(e.getNotApplicableCode()));
				handler.sendToCurrentPlayer(new AskMoveCommand());
				return;
			}
		} catch (Exception e) {
			handler.sendToCurrentPlayer(new InvalidCommand());
			handler.sendToCurrentPlayer(new AskMoveCommand());
		}

	}

	/**
	 * Apply command.
	 *
	 * @param command the command
	 */
	public void applyCommand(ProductionCommand command) {
		try{
		handler.saveProductionParams(command);
		handler.sendToCurrentPlayer(new ChooseProductionExchangeEffectsCommand(match.getCurrentPlayerProductionChoices(
				command.getFamilyMember(), command.getActionSpace(), command.getPaidServants())));
		}catch(Exception e){
			e.printStackTrace();
			handler.sendToCurrentPlayer(new InvalidCommand());
			handler.sendToCurrentPlayer(new AskMoveCommand());
		}
		}

	/**
	 * Calculate take card action.
	 *
	 * @param takeCardCommand the take card command
	 * @return the action
	 * @throws NotApplicableException the not applicable exception
	 */
	private Action calculateTakeCardAction(TakeCardCommand takeCardCommand) throws NotApplicableException {
		Player player = match.getCurrentPlayer();
		FamilyMember familyMember = player.getFamilyMember(takeCardCommand.getFamilyMember());
		if (familyMember == null) {
			throw new NotApplicableException("you don't have that family member");
		} else {
			Floor floor = match.getFloor(takeCardCommand.getCardType(), takeCardCommand.getFloor());

			return new TakeCardAction(familyMember, floor, new Servant(takeCardCommand.getPaidServants()));
		}
	}

	/**
	 * Apply command.
	 *
	 * @param requestClosureCommand the request closure command
	 */
	public void applyCommand(RequestClosureCommand requestClosureCommand) {
		handler.clientClosedTheGame(requestClosureCommand.getPlayerColor());
	}

	/**
	 * Apply command.
	 *
	 * @param chosenPrivilegeCommand the chosen privilege command
	 */
	public void applyCommand(ChosenPrivilegeCommand chosenPrivilegeCommand) {
		handler.addPrivilegeResources(chosenPrivilegeCommand.getChoice());
		// for(ResourceChest rc : chosenPrivilegeCommand.getChoice()){
		// handler.getCurrentPlayer().addResources(rc);
		// }

	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.server.observers.CommandObserver#notifyNewCommand(it.polimi.ingsw.ps19.command.toserver.ClientToServerCommand)
	 */
	@Override
	public void notifyNewCommand(ClientToServerCommand command) {
		command.processCommand(this);
	}

	/**
	 * Apply command.
	 *
	 * @param discardLeaderCardCommand the discard leader card command
	 */
	public void applyCommand(DiscardLeaderCardCommand discardLeaderCardCommand) {
		handler.discardLeaderCard(discardLeaderCardCommand.getLeaderName());
	}

	/**
	 * Apply command.
	 *
	 * @param activateLeaderCardCommand the activate leader card command
	 * @throws NotApplicableException 
	 */
	public void applyCommand(ActivateLeaderCardCommand activateLeaderCardCommand) throws NotApplicableException {
		if(handler.getCurrentPlayer().getLeaderCards().containsKey(activateLeaderCardCommand.getLeaderName())
				&& handler.isLeaderCardActivable(activateLeaderCardCommand.getLeaderName())){
			handler.getCurrentPlayer().activateLeaderCard(activateLeaderCardCommand.getLeaderName());
			handler.sendToCurrentPlayer(new AskMoveCommand());
		}
		else {
			handler.sendToCurrentPlayer(new InvalidActionCommand("You don't have that leader requirements to activate it!"));
			handler.sendToCurrentPlayer(new AskMoveCommand());
		}
			
	}


	/**
	 * Apply command.
	 *
	 * @param churchSupportCommand the church support command
	 */
	public void applyCommand(ChurchSupportCommand churchSupportCommand) {
		handler.handleChurchSupportDecision(churchSupportCommand.getPlayerColor(), churchSupportCommand.getDecision());

	}

	/**
	 * Apply command.
	 *
	 * @param command the command
	 * @param clientHandler the client handler
	 */
	public void applyCommand(SendCredentialsCommand command, ClientHandler clientHandler) {
		handler.handleCredentials(command.getUsername(), command.getPassword(), clientHandler);
	}

	/**
	 * Apply command.
	 *
	 * @param command the command
	 */
	public void applyCommand(ChosenLeaderCardCommand command) {
		handler.handleLeaderChoice(command.getName(), command.getPlayerColor());
	}

	/**
	 * Apply command.
	 *
	 * @param playerMoveCommand the player move command
	 * @param clientHandler the client handler
	 */
	public void applyCommand(PlayerMoveCommand playerMoveCommand, ClientHandler clientHandler) {
		// handler.handlePlayerMove(playerMoveCommand.getMove(), clientHandler);
	}

	/**
	 * Apply command.
	 *
	 * @param finishRoundCommand the finish round command
	 */
	public void applyCommand(FinishRoundCommand finishRoundCommand) {
		handler.finishRound();
	}

	/**
	 * Apply command.
	 *
	 * @param invalidInputCommand the invalid input command
	 */
	public void applyCommand(InvalidInputCommand invalidInputCommand) {
		handler.handleInvalidCommand();
	}

	/**
	 * Apply command.
	 *
	 * @param productionActivationCommand the production activation command
	 */
	public void applyCommand(ProductionActivationCommand productionActivationCommand) {
		handler.handleProductionActivation(productionActivationCommand);
	}

	/**
	 * Apply command.
	 *
	 * @param sendCredentialsCommand the send credentials command
	 */
	public void applyCommand(SendCredentialsCommand sendCredentialsCommand) {
		handler.handleAuthenticationRequest(sendCredentialsCommand.getUsername(),
				sendCredentialsCommand.getPassword(),sendCredentialsCommand.getPlayerColor());
	}

	/**
	 * Apply command.
	 *
	 * @param chatMessageClientCommand the chat message client command
	 */
	public void applyCommand(ChatMessageClientCommand chatMessageClientCommand) {
		handler.sendToAllPlayers(new ChatMessageServerCommand(chatMessageClientCommand.getMessage()));
	}

	/**
	 * Apply command.
	 *
	 * @param harvestCommand the harvest command
	 */
	public void applyCommand(HarvestCommand harvestCommand) {
		try{
		FamilyMember member = handler.getCurrentPlayer().getFamilyMember(harvestCommand.getFamilyMember());

		if (harvestCommand.getActionSpace() == 1) {
			try {
				handler.applyAction(new IndustrialAction(member, match.getBoard().getHarvestArea(),
						match.getBoard().getHarvestArea().getSingleActionSpace(), harvestCommand.getPaidServants()));
			} catch (NotApplicableException e) {
				handler.sendToCurrentPlayer(new InvalidActionCommand(e.getNotApplicableCode()));
				handler.sendToCurrentPlayer(new AskMoveCommand());
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
		}catch(Exception e){
			handler.sendToCurrentPlayer(new InvalidCommand());
			handler.sendToCurrentPlayer(new AskMoveCommand());
		}

	}


	public void applyCommand(SatanChoiceCommand satanChoice) {
		handler.handleSatanChoice(satanChoice.getColor());
		
	}


	public void applyCommand(ReconnectionAnswerCommand reconnectionAnswerCommand) {
		
	}
	
	
	// Others apply overloaded methods
}
