package it.polimi.ingsw.ps19.model.deck;

import java.io.IOException;

import it.polimi.ingsw.ps19.model.card.BuildingCard;
import it.polimi.ingsw.ps19.model.card.TerritoryCard;

/**
 * The Class TerritoryDeck.
 */
/**
 * @author Mirko
 *
 */
public class TerritoryDeck extends Deck<TerritoryCard> {

	/** The cards. */
	private TerritoryCard[] cards;

	/**
	 * Instantiates a new territory deck.
	 *
	 * @param filePath
	 *            the file path
	 * @param deckLength
	 *            the deck length
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public TerritoryDeck(String filePath, int deckLength) throws IOException {
		cards = DeckCreator.createTerritoryCardDeck(filePath, deckLength);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.polimi.ingsw.ps19.model.deck.Deck#length()
	 */
	@Override
	public int length() {
		return this.cards.length;
	}
}
