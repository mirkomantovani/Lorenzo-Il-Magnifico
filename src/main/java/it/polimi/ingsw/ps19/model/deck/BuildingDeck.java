package it.polimi.ingsw.ps19.model.deck;

import java.io.IOException;

import it.polimi.ingsw.ps19.model.card.BuildingCard;

/**
 * A deck of building cards
 * 
*
* @author Mirko
*
*/
public class BuildingDeck extends Deck<BuildingCard> {

	
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 626392870621207325L;

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
	
	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.deck.Deck#printCardInfo(int)
	 */
	public void printCardInfo(int i){
		System.out.println(this.cards[i].getId());
		
	}


}
