package it.polimi.ingsw.ps19.command.toclient;

import java.util.ArrayList;

import it.polimi.ingsw.ps19.client.ClientCommandHandler;
import it.polimi.ingsw.ps19.model.card.LeaderCard;

/**
 * The Class ChooseLeaderCardCommand.
 *
 * @author matteo
 * At the beginning of the match each player has to choose four leader cards in four different steps, this class 
 * represent the server request to choose a card for each step. 
 */
public class ChooseLeaderCardCommand extends ServerToClientCommand {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5837987134598037454L;
	
	/** The possible choices. */
	private ArrayList<LeaderCard> possibleChoices;
	
	/**
	 * Instantiates a new choose leader card command.
	 *
	 * @param possibleChoices the possible choices
	 */
	public ChooseLeaderCardCommand(ArrayList<LeaderCard> possibleChoices){
		
		
		if(possibleChoices.size()==0)
		
		
		this.possibleChoices = possibleChoices;
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.command.toclient.ServerToClientCommand#processCommand(it.polimi.ingsw.ps19.client.ClientCommandHandler)
	 */
	@Override
	public void processCommand(ClientCommandHandler clientCommandHandler) {
		clientCommandHandler.applyCommand(this);
		
	}

	/**
	 * Gets the possible choices.
	 *
	 * @return the possible choices
	 */
	public ArrayList<LeaderCard> getPossibleChoices() {
		return possibleChoices;
	}
	
	

}
