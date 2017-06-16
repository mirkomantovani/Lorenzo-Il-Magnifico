package it.polimi.ingsw.ps19.command.toclient;

import it.polimi.ingsw.ps19.client.ClientCommandHandler;

/**
 * @author matteo
 * the command to notify the client about an invalid action
 *
 */
public class InvalidActionCommand extends ServerToClientCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6304179008470498378L;
	
	public InvalidActionCommand(){
	
	}

	@Override
	public void processCommand(ClientCommandHandler clientCommandHandler) {
		clientCommandHandler.applyCommand(this);
		
	}
	
	
}
