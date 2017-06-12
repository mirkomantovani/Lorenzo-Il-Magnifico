package it.polimi.ingsw.ps19.command;

import it.polimi.ingsw.ps19.client.ClientCommandHandler;

public class CloseConnectionCommand extends ServerToClientCommand{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5803857804263612009L;

	@Override
	public void processCommand(ClientCommandHandler clientCommandHandler) {
		clientCommandHandler.applyCommand(this);
		
	}

}
