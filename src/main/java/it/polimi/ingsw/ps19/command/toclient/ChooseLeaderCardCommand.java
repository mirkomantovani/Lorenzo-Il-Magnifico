package it.polimi.ingsw.ps19.command.toclient;

import java.util.ArrayList;

import it.polimi.ingsw.ps19.client.ClientCommandHandler;
import it.polimi.ingsw.ps19.model.card.LeaderCard;

/**
 * @author matteo
 * At the beginning of the match each player has to choose four leader cards in four different steps, this class 
 * represent the server request to choose a card for each step. 
 *
 */
public class ChooseLeaderCardCommand extends ServerToClientCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5837987134598037454L;
	
	private ArrayList<LeaderCard> possibleChoices;
	
	public ChooseLeaderCardCommand(ArrayList<LeaderCard> possibleChoices){
		
		System.out.println("ciao");
		if(possibleChoices.size()==0)
		System.out.println("size è a 0");
//		System.out.println("chooseleadercardcommand: creato comando da inviare"+possibleChoices.get(0).toString());
		
		this.possibleChoices = possibleChoices;
	}

	@Override
	public void processCommand(ClientCommandHandler clientCommandHandler) {
		clientCommandHandler.applyCommand(this);
		
	}

	public ArrayList<LeaderCard> getPossibleChoices() {
		return possibleChoices;
	}
	
	

}
