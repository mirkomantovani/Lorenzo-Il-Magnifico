package it.polimi.ingsw.ps19.model.card;

import it.polimi.ingsw.ps19.Period;
import it.polimi.ingsw.ps19.model.effect.CardEffect;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;


/**
 * The Class VentureCard.
 */
/**
 * @author Mirko
 *
 */
public class VentureCard extends DevelopmentCard {
	
	

	/**
	 * Instantiates a new venture card.
	 *
	 * @param id the id
	 * @param name the name
	 * @param period the period
	 * @param cost the cost
	 * @param immediateEffect the immediate effect
	 * @param permanentEffect the permanent effect
	 */
	public VentureCard(int id, String name, Period period, ResourceChest cost, CardEffect immediateEffect,
			CardEffect permanentEffect) {
		super(id, name, period, cost, immediateEffect, permanentEffect);
		this.cardType=CardType.VENTURE;
	}
	
	public ResourceChest selectCost(ResourceChest cost, ResourceChest alternativeCost, int choice){
		switch(choice){
		case 1: return cost;
		case 2: return alternativeCost;
		default: return cost;
		}
	}

	

}
