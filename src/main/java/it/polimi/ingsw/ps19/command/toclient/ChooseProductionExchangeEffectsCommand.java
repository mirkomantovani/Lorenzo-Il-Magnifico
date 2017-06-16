package it.polimi.ingsw.ps19.command.toclient;

import java.util.List;

import it.polimi.ingsw.ps19.client.ClientCommandHandler;

/**
 * This command asks the player that requested a production action to choose between
 * the various resource exchange effects that the cards he owns have
 * @author Mirko
 *
 */
public class ChooseProductionExchangeEffectsCommand extends ServerToClientCommand {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5781592866678355190L;
	
	List<String[]> choices;
	
	public ChooseProductionExchangeEffectsCommand(List<String[]> choices){
		this.choices=choices;
	}
	
	public List<String[]> getChoices() {
		return choices;
	}

	@Override
	public void processCommand(ClientCommandHandler clientCommandHandler) {

	}

}
