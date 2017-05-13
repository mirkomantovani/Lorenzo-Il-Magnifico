package it.polimi.ingsw.ps19.model.deck;

import java.io.IOException;

import it.polimi.ingsw.ps19.model.card.CharacterCard;

/**
 * The Class CharacterDeck.
 
 *
 * @author Mirko
 *
 */
public class CharacterDeck extends Deck<CharacterCard> {

	/** The cards. */
	private CharacterCard[] cards;
	
	/**
	 * Instantiates a new character deck.
	 *
	 * @param filePath the file path
	 * @param deckLength the deck length
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public CharacterDeck(String filePath, int deckLength) throws IOException {
		cards=DeckCreator.createCharacterCardDeck(filePath, deckLength);
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.deck.Deck#length()
	 */
	@Override
	public int length() {
		return this.cards.length;
	}


}
