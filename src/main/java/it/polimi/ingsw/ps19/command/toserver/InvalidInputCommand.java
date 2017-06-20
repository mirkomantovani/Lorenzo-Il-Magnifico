package it.polimi.ingsw.ps19.command.toserver;

import it.polimi.ingsw.ps19.server.ServerCommandHandler;

public class InvalidInputCommand extends ClientToServerCommand{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4017180267188280820L;


	@Override
	public void processCommand(ServerCommandHandler serverHandlerCommand) {
		serverHandlerCommand.applyCommand(this);
	}

}
