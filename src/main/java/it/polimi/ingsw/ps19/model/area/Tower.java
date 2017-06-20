package it.polimi.ingsw.ps19.model.area;


import java.io.Serializable;
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
public class Tower implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5976389942566908672L;

	private List<Floor> floors;

	private CardType cardType;
	
	private Deck<? extends DevelopmentCard> deck;
	
	private int currentCard = 0; // index of the card on the top of the deck
	
	private int actionSpaceCost = 1;
	
	
 	public Tower(CardType cardType, Deck<? extends DevelopmentCard> deck,ArrayList<Resource> bonuses){ 
 			floors=new ArrayList<Floor>();
			this.cardType = cardType;
			this.deck = deck;
			
			ResourceChest r;

			for(int i = 0; i < deck.length() / 6; i++){
				r = new ResourceChest();
				r.addResource(bonuses.get(i));
				floors.add(new Floor(null,this,actionSpaceCost,new InstantResourcesEffect(r)));
				actionSpaceCost = actionSpaceCost + 2;
			}
			
	} 
 	
 	
 
 	/**
 	 * This method places one Card in each floor, starting by the card currently on the top of it.
 	 * 
 	 */
 	public void changeCards(){
 		for(int i=0; i < deck.length()/6; i++){
 			floors.get(i).setCard(deck.getCard(currentCard)); 
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

	public Floor getFloor(int index) {
		return floors.get(index);
	}

	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append( this.cardType.toString().toUpperCase() + " tower : ");
				for(int i = 0; i < floors.size(); i++){
					if(this.getFloor(i).getCard() != null){
				string.append(" \n" + i + " floor: the Dice value required is " +
					this.getFloor(i).getActionSpace().getActionValueRequired()
						+ ".\nYou can take the card : "  + 
				 this.getFloor(i).getCard().toString());
					} else string.append("\n" + i + "floor: ");
				}
		return string.toString();
	}
 	
	
	
}
