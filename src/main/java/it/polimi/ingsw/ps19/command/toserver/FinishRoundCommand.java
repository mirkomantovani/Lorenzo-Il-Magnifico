package it.polimi.ingsw.ps19.command.toserver;

import it.polimi.ingsw.ps19.server.ServerCommandHandler;

public class FinishRoundCommand extends ClientToServerCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8641006857842951634L;

	@Override
	public void processCommand(ServerCommandHandler serverHandlerCommand) {
			serverHandlerCommand.applyCommand(this);
	}

}
