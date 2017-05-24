package it.polimi.ingsw.ps19.model.deck;

import java.io.IOException;

import it.polimi.ingsw.ps19.model.card.BuildingCard;

/**
 * The Class BuildingDeck.

*
* @author Mirko
*
*/
public class BuildingDeck extends Deck<BuildingCard> {

	
	
	/**
	 * Instantiates a new building deck.
	 *
	 * @param filePath the file path
	 * @param deckLength the deck length
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public BuildingDeck(String filePath, int deckLength) throws IOException {
		cards=DeckCreator.createBuildingCardDeck(filePath, deckLength);
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.deck.Deck#length()
	 */
	@Override
	public int length() {
		return this.cards.length;
	}
	
	public void printCardInfo(int i){
		System.out.println(this.cards[i].getId());
		
	}


}
