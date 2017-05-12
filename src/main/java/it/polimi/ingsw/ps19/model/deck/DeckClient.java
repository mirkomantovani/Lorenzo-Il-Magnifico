package it.polimi.ingsw.ps19.model.deck;

import java.io.IOException;

import it.polimi.ingsw.ps19.model.card.BuildingCard;


//solo per prova, da cancellare
/**
 * @author Mirko
 *
 */
public class DeckClient {
	

	private static Deck<BuildingCard> buildingDeck;
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		try {
			buildingDeck=new BuildingDeck(null, 0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
