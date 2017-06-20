package it.polimi.ingsw.ps19.command.toclient;

import it.polimi.ingsw.ps19.client.ClientCommandHandler;

public class AskAuthenticationCommand extends ServerToClientCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2642328618210688214L;

	@Override
	public void processCommand(ClientCommandHandler clientCommandHandler) {
		clientCommandHandler.applyCommand(this);

	}

}
