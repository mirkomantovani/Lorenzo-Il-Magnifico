package it.polimi.ingsw.ps19.command.toclient;

import it.polimi.ingsw.ps19.client.ClientCommandHandler;

/**
 * The Class LoseCommand.
 *
 * @author matteo
 * the command to notify a client that he isn't the winner of the match
 */
public class LoseCommand extends ServerToClientCommand {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4757146999670162579L;

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.command.toclient.ServerToClientCommand#processCommand(it.polimi.ingsw.ps19.client.ClientCommandHandler)
	 */
	@Override
	public void processCommand(ClientCommandHandler clientCommandHandler) {
		clientCommandHandler.applyCommand(this);
		
	}

}
