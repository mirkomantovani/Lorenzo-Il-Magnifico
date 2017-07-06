package it.polimi.ingsw.ps19.command.toserver;

import it.polimi.ingsw.ps19.server.ServerCommandHandler;

/**
 * The Class InvalidInputCommand.
 */
public class InvalidInputCommand extends ClientToServerCommand{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4017180267188280820L;


	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.command.toserver.ClientToServerCommand#processCommand(it.polimi.ingsw.ps19.server.ServerCommandHandler)
	 */
	@Override
	public void processCommand(ServerCommandHandler serverHandlerCommand) {
		serverHandlerCommand.applyCommand(this);
	}

}
