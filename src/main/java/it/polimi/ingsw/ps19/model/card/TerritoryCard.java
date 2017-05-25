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
	//solved: the name will we permanentEffect anyway, but the dynamic type of the object passed to it will be HarvestEffect
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
		this.cardType=CardType.TERRITORY;
		this.harvestActivationCost=harvestActivationCost;
	}

    /**
     * Can activate harvest with a certain production value
     *
     * @param harvestValue the harvest value
     * @return true, if the harvest effect of this card can be activated with the given production Value
     */
    public boolean canActivateHarvestWith(int harvestValue){
    	return harvestValue>harvestActivationCost;
		
	}
    
     @Override
    public String toString() {
    	StringBuilder string = new StringBuilder();
    	string.append(super.toString() + "\nHarvest cost: " + harvestActivationCost + "\nHarvest effect: ");
    	if(this.permanentEffect!=null)
    		string.append(permanentEffect.toString());
    	
    	return string.toString();
    }
	

}
