package it.polimi.ingsw.ps19.model.deck;

import java.io.Serializable;
import java.util.Random;

import it.polimi.ingsw.ps19.Period;
import it.polimi.ingsw.ps19.model.card.DevelopmentCard;

/**
 * A generic deck of <T> type where <T> extends a Development Card
 *
 * @author Mirko
 * @param <T> the generic type
 */
public abstract class Deck<T extends DevelopmentCard> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1517970109429683372L;
	
	/** The cards. */
	protected T[] cards;

	/**
	 * This method shuffles the deck mantaining the Periods' order (FIRST Period
	 * in from 0 to cards.length-1 etc. The code is similar to the one used by
	 * Collections, see Collections.shuffle
	 */
	public void shuffleDeck() {
		Random rnd = new Random();

		for (int i = cards.length / Period.values().length; i > 1; i--)
			swap(cards, i - 1, rnd.nextInt(cards.length / Period.values().length));
		for (int i = 2 * cards.length / Period.values().length; i > cards.length / Period.values().length + 1; i--)
			swap(cards, i - 1, (rnd.nextInt(cards.length / Period.values().length))+cards.length / Period.values().length);
		for (int i = cards.length; i > 2 * cards.length / Period.values().length + 1; i--)
			swap(cards, i - 1, (rnd.nextInt(cards.length / Period.values().length))+cards.length / Period.values().length);
	}

	/**
	 * Swap.
	 *
	 * @param arr the arr
	 * @param i the i
	 * @param j            swaps cards in index i and j
	 */
	private static void swap(Object[] arr, int i, int j) {
		Object tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	/**
	 * Length.
	 *
	 * @return the int
	 */
	public int length(){
		return this.cards.length;
	}
	
	/**
	 * Prints the card info.
	 *
	 * @param i the i
	 */
	public void printCardInfo(int i){
		System.out.println(this.cards[i].getId());
		
	}
	
	
	/**
	 * Gets the card.
	 *
	 * @param i the i
	 * @return dynamic type T extends DevelopmentCard
	 */
	public T getCard(int i) {  

		return this.cards[i];
	}
	
}
