package it.polimi.ingsw.ps19.model.card;

import it.polimi.ingsw.ps19.Period;
import it.polimi.ingsw.ps19.model.effect.Effect;

/**
 * The Class TerritoryCard.
 *
 * @author Mirko
 */
public class TerritoryCard extends DevelopmentCard {
    
    /** The harvest activation cost. */
    //this class should have something like HarvestEffect and not a permanentEffect, waiting for the Effect Generalization modeling
	private int harvestActivationCost;
	
	/**
	 * Instantiates a new territory card.
	 *
	 * @param id the id
	 * @param name the name
	 * @param period the period
	 * @param immediateEffect the immediate effect
	 * @param permanentEffect the permanent effect
	 * @param harvestActivationCost the harvest activation cost
	 */
	public TerritoryCard(int id, String name, Period period, Effect immediateEffect,
			Effect permanentEffect,int harvestActivationCost) {
		super(id, name, period, null, immediateEffect, permanentEffect);
		this.harvestActivationCost=harvestActivationCost;
	}

    /**
     * Can activate harvest with.
     *
     * @param harvestValue the harvest value
     * @return true, if successful
     */
    public boolean canActivateHarvestWith(int harvestValue){
    	return harvestValue>harvestActivationCost;
		
	}
	

}
