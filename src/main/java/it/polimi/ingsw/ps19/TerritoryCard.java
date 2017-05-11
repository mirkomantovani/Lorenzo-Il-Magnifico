package it.polimi.ingsw.ps19;

public class TerritoryCard extends DevelopmentCard {
    //this class should have something like HarvestEffect and not a permanentEffect, waiting for the Effect Generalization modeling
	private int harvestActivationCost;
	public TerritoryCard(int id, String name, Period period, Effect immediateEffect,
			Effect permanentEffect,int harvestActivationCost) {
		super(id, name, period, null, immediateEffect, permanentEffect);
		this.harvestActivationCost=harvestActivationCost;
	}

    public boolean canActivateHarvestWith(int harvestValue){
    	return harvestValue>harvestActivationCost;
		
	}
	

}
