package it.polimi.ingsw.ps19;

import it.polimi.ingsw.ps19.model.resource.Resource;

/**
 * @author matteo
 *
 */
public abstract class PersonalBonusTile {
	
	Resource productionBonus1;
	Resource productionBonus2;
	
	Resource harvestBonus1;
	Resource harvestBonus2;
	Resource harvestBonus3;
	
	
	public Resource getHarvestBonus1() {
		return harvestBonus1;
	}

	public Resource getHarvestBonus2() {
		return harvestBonus2;
	}

	public Resource getProductionBonus1() {
		return productionBonus1;
	}

	public Resource getProductionBonus2() {
		return productionBonus2;
	}

	public Resource getHarvestBonus3() {
		return harvestBonus3;
	}
	
	

}
