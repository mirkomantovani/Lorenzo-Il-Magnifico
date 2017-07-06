package it.polimi.ingsw.ps19.model.deck;

import java.io.IOException;

import it.polimi.ingsw.ps19.model.card.VentureCard;

/**
 * The Class VentureDeck.

 * @author Mirko
 *
 */
public class VentureDeck extends Deck<VentureCard> {


	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2801964840693027773L;

	/**
	 * Instantiates a new venture deck.
	 *
	 * @param filePath the file path
	 * @param deckLength the deck length
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public VentureDeck(String filePath, int deckLength) throws IOException {
		cards=DeckCreator.createVentureCardDeck(filePath, deckLength);
		
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
