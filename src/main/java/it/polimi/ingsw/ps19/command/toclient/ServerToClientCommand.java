package it.polimi.ingsw.ps19.command.toclient;

import java.io.Serializable;

import it.polimi.ingsw.ps19.client.ClientCommandHandler;

/**
 * @author matteo
 * 
 * this class represent the genric command from server to client
 *
 */
public abstract class ServerToClientCommand implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6460847901998831472L;
	
	public abstract void processCommand(ClientCommandHandler clientCommandHandler);

}
