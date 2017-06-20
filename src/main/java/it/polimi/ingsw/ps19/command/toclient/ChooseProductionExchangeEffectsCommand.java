package it.polimi.ingsw.ps19.command.toclient;

import java.util.ArrayList;

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
	
	private ArrayList<String[]> choices;
	
	public ChooseProductionExchangeEffectsCommand(ArrayList<String[]> choices){
		this.choices = new ArrayList<String[]>();
		this.choices=choices;
	}
	
	public ArrayList<String[]> getChoices() {
		return choices;
	}

	@Override
	public void processCommand(ClientCommandHandler clientCommandHandler) {
		clientCommandHandler.applyCommand(this);
	}

}
