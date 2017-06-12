package it.polimi.ingsw.ps19.client;

import it.polimi.ingsw.ps19.command.CloseConnectionCommand;
import it.polimi.ingsw.ps19.command.InvalidActionCommand;
import it.polimi.ingsw.ps19.command.InvalidCommand;
import it.polimi.ingsw.ps19.command.StartTurnCommand;
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
		// TODO Auto-generated method stub
		
	}

	public void applyCommand(InvalidCommand invalidCommand) {
		// TODO Auto-generated method stub
		
	}

	public void applyCommand(StartTurnCommand startTurnCommand) {
		// TODO Auto-generated method stub
		
	}

	public void applyCommand(CloseConnectionCommand closeConnectionCommand) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	//TODO the applyCommand() for each Command from Server to Client we define	
}
