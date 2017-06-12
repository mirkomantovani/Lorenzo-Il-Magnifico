package it.polimi.ingsw.ps19.command;

import it.polimi.ingsw.ps19.client.ClientCommandHandler;

public class StartTurnCommand extends ServerToClientCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6276314088628114292L;

	@Override
	public void processCommand(ClientCommandHandler clientCommandHandler) {
		clientCommandHandler.applyCommand(this);
		
	}

}
