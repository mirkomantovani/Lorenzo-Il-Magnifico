package it.polimi.ingsw.ps19.command.toclient;

import it.polimi.ingsw.ps19.client.ClientCommandHandler;

/**
 * The Class AskMoveCommand.
 */
public class AskMoveCommand extends ServerToClientCommand {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6651993894417335857L;

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.command.toclient.ServerToClientCommand#processCommand(it.polimi.ingsw.ps19.client.ClientCommandHandler)
	 */
	@Override
	public void processCommand(ClientCommandHandler clientCommandHandler) {
		clientCommandHandler.applyCommand(this);
	}

}
