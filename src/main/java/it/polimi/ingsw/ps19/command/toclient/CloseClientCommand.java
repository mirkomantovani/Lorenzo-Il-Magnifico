package it.polimi.ingsw.ps19.command.toclient;

import it.polimi.ingsw.ps19.client.ClientCommandHandler;

/**
 * @author matteo
 * 
 * this class represent the command sent from the server to the client, to notify that the server decides 
 * to remove a client from the match.
 *
 */
public class CloseClientCommand extends ServerToClientCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void processCommand(ClientCommandHandler clientCommandHandler) {
		clientCommandHandler.applyCommand(this);
		
	}
		
}
