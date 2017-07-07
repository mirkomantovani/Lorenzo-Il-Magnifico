package it.polimi.ingsw.ps19.command.toclient;

import it.polimi.ingsw.ps19.client.ClientCommandHandler;

/**
 * The Class WinCommand.
 *
 * @author matteo
 * this command notify the winner client 
 */
public class WinCommand extends ServerToClientCommand{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2918136961631454765L;

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.command.toclient.ServerToClientCommand#processCommand(it.polimi.ingsw.ps19.client.ClientCommandHandler)
	 */
	@Override
	public void processCommand(ClientCommandHandler clientCommandHandler) {
		clientCommandHandler.applyCommand(this);
		
	}

}
