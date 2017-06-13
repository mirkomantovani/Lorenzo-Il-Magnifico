package it.polimi.ingsw.ps19.client;

import it.polimi.ingsw.ps19.command.AskPrivilegeChoiceCommand;
import it.polimi.ingsw.ps19.command.CloseClientCommand;
import it.polimi.ingsw.ps19.command.InitializeMatchCommand;
import it.polimi.ingsw.ps19.command.InvalidActionCommand;
import it.polimi.ingsw.ps19.command.InvalidCommand;
import it.polimi.ingsw.ps19.command.LoseCommand;
import it.polimi.ingsw.ps19.command.StartTurnCommand;
import it.polimi.ingsw.ps19.command.WinCommand;
import it.polimi.ingsw.ps19.network.NetworkInterface;
import it.polimi.ingsw.ps19.view.UserInterface;

/**
 * @author matteo
 *
 *  this object modifies the view of the client by applying commands
 */
public class ClientCommandHandler {

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
		
	}

	public void applyCommand(CloseClientCommand closeConnectionCommand) {
		networkInterface.closeConnection();
		
	}

	public void applyCommand(AskPrivilegeChoiceCommand askPrivilegeChoiceCommand) {
		// TODO Auto-generated method stub
		
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
	
	
	
	//TODO the applyCommand() for each Command from Server to Client we define	
}
