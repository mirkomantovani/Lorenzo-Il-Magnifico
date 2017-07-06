package it.polimi.ingsw.ps19.model.deck;

import java.io.IOException;

import it.polimi.ingsw.ps19.model.card.CharacterCard;

/**
 * A deck of character cards
 
 *
 * @author Mirko
 *
 */
public class CharacterDeck extends Deck<CharacterCard> {

	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6919342650647211328L;

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
	
	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.deck.Deck#printCardInfo(int)
	 */
	public void printCardInfo(int i){
		System.out.println(this.cards[i].getId());
		
	}

}
