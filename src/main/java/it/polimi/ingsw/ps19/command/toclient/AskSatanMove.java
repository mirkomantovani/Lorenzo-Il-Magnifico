package it.polimi.ingsw.ps19.command.toclient;

import it.polimi.ingsw.ps19.client.ClientCommandHandler;

public class AskSatanMove extends ServerToClientCommand{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1303484145043675961L;

	@Override
	public void processCommand(ClientCommandHandler clientCommandHandler) {
		clientCommandHandler.applyCommand(this);
		
	}

}
