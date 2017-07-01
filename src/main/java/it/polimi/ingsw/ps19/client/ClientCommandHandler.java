package it.polimi.ingsw.ps19.client;


import it.polimi.ingsw.ps19.command.toclient.AskAuthenticationCommand;
import it.polimi.ingsw.ps19.command.toclient.AskFinishRoundOrDiscardCommand;
import it.polimi.ingsw.ps19.command.toclient.AskForExcommunicationPaymentCommand;
import it.polimi.ingsw.ps19.command.toclient.AskMoveCommand;
import it.polimi.ingsw.ps19.command.toclient.AskPrivilegeChoiceCommand;
import it.polimi.ingsw.ps19.command.toclient.AssignColorCommand;
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
import it.polimi.ingsw.ps19.network.NetworkInterface;
import it.polimi.ingsw.ps19.view.UserInterface;

/**
 * @author matteo
 *
 *  this object modifies the view of the client by applying commands
 */
public class ClientCommandHandler implements ServerToClientCommandObserver{

	private UserInterface userInterface;
	private NetworkInterface networkInterface;
	
	public ClientCommandHandler(UserInterface ui, NetworkInterface networkInterface){
		this.userInterface = ui;
		this.networkInterface = networkInterface;
	}

	public void applyCommand(InvalidCommand invalidCommand) {
		userInterface.commandNotValid();
		
	}

	public void applyCommand(StartTurnCommand startTurnCommand) {
		System.out.println("clientcommandhandler: arrivato startturn command");
		userInterface.startTurn();
		
	}

	public void applyCommand(CloseClientCommand closeConnectionCommand) {
		networkInterface.closeConnection();
		
	}

	public void applyCommand(AskPrivilegeChoiceCommand askPrivilegeChoiceCommand) {
		userInterface.AskPrivilegeChoice(askPrivilegeChoiceCommand.getNumberOfPrivilege(), askPrivilegeChoiceCommand.getPrivilegeResources());
		
	}

	public void applyCommand(InitializeMatchCommand initializeMatchCommand) {
		userInterface.initializeMatch();
		
	}

	public void applyCommand(WinCommand winCommand) {
		userInterface.win();
		
	}

	public void applyCommand(LoseCommand loseCommand) {
		userInterface.lose();
		
	}

	@Override
	public void notifyNewCommand(ServerToClientCommand serverToClientCommand) {
		serverToClientCommand.processCommand(this);
		
	}

	public void applyCommand(NotifyExcommunicationCommand notifyExcommunicationCommand) {
		userInterface.notifyExcommunication();
		
	}

	public void applyCommand(ChooseLeaderCardCommand chooseLeaderCardCommand) {
		userInterface.startDraft(chooseLeaderCardCommand.getPossibleChoices());
	}

	public void applyCommand(AskForExcommunicationPaymentCommand solveExcommunicationCommand) {
		userInterface.askForExcommunicationPayment(solveExcommunicationCommand.getExcommunicationEffect());
	}

	public void applyCommand(AskAuthenticationCommand askAuthenticationCommand) {
		userInterface.askNameAndPassword();
	}

	public void applyCommand(PlayerStatusChangeCommand playerStatusChangeCommand) {
		userInterface.playerStatusChange(playerStatusChangeCommand.getPlayer());
	}
	
	public void applyCommand(InitializeTurnCommand initializeTurnCommand){
		userInterface.initializeTurn( initializeTurnCommand.getPeriod(), initializeTurnCommand.getTurn());
	}
	
	public void applyCommand(OpponentStatusChangeCommand opponentStatusChangeCommand){
		userInterface.opponentStatusChanged(opponentStatusChangeCommand.getMaskedPlayer());
	}

	public void applyCommand(ChatMessageServerCommand chatMessageServerCommand) {
		userInterface.newChatMessage(chatMessageServerCommand.getText());
		
	}

	public void applyCommand(ChooseProductionExchangeEffectsCommand chooseProductionExchangeEffectsCommand) {
		userInterface.askForProductionExchangeEffect(chooseProductionExchangeEffectsCommand.getChoices());
		
	}

	public void applyCommand(RefreshBoardCommand refreshBoardCommand) {
		userInterface.refreshBoard(refreshBoardCommand.getBoard());
		
	}

	public void applyCommand(RoundTimerExpiredCommand roundTimerExpiredCommand) {
		userInterface.notifyRoundTimerExpired();
		
	}
	

	public void applyCommand(AssignColorCommand assignColorCommand) {
		userInterface.assignColor(assignColorCommand.getColor());
	}

	public void applyCommand(AskMoveCommand askMoveCommand) {
		userInterface.askMove();
	}

	public void applyCommand(AskFinishRoundOrDiscardCommand askFinishRoundOrDiscardCommand) {
		userInterface.askFinishRoundOrDiscard();
	}
	
	public void applyCommand(InvalidActionCommand command){
		userInterface.actionCommandNotValid(command.getInvalidCode());
	}

	
	//TODO the applyCommand() for each Command from Server to Client we define	
}