package it.polimi.ingsw.ps19.command.toserver;

import java.io.Serializable;

import it.polimi.ingsw.ps19.server.ServerCommandHandler;

/**
 * @author matteo
 * this class represent a generic command that a client needs to submit to the server
 *
 */
public abstract class ClientToServerCommand implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6514178874808077590L;

	public abstract void processCommand(ServerCommandHandler serverHandlerCommand);

}
