package it.polimi.ingsw.ps19;

public class BuildingCard extends DevelopmentCard {
	  //this class should have something like ProductionEffect and not a permanentEffect, waiting for the Effect Generalization modeling
	private ActionValue productionActivationCost;
	public BuildingCard(int id, String name, Period period, ResourceCost cost, Effect immediateEffect,
			Effect permanentEffect) {
		super(id, name, period, cost, immediateEffect, permanentEffect);
		this.productionActivationCost=productionActivationCost;
	}

	public boolean canActivateProductionWith(ActionValue productionValue){
    	return productionValue.getValue()>productionActivationCost.getValue();

	}

}
