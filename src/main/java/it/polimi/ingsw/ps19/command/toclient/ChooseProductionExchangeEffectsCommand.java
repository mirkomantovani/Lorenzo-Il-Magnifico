package it.polimi.ingsw.ps19.command.toclient;

import java.util.List;

import it.polimi.ingsw.ps19.client.ClientCommandHandler;

/**
 * This command asks the player that requested a production action to choose between
 * the various resource exchange effects that the cards he owns have.
 *
 * @author Mirko
 */
public class ChooseProductionExchangeEffectsCommand extends ServerToClientCommand {

	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5781592866678355190L;
	
	/** The choices. */
	List<String[]> choices;
	
	/**
	 * Instantiates a new choose production exchange effects command.
	 *
	 * @param choices the choices
	 */
	public ChooseProductionExchangeEffectsCommand(List<String[]> choices){
		this.choices=choices;
	}
	
	/**
	 * Gets the choices.
	 *
	 * @return the choices
	 */
	public List<String[]> getChoices() {
		return choices;
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.command.toclient.ServerToClientCommand#processCommand(it.polimi.ingsw.ps19.client.ClientCommandHandler)
	 */
	@Override
	public void processCommand(ClientCommandHandler clientCommandHandler) {
		clientCommandHandler.applyCommand(this);
	}

}
