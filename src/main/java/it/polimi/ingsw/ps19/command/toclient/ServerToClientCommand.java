package it.polimi.ingsw.ps19.command.toclient;

import java.io.Serializable;

import it.polimi.ingsw.ps19.client.ClientCommandHandler;

/**
 * The Class ServerToClientCommand.
 *
 * @author matteo
 * 
 * this class represent the genric command from server to client
 */
public abstract class ServerToClientCommand implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6460847901998831472L;
	
	/**
	 * Process command.
	 *
	 * @param clientCommandHandler the client command handler
	 */
	public abstract void processCommand(ClientCommandHandler clientCommandHandler);

}
