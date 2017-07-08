package it.polimi.ingsw.ps19.client;


import it.polimi.ingsw.ps19.command.toclient.AskAuthenticationCommand;
import it.polimi.ingsw.ps19.command.toclient.AskFinishRoundOrDiscardCommand;
import it.polimi.ingsw.ps19.command.toclient.AskForExcommunicationPaymentCommand;
import it.polimi.ingsw.ps19.command.toclient.AskMoveCommand;
import it.polimi.ingsw.ps19.command.toclient.AskPrivilegeChoiceCommand;
import it.polimi.ingsw.ps19.command.toclient.AssignColorCommand;
import it.polimi.ingsw.ps19.command.toclient.AuthenticatedCorrectlyCommand;
import it.polimi.ingsw.ps19.command.toclient.ChatMessageServerCommand;
import it.polimi.ingsw.ps19.command.toclient.ChooseLeaderCardCommand;
import it.polimi.ingsw.ps19.command.toclient.ChooseProductionExchangeEffectsCommand;
import it.polimi.ingsw.ps19.command.toclient.CloseClientCommand;
import it.polimi.ingsw.ps19.command.toclient.InitializeMatchCommand;
import it.polimi.ingsw.ps19.command.toclient.InitializeTurnCommand;
import it.polimi.ingsw.ps19.command.toclient.InvalidActionCommand;
import it.polimi.ingsw.ps19.command.toclient.InvalidCommand;
import it.polimi.ingsw.ps19.command.toclient.LoseCommand;
import it.polimi.ingsw.ps19.command.toclient.NotifyExcommunicationCommand;
import it.polimi.ingsw.ps19.command.toclient.OpponentStatusChangeCommand;
import it.polimi.ingsw.ps19.command.toclient.PlayerStatusChangeCommand;
import it.polimi.ingsw.ps19.command.toclient.RefreshBoardCommand;
import it.polimi.ingsw.ps19.command.toclient.RoundTimerExpiredCommand;
import it.polimi.ingsw.ps19.command.toclient.ServerToClientCommand;
import it.polimi.ingsw.ps19.command.toclient.StartTurnCommand;
import it.polimi.ingsw.ps19.command.toclient.WinCommand;
import it.polimi.ingsw.ps19.command.toclient.WrongPasswordCommand;
import it.polimi.ingsw.ps19.network.NetworkInterface;
import it.polimi.ingsw.ps19.view.UserInterface;

/**
 * The Class ClientCommandHandler.
 * This class manages the commands from the Server by calling the right method based on 
 * connection and view type
 *
 * @author matteo
 * 
 *  this object modifies the view of the client by applying commands
 */
public class ClientCommandHandler implements ServerToClientCommandObserver{

	/** The user interface. */
	private UserInterface userInterface;
	
	/** The network interface. */
	private NetworkInterface networkInterface;
	
	/**
	 * Instantiates a new client command handler.
	 *
	 * @param ui the ui
	 * @param networkInterface the network interface
	 */
	public ClientCommandHandler(UserInterface ui, NetworkInterface networkInterface){
		this.userInterface = ui;
		this.networkInterface = networkInterface;
	}

	/**
	 * Apply command.
	 *
	 * @param invalidCommand the invalid command
	 */
	public void applyCommand(InvalidCommand invalidCommand) {
		userInterface.commandNotValid();
		
	}

	/**
	 * Apply command.
	 *
	 * @param startTurnCommand the start turn command
	 */
	public void applyCommand(StartTurnCommand startTurnCommand) {
		System.out.println("clientcommandhandler: arrivato startturn command");
		userInterface.startTurn();
		
	}

	/**
	 * Apply command.
	 *
	 * @param closeConnectionCommand the close connection command
	 */
	public void applyCommand(CloseClientCommand closeConnectionCommand) {
		userInterface.notifyServerClosed();
		networkInterface.closeConnection();
		
	}

	/**
	 * Apply command.
	 *
	 * @param askPrivilegeChoiceCommand the ask privilege choice command
	 */
	public void applyCommand(AskPrivilegeChoiceCommand askPrivilegeChoiceCommand) {
		userInterface.AskPrivilegeChoice(askPrivilegeChoiceCommand.getNumberOfPrivilege(), askPrivilegeChoiceCommand.getPrivilegeResources());
		
	}

	/**
	 * Apply command.
	 *
	 * @param initializeMatchCommand the initialize match command
	 */
	public void applyCommand(InitializeMatchCommand initializeMatchCommand) {
		userInterface.initializeMatch(initializeMatchCommand.getNumPlayers());
	}

	/**
	 * Apply command.
	 *
	 * @param winCommand the win command
	 */
	public void applyCommand(WinCommand winCommand) {
		userInterface.win();
		
	}

