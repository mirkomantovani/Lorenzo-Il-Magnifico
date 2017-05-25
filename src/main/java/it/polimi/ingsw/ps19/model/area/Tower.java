package it.polimi.ingsw.ps19.model.area;


import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.ps19.model.card.CardType;
import it.polimi.ingsw.ps19.model.card.DevelopmentCard;
import it.polimi.ingsw.ps19.model.deck.Deck;

/**
 * @author matteo
 *
 */
public class Tower {
	
	private List<Floor> floors;

	private CardType cardType;
	
	private Deck<DevelopmentCard> deck;
	
	private static int currentCard = 0; // index of the card on the top of the deck
	
	
 	public Tower(CardType cardType, Deck<DevelopmentCard> deck){ 
 			floors=new ArrayList<Floor>();
			this.cardType = cardType;
			this.deck = deck;
			
			for(int i = 0; i < deck.length() / 6; i++){
				floors.add(new Floor(deck.getCard(currentCard),this,0,null));
				currentCard++;
				
			}
	
	
	} 
 	
 	/**
 	 * This method places one Card in each floor, starting by the card currently on the top of it.
 	 * 
 	 */
 	public void setCards(){
 		for(int i=0; i < deck.length()/6; i++){
 			Floor floor = new Floor(deck.getCard(currentCard),this,0,null);
 			floors.set(i,floor);
 			currentCard++;
 		}
 	}
 	
 	/**
 	 * 
 	 * This Method builds an Array with the cards in the tower and returns it
 	 * 
 	 * @return
 	 */
 	public ArrayList<DevelopmentCard> getCards(){
 		
 		ArrayList<DevelopmentCard> towerCards = new ArrayList<DevelopmentCard>();
 		
 		for(Floor f : floors){
 			towerCards.add(f.card);
 		}
 		
 		return towerCards;
 	}
 	
 	public List<Floor> getFloors() {
		return floors;
	}
	
}
