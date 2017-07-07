package it.polimi.ingsw.ps19.command.toserver;

import java.io.Serializable;

import it.polimi.ingsw.ps19.server.ServerCommandHandler;

/**
 * The Class ClientToServerCommand.
 *
 * @author matteo
 * this class represent a generic command that a client needs to submit to the server
 */
public abstract class ClientToServerCommand implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6514178874808077590L;

	/**
	 * Process command.
	 *
	 * @param serverHandlerCommand the server handler command
	 */
	public abstract void processCommand(ServerCommandHandler serverHandlerCommand);

}
