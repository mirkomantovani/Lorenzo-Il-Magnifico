package it.polimi.ingsw.ps19.command;

import it.polimi.ingsw.ps19.client.ClientCommandHandler;

public class InitializeMatchCommand extends ServerToClientCommand{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5791396313072706548L;

	@Override
	public void processCommand(ClientCommandHandler clientCommandHandler) {
		clientCommandHandler.applyCommand(this);
		
	}
	
	

}
