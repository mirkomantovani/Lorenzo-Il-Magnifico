package it.polimi.ingsw.ps19.command.toclient;

import it.polimi.ingsw.ps19.client.ClientCommandHandler;

public class NotifySatanAction extends ServerToClientCommand{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1233194644751446601L;

	@Override
	public void processCommand(ClientCommandHandler clientCommandHandler) {
		clientCommandHandler.applyCommand(this);
		
	}
	
	

}
