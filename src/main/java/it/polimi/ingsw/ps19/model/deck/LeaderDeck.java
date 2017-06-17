package it.polimi.ingsw.ps19.model.deck;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import it.polimi.ingsw.ps19.constant.CardConstants;
import it.polimi.ingsw.ps19.constant.FileConstants;
import it.polimi.ingsw.ps19.model.card.LeaderCard;

/**
 * @author matteo
 *
 */
public class LeaderDeck  {
	
	private LeaderCard[] cards;
	
	public LeaderDeck() throws IOException{
		cards = DeckCreator.createLeaderCardDeck(FileConstants.LEADERCARDS, CardConstants.LEADER_DECK_LENGTH);
		System.out.println("leaderdeck: creato deck tramite deck creator");
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
	
	public LeaderCard getCard(String name){
		for(LeaderCard c : this.cards){
			if(c.getName().equals(name)){
				return c;
			}
		}
		return null;
	}
	
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
		
		System.out.println("dio caneeeee"+box.get(0).get(0).toString());
		
		return (ArrayList<ArrayList<LeaderCard>>) box;
	}

}
