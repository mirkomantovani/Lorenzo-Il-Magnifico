package it.polimi.ingsw.ps19.model.deck;

import java.io.IOException;

import it.polimi.ingsw.ps19.model.card.BuildingCard;
import it.polimi.ingsw.ps19.model.card.CardConstants;


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
		long start = System.currentTimeMillis();

	
		
		//la linea 292 del file building è un 8 non 5 ma ho messo 5 perchè non possiamo trattare la privilege come risorsa ancora
		try {
			buildingDeck=new BuildingDeck("src/main/resources/files/filebuildingcards.txt", CardConstants.DECK_LENGTH);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("There was a fucking Error");
		}
		long end = System.currentTimeMillis();
		
		System.out.println("Time complexity: "+(end-start)+"ms");
		
		System.out.println("Building Deck built successfully");
		buildingDeck.printCardInfo(0);
		System.out.println(buildingDeck.getCard(4).toString());
//		buildingDeck.getCard(0).toString();
	}

}