	/**
	 * Apply command.
	 *
	 * @param loseCommand the lose command
	 */
	public void applyCommand(LoseCommand loseCommand) {
		userInterface.lose();
		
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.client.ServerToClientCommandObserver#notifyNewCommand(it.polimi.ingsw.ps19.command.toclient.ServerToClientCommand)
	 */
	@Override
	public void notifyNewCommand(ServerToClientCommand serverToClientCommand) {
		serverToClientCommand.processCommand(this);
		
	}

	/**
	 * Apply command.
	 *
	 * @param notifyExcommunicationCommand the notify excommunication command
	 */
	public void applyCommand(NotifyExcommunicationCommand notifyExcommunicationCommand) {
		userInterface.notifyExcommunication();
		
	}

	/**
	 * Apply command.
	 *
	 * @param chooseLeaderCardCommand the choose leader card command
	 */
	public void applyCommand(ChooseLeaderCardCommand chooseLeaderCardCommand) {
		userInterface.startDraft(chooseLeaderCardCommand.getPossibleChoices());
	}

	/**
	 * Apply command.
	 *
	 * @param solveExcommunicationCommand the solve excommunication command
	 */
	public void applyCommand(AskForExcommunicationPaymentCommand solveExcommunicationCommand) {
		userInterface.askForExcommunicationPayment(solveExcommunicationCommand.getExcommunicationEffect());
	}

	/**
	 * Apply command.
	 *
	 * @param askAuthenticationCommand the ask authentication command
	 */
	public void applyCommand(AskAuthenticationCommand askAuthenticationCommand) {
		userInterface.askNameAndPassword();
	}

	/**
	 * Apply command.
	 *
	 * @param playerStatusChangeCommand the player status change command
	 */
	public void applyCommand(PlayerStatusChangeCommand playerStatusChangeCommand) {
		userInterface.playerStatusChange(playerStatusChangeCommand.getPlayer());
	}
	
	/**
	 * Apply command.
	 *
	 * @param initializeTurnCommand the initialize turn command
	 */
	public void applyCommand(InitializeTurnCommand initializeTurnCommand){
		userInterface.initializeTurn( initializeTurnCommand.getPeriod(), initializeTurnCommand.getTurn());
	}
	
	/**
	 * Apply command.
	 *
	 * @param opponentStatusChangeCommand the opponent status change command
	 */
	public void applyCommand(OpponentStatusChangeCommand opponentStatusChangeCommand){
		userInterface.opponentStatusChanged(opponentStatusChangeCommand.getMaskedPlayer());
	}

	/**
	 * Apply command.
	 *
	 * @param chatMessageServerCommand the chat message server command
	 */
	public void applyCommand(ChatMessageServerCommand chatMessageServerCommand) {
		userInterface.newChatMessage(chatMessageServerCommand.getText());
		
	}

	/**
	 * Apply command.
	 *
	 * @param chooseProductionExchangeEffectsCommand the choose production exchange effects command
	 */
	public void applyCommand(ChooseProductionExchangeEffectsCommand chooseProductionExchangeEffectsCommand) {
		userInterface.askForProductionExchangeEffect(chooseProductionExchangeEffectsCommand.getChoices());
		
	}

	/**
	 * Apply command.
	 *
	 * @param refreshBoardCommand the refresh board command
	 */
	public void applyCommand(RefreshBoardCommand refreshBoardCommand) {
		userInterface.refreshBoard(refreshBoardCommand.getBoard());
		
	}

	/**
	 * Apply command.
	 *
	 * @param roundTimerExpiredCommand the round timer expired command
	 */
	public void applyCommand(RoundTimerExpiredCommand roundTimerExpiredCommand) {
		userInterface.notifyRoundTimerExpired();
		
	}
	

	/**
	 * Apply command.
	 *
	 * @param assignColorCommand the assign color command
	 */
	public void applyCommand(AssignColorCommand assignColorCommand) {
		userInterface.assignColor(assignColorCommand.getColor());
	}

	/**
	 * Apply command.
	 *
	 * @param askMoveCommand the ask move command
	 */
	public void applyCommand(AskMoveCommand askMoveCommand) {
		userInterface.askMove();
	}

	/**
	 * Apply command.
	 *
	 * @param askFinishRoundOrDiscardCommand the ask finish round or discard command
	 */
	public void applyCommand(AskFinishRoundOrDiscardCommand askFinishRoundOrDiscardCommand) {
		userInterface.askFinishRoundOrDiscard();
	}
	
	/**
	 * Apply command.
	 *
	 * @param command the command
	 */
	public void applyCommand(InvalidActionCommand command){
		userInterface.actionCommandNotValid(command.getInvalidCode());
	}

	public void applyCommand(AuthenticatedCorrectlyCommand authenticatedCorrectlyCommand) {
		userInterface.authenticatedCorrectly(authenticatedCorrectlyCommand.getUsername());
	}

	public void applyCommand(WrongPasswordCommand wrongPasswordCommand) {
		userInterface.displayWrongPasswordMessage(wrongPasswordCommand.getUsername());
	}

	
	//TODO the applyCommand() for each Command from Server to Client we define	
}