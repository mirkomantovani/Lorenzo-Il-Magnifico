package it.polimi.ingsw.ps19.model.card;

import it.polimi.ingsw.ps19.Effect;
import it.polimi.ingsw.ps19.Period;
import it.polimi.ingsw.ps19.ResourceCost;

public class BuildingCard extends DevelopmentCard {
	  //this class should have something like ProductionEffect and not a permanentEffect, waiting for the Effect Generalization modeling
	private int productionActivationCost;
	public BuildingCard(int id, String name, Period period, ResourceCost cost, Effect immediateEffect,
			Effect permanentEffect) {
		super(id, name, period, cost, immediateEffect, permanentEffect);
		this.productionActivationCost=productionActivationCost;
	}

	public boolean canActivateProductionWith(int productionValue){
    	return productionValue>productionActivationCost;

	}

}
