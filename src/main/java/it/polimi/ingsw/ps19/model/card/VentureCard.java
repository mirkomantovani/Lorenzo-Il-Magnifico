package it.polimi.ingsw.ps19.model.card;

import it.polimi.ingsw.ps19.Period;
import it.polimi.ingsw.ps19.model.effect.Effect;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;


/**
 * A card of type Venture

 * @author Mirko
 *
 */
public class VentureCard extends DevelopmentCard {
	
	

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3369173763705272528L;

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
	public VentureCard(int id, String name, Period period, ResourceChest cost, Effect immediateEffect,
			Effect permanentEffect) {
		super(id, name, period, cost, immediateEffect, permanentEffect);
		this.cardType=CardType.VENTURE;
	}
	
	/**
	 * Select cost.
	 *
	 * @param cost the cost
	 * @param alternativeCost the alternative cost
	 * @param choice the choice
	 * @return the resource chest
	 */
	public ResourceChest selectCost(ResourceChest cost, ResourceChest alternativeCost, int choice){
		switch(choice){
		case 1: return cost;
		case 2: return alternativeCost;
		default: return cost;
		}
	}
	

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.card.DevelopmentCard#toString()
	 */
	@Override
	public String toString() {
		
    	StringBuilder string = new StringBuilder();
    	string.append(super.toString() + "\nPermanent effect: ");
    	if(this.permanentEffect!=null)
    		string.append(permanentEffect.toString());
    	
    	return string.toString();
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.card.DevelopmentCard#getActivationCost()
	 */
	@Override
	public int getActivationCost() {
		return -1;
	}

	

}
