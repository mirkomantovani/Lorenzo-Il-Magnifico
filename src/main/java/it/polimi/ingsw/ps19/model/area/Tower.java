package it.polimi.ingsw.ps19.model.area;


import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.ps19.model.card.CardType;
import it.polimi.ingsw.ps19.model.card.DevelopmentCard;
import it.polimi.ingsw.ps19.model.deck.Deck;
import it.polimi.ingsw.ps19.model.effect.InstantResourcesEffect;
import it.polimi.ingsw.ps19.model.resource.Resource;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;

/**
 * @author matteo
 *
 */
public class Tower {
	
	private List<Floor> floors;

	private CardType cardType;
	
	private Deck<? extends DevelopmentCard> deck;
	
	private static int currentCard = 0; // index of the card on the top of the deck
	
	private static int actionSpaceCost = 1;
	
	
 	public Tower(CardType cardType, Deck<? extends DevelopmentCard> deck,ArrayList<Resource> bonuses){ 
 			floors=new ArrayList<Floor>();
			this.cardType = cardType;
			this.deck = deck;
			
			ResourceChest r;

			
			for(int i = 0; i < deck.length() / 6; i++){
				r = new ResourceChest();
				r.addResource(bonuses.get(i));
				floors.add(new Floor(deck.getCard(currentCard),this,actionSpaceCost,new InstantResourcesEffect(r)));
				currentCard++;
				actionSpaceCost = actionSpaceCost + 2;
			}
	
			actionSpaceCost = 1;
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
