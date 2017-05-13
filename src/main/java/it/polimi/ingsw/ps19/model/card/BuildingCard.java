package it.polimi.ingsw.ps19.model.card;

import it.polimi.ingsw.ps19.Effect;
import it.polimi.ingsw.ps19.Period;
import it.polimi.ingsw.ps19.ResourceChest;

/**
 * The Class BuildingCard.
 *
 * @author Mirko
 */
public class BuildingCard extends DevelopmentCard {
	  
  	/** The production activation cost. */
  	//this class should have something like ProductionEffect and not a permanentEffect, waiting for the Effect Generalization modeling
	private int productionActivationCost;
	
	/**
	 * Instantiates a new building card.
	 *
	 * @param id the id
	 * @param name the name
	 * @param period the period
	 * @param cost the cost
	 * @param immediateEffect the immediate effect
	 * @param permanentEffect the permanent effect
	 */
	public BuildingCard(int id, String name, Period period, ResourceChest cost, Effect immediateEffect,
			Effect permanentEffect) {
		super(id, name, period, cost, immediateEffect, permanentEffect);
		this.productionActivationCost=productionActivationCost;
	}

	/**
	 * Can activate production with.
	 *
	 * @param productionValue the production value
	 * @return true, if successful
	 */
	public boolean canActivateProductionWith(int productionValue){
    	return productionValue>productionActivationCost;

	}

}
