package it.polimi.ingsw.ps19.model.area;


import it.polimi.ingsw.ps19.model.card.CardType;
import it.polimi.ingsw.ps19.model.effect.InstantResourcesEffect;
import it.polimi.ingsw.ps19.model.card.DevelopmentCard;

/**
 * @author matteo
 *
 */
public class Tower {
	
	Floor firstFloor;
	Floor secondFloor;
	Floor thirdFloor;
	Floor fourthFloor;
	
	CardType cardType;
	
	DevelopmentCard[] deck;
	
	//todo
 	public Tower(CardType cardType, DevelopmentCard[] deck, InstantResourcesEffect thirdFloorEffect, InstantResourcesEffect fourthFloorEffect ){ 
			this.cardType = cardType;
			this.deck = deck;
			
			firstFloor = new Floor(null,null,1,null);
			secondFloor = new Floor(null,null,3,null);
			thirdFloor = new Floor(null,null,5,thirdFloorEffect);
			fourthFloor = new Floor(null,null,7,fourthFloorEffect);
	
	} 

}
