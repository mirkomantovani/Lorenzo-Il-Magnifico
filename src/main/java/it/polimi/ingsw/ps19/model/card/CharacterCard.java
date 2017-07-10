package it.polimi.ingsw.ps19.model.card;

import it.polimi.ingsw.ps19.model.Period;
import it.polimi.ingsw.ps19.model.effect.Effect;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;



/**
 * A card of type Character
 *
 * @author Mirko
 */
public class CharacterCard extends DevelopmentCard {
	
	//we have to pass some null values to immediate or permanent effect of some of these cards because some of them don't have both

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8075237121381367847L;

	/**
	 * Instantiates a new character card.
	 *
	 * @param id the id
	 * @param name the name
	 * @param period the period
	 * @param cost the cost
	 * @param immediateEffect the immediate effect
	 * @param permanentEffect the permanent effect
	 */
	public CharacterCard(int id, String name, Period period, ResourceChest cost, Effect immediateEffect,
			Effect permanentEffect) {
		super(id, name, period, cost, immediateEffect, permanentEffect);
		this.cardType=CardType.CHARACTER;
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
