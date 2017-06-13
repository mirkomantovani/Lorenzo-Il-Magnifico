package it.polimi.ingsw.ps19.command;

import it.polimi.ingsw.ps19.server.ServerCommandHandler;

public class RequestClosureCommand extends ClientToServerCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4077481752452935713L;

	@Override
	public void processCommand(ServerCommandHandler serverHandlerCommand) {
		serverHandlerCommand.applyCommand(this);
		
	}
	
	

}
