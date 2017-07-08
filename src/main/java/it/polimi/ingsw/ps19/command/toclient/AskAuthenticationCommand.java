package it.polimi.ingsw.ps19.command.toclient;

import it.polimi.ingsw.ps19.client.ClientCommandHandler;

 
/**
 * This command asks the user to send his credentials to log into the game before the match starts
 * @author Mirko
 *
 */
public class AskAuthenticationCommand extends ServerToClientCommand {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2642328618210688214L;


	@Override
	public void processCommand(ClientCommandHandler clientCommandHandler) {
		clientCommandHandler.applyCommand(this);

	}

}
