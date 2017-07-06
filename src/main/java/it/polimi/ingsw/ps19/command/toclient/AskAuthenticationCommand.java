package it.polimi.ingsw.ps19.command.toclient;

import it.polimi.ingsw.ps19.client.ClientCommandHandler;

/**
 * The Class AskAuthenticationCommand.
 */
public class AskAuthenticationCommand extends ServerToClientCommand {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2642328618210688214L;

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.command.toclient.ServerToClientCommand#processCommand(it.polimi.ingsw.ps19.client.ClientCommandHandler)
	 */
	@Override
	public void processCommand(ClientCommandHandler clientCommandHandler) {
		clientCommandHandler.applyCommand(this);

	}

}
