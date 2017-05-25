package it.polimi.ingsw.ps19.model.deck;

import it.polimi.ingsw.ps19.model.card.TerritoryCard;

/**
 * The Class TerritoryDeck.
 

 * @author Mirko
 *
 */
public class TerritoryDeck extends Deck<TerritoryCard> {


	/**
	 * Instantiates a new territory deck.
	 *
	 * @param filePath
	 *            the file path
	 * @param deckLength
	 *            the deck length
	 * 
	 */
	public TerritoryDeck(String filePath, int deckLength) {
		
		try {
			cards = DeckCreator.createTerritoryCardDeck(filePath, deckLength);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("No territory cards without an harvest effect allowed");
		}
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
