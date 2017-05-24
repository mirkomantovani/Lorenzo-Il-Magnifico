package it.polimi.ingsw.ps19.model.deck;

import java.io.IOException;
import java.util.Random;

import it.polimi.ingsw.ps19.model.card.LeaderCard;

/**
 * @author matteo
 *
 */
public class LeaderDeck  {
	
	LeaderCard[] cards;
	
	public LeaderDeck(String filePath, int deckLength) throws IOException{
		cards = DeckCreator.createLeaderCardDeck(filePath, deckLength);
	}
	
	public void shuffleDeck() {
		Random rnd = new Random();

		for (int i = cards.length; i > 1; i--)
			swap(cards, i - 1, rnd.nextInt(i));

	}
	
	/**
	 * @param arr
	 * @param i
	 * @param j
	 *            swaps cards in index i and j
	 */
	private static void swap(Object[] arr, int i, int j) {
		Object tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
	
	public int length(){
		return this.cards.length;
	}
	
	public void printCardInfo(int i){
		System.out.println(this.cards[i].getName());
		
	}
	
	/**
	 * @param i
	 * @return LeaderCard
	 */
	public LeaderCard getCard(int i) {  
//		System.out.println(this.cards[i]);
//		this.cards[i];
		return this.cards[i];
	}

}
