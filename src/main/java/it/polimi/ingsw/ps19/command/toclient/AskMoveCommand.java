package it.polimi.ingsw.ps19.command.toclient;

import it.polimi.ingsw.ps19.client.ClientCommandHandler;

public class AskMoveCommand extends ServerToClientCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6651993894417335857L;

	@Override
	public void processCommand(ClientCommandHandler clientCommandHandler) {
		clientCommandHandler.applyCommand(this);
	}

}
