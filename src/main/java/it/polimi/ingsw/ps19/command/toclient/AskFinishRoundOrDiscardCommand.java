package it.polimi.ingsw.ps19.command.toclient;

import it.polimi.ingsw.ps19.client.ClientCommandHandler;

/**
 * The Class AskFinishRoundOrDiscardCommand.
 */
public class AskFinishRoundOrDiscardCommand extends ServerToClientCommand {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5372505115142988012L;

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.command.toclient.ServerToClientCommand#processCommand(it.polimi.ingsw.ps19.client.ClientCommandHandler)
	 */
	@Override
	public void processCommand(ClientCommandHandler clientCommandHandler) {
		clientCommandHandler.applyCommand(this);
	}

}
