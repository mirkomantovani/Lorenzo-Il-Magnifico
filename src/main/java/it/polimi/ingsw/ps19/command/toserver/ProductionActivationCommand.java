package it.polimi.ingsw.ps19.command.toserver;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.ps19.server.ServerCommandHandler;

/**
 * This command is sent when the user has already chosen between
 * the various resource exchange effects that the cards he owns have.
 *
 * @author Mirko
 */
public class ProductionActivationCommand extends ClientToServerCommand {

	
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7717186073952046332L;
	
	/** array of choices: 1=normal exchange effect, 2= alternative The order has to be the same of the order of the building cards which have the exchange choice in the player's building deck. */
	List<Integer> choices;
	
	/**
	 * Gets the choices.
	 *
	 * @return the choices
	 */
	public List<Integer> getChoices() {
		return choices;
	}

	/**
	 * Instantiates a new production activation command.
	 *
	 * @param choices the choices
	 */
	public ProductionActivationCommand(ArrayList<Integer> choices){
		this.choices = new ArrayList<Integer>();
		this.choices = choices;
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.command.toserver.ClientToServerCommand#processCommand(it.polimi.ingsw.ps19.server.ServerCommandHandler)
	 */
	@Override
	public void processCommand(ServerCommandHandler serverHandlerCommand) {
		serverHandlerCommand.applyCommand(this);
	}

}
