package it.polimi.ingsw.ps19.model.deck;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import it.polimi.ingsw.ps19.constant.CardConstants;
import it.polimi.ingsw.ps19.constant.FileConstants;
import it.polimi.ingsw.ps19.model.card.LeaderCard;

/**
 * The Class LeaderDeck.
 *
 * @author matteo
 */
public class LeaderDeck implements Serializable  {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6540351317616183197L;
	
	/** The cards. */
	private LeaderCard[] cards;
	
	/**
	 * Instantiates a new leader deck.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public LeaderDeck() throws IOException{
		cards = DeckCreator.createLeaderCardDeck(FileConstants.LEADERCARDS, CardConstants.LEADER_DECK_LENGTH);
	}
	
	/**
	 * Shuffle deck.
	 */
	public void shuffleDeck() {
		Random rnd = new Random();

		for (int i = cards.length; i > 1; i--)
			swap(cards, i - 1, rnd.nextInt(i));

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
		System.out.println(this.cards[i].getName());
		
	}
	
	/**
	 * Gets the card.
	 *
	 * @param i the i
	 * @return LeaderCard
	 */
	public LeaderCard getCard(int i) {  
//		this.cards[i];
		return this.cards[i];
	}
	
	/**
	 * Gets the card.
	 *
	 * @param name the name
	 * @return the card
	 */
	public LeaderCard getCard(String name){
		for(LeaderCard c : this.cards){
			if(c.getName().equals(name)){
				return c;
			}
		}
		return null;
	}
	
	/**
	 * Gets the starting leader sets.
	 *
	 * @param numberOfPlayers the number of players
	 * @return the starting leader sets
	 */
	public ArrayList<ArrayList<LeaderCard>> getStartingLeaderSets(int numberOfPlayers){
		this.shuffleDeck();
		int a=1;
		int i=0;
	
		List<ArrayList<LeaderCard>> box = new ArrayList<ArrayList<LeaderCard>>();
		for(int j = 0; j<numberOfPlayers; j++){
			List<LeaderCard> cards = new ArrayList<LeaderCard>();
			for(; i < 4*a; i++){
				cards.add(this.getCard(i));
			}
			box.add((ArrayList<LeaderCard>) cards);
			a++;
		}
		
		
		return (ArrayList<ArrayList<LeaderCard>>) box;
	}

}
