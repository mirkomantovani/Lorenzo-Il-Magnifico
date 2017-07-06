package it.polimi.ingsw.ps19.command.toclient;

import it.polimi.ingsw.ps19.client.ClientCommandHandler;

/**
 * The Class InvalidCommand.
 *
 * @author matteo
 * the command to notify the client that he sent a not valid command
 */
public class InvalidCommand extends ServerToClientCommand{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7556587540722322509L;

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.command.toclient.ServerToClientCommand#processCommand(it.polimi.ingsw.ps19.client.ClientCommandHandler)
	 */
	@Override
	public void processCommand(ClientCommandHandler clientCommandHandler) {
		clientCommandHandler.applyCommand(this);
		
	}
	
	

}
