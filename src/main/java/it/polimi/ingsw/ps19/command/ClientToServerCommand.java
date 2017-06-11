package it.polimi.ingsw.ps19.command;

import java.io.Serializable;

import it.polimi.ingsw.ps19.server.ServerCommandHandler;

public abstract class ClientToServerCommand implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6514178874808077590L;

	public abstract void processCommand(ServerCommandHandler serverHandlerCommand);

}
