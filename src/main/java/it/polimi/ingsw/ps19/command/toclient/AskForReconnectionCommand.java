package it.polimi.ingsw.ps19.command.toclient;

import it.polimi.ingsw.ps19.client.ClientCommandHandler;

public class AskForReconnectionCommand extends ServerToClientCommand{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3161152521045309148L;

	@Override
	public void processCommand(ClientCommandHandler clientCommandHandler) {
		try {
			clientCommandHandler.applyCommand(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
