package it.polimi.ingsw.ps19.command;

import it.polimi.ingsw.ps19.client.ClientCommandHandler;

public class WinCommand extends ServerToClientCommand{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2918136961631454765L;

	@Override
	public void processCommand(ClientCommandHandler clientCommandHandler) {
		clientCommandHandler.applyCommand(this);
		
	}

}
