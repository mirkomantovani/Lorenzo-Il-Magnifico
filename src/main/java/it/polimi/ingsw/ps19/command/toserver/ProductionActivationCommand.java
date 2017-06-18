package it.polimi.ingsw.ps19.command.toserver;

import it.polimi.ingsw.ps19.server.ServerCommandHandler;

/**
 * This command is sent when the user has already chosen between
 * the various resource exchange effects that the cards he owns have
 * @author Mirko
 *
 */
public class ProductionActivationCommand extends ClientToServerCommand {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7717186073952046332L;
	
	/**
	 * array of choices: 1=normal exchange effect, 2= alternative
	 * The order has to be the same of the order of the building cards which have the
	 * exchange choice in the player's building deck
	 */
	int[] choices;
	

	@Override
	public void processCommand(ServerCommandHandler serverHandlerCommand) {
		serverHandlerCommand.applyCommand(this);
	}

}
