package it.polimi.ingsw.ps19.model.deck;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import it.polimi.ingsw.ps19.model.card.BuildingCard;
import it.polimi.ingsw.ps19.model.card.CharacterCard;
import it.polimi.ingsw.ps19.model.card.DevelopmentCard;
import it.polimi.ingsw.ps19.model.card.TerritoryCard;
import it.polimi.ingsw.ps19.model.card.VentureCard;

/**
 * The Class DeckCreator.
 */
/**
 * @author Matteo, Jimmy, Mirko
 *
 */
public class DeckCreator {

	/** The buffered reader. */
	private static BufferedReader bufferedReader;
	
	/** The line read. */
	private static String lineRead;

	/**
	 * Creates the building card deck.
	 *
	 * @param filePath the file path
	 * @param deckLength the deck length
	 * @return the building card[]
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static BuildingCard[] createBuildingCardDeck(String filePath, int deckLength) throws IOException {

		int cardId=0;
		BuildingCard[] deck = new BuildingCard[deckLength];

		bufferedReader = new BufferedReader(new FileReader(filePath));
		lineRead = bufferedReader.readLine();    	//The lineRead variable stores the first line of a card and use it to check the while condition
		while (lineRead!=null) {
			
		}
		return deck;
	}
	
	
	
	/**
	 * Creates the territory card deck.
	 *
	 * @param filePath the file path
	 * @param deckLength the deck length
	 * @return the territory card[]
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static TerritoryCard[] createTerritoryCardDeck(String filePath, int deckLength) throws IOException {

		int cardId=0;
		TerritoryCard[] deck = new TerritoryCard[deckLength];

		bufferedReader = new BufferedReader(new FileReader(filePath));
		lineRead = bufferedReader.readLine();
		
		
		while (lineRead!=null) {

		}
		return deck;
	}
	
	/**
	 * Creates the venture card deck.
	 *
	 * @param filePath the file path
	 * @param deckLength the deck length
	 * @return the venture card[]
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static VentureCard[] createVentureCardDeck(String filePath, int deckLength) throws IOException {

		int cardId=0;
		VentureCard[] deck = new VentureCard[deckLength];

		bufferedReader = new BufferedReader(new FileReader(filePath));
		lineRead = bufferedReader.readLine();
		
		while (lineRead!=null) {
			
		}
		return deck;
	}
	
	/**
	 * Creates the character card deck.
	 *
	 * @param filePath the file path
	 * @param deckLength the deck length
	 * @return the character card[]
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static CharacterCard[] createCharacterCardDeck(String filePath, int deckLength) throws IOException {

		int cardId=0;
		CharacterCard[] deck = new CharacterCard[deckLength];

		bufferedReader = new BufferedReader(new FileReader(filePath));
		lineRead = bufferedReader.readLine();
		
		while (lineRead!=null) {
			
			cardId++;
		}
		return deck;
	}

	// private Effect createEffect(){
	//
	// }

}
