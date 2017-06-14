package it.polimi.ingsw.ps19.command;

import it.polimi.ingsw.ps19.client.ClientCommandHandler;

public class LoseCommand extends ServerToClientCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4757146999670162579L;

	@Override
	public void processCommand(ClientCommandHandler clientCommandHandler) {
		clientCommandHandler.applyCommand(this);
		
	}

}
