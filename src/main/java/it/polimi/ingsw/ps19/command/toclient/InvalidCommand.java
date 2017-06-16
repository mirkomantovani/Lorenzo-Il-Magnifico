package it.polimi.ingsw.ps19.command.toclient;

import it.polimi.ingsw.ps19.client.ClientCommandHandler;

/**
 * @author matteo
 * the command to notify the client that he sent a not valid command
 *
 */
public class InvalidCommand extends ServerToClientCommand{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7556587540722322509L;

	@Override
	public void processCommand(ClientCommandHandler clientCommandHandler) {
		clientCommandHandler.applyCommand(this);
		
	}
	
	

}
