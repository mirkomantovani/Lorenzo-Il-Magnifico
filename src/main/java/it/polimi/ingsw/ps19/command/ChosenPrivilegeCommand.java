package it.polimi.ingsw.ps19.command;

import it.polimi.ingsw.ps19.server.ServerCommandHandler;

public class ChosenPrivilegeCommand extends ClientToServerCommand{

	/**
	 * 
	 */
	private static final long serialVersionUID = -294023756405550321L;

	@Override
	public void processCommand(ServerCommandHandler serverHandlerCommand) {
		serverHandlerCommand.applyCommand(this);
		
	}

}
