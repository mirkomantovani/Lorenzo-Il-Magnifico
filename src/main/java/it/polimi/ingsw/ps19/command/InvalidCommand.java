package it.polimi.ingsw.ps19.command;

import it.polimi.ingsw.ps19.client.ClientCommandHandler;

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
