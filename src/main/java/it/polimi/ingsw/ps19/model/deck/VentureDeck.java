package it.polimi.ingsw.ps19.model.deck;

import java.io.IOException;

import it.polimi.ingsw.ps19.model.card.VentureCard;

/**
 * The Class VentureDeck.

 * @author Mirko
 *
 */
public class VentureDeck extends Deck<VentureCard> {

	/** The cards. */
	private VentureCard[] cards;
	
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


}
