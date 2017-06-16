package it.polimi.ingsw.ps19.client;


import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.command.toclient.AskAuthenticationCommand;
import it.polimi.ingsw.ps19.command.toclient.AskPrivilegeChoiceCommand;
import it.polimi.ingsw.ps19.command.toclient.ChooseLeaderCardCommand;
import it.polimi.ingsw.ps19.command.toclient.CloseClientCommand;
import it.polimi.ingsw.ps19.command.toclient.InitializeMatchCommand;
import it.polimi.ingsw.ps19.command.toclient.InvalidActionCommand;
import it.polimi.ingsw.ps19.command.toclient.InvalidCommand;
import it.polimi.ingsw.ps19.command.toclient.LoseCommand;
import it.polimi.ingsw.ps19.command.toclient.NotifyExcommunicationCommand;
import it.polimi.ingsw.ps19.command.toclient.ServerToClientCommand;
import it.polimi.ingsw.ps19.command.toclient.StartTurnCommand;
import it.polimi.ingsw.ps19.command.toclient.WinCommand;
import it.polimi.ingsw.ps19.command.toserver.SolveExcommunicationCommand;
import it.polimi.ingsw.ps19.model.area.Board;
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

	public void applyCommand(InvalidActionCommand ActionNotValidCommand) {
		
		
	}

	public void applyCommand(InvalidCommand invalidCommand) {
		userInterface.commandNotValid();
		
	}

	public void applyCommand(StartTurnCommand startTurnCommand) {
		// TODO Auto-generated method stub
		userInterface.startTurn();
		
	}

	public void applyCommand(CloseClientCommand closeConnectionCommand) {
		networkInterface.closeConnection();
		
	}

	public void applyCommand(AskPrivilegeChoiceCommand askPrivilegeChoiceCommand) {
		userInterface.AskPrivilegeChoice(askPrivilegeChoiceCommand.getNumberOfPrivilege());
		
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
		// TODO Auto-generated method stub
		
	}

	public void applyCommand(ChooseLeaderCardCommand chooseLeaderCardCommand) {
		// TODO Auto-generated method stub
		
	}

	public void applyCommand(SolveExcommunicationCommand solveExcommunicationCommand) {
		// TODO Auto-generated method stub
		
	}

	

	public void applyCommand(AskAuthenticationCommand askAuthenticationCommand) {
		// TODO Auto-generated method stub
		
	}


	
	
	
	//TODO the applyCommand() for each Command from Server to Client we define	
}
