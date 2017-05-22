package it.polimi.ingsw.ps19;

/**
 * @author Jimmy
 *
 */
public class Bonus {
	 
	 int harvestVariation; //it means the increase/decrease of the Harvest action value for the specific player
	 int productionVariation; // "  "  "  " 
	 
	 int actionValueBuildingVariation;
	 int actionValueVentureVariation;
	 int actionValueCharacterVariation;
	 int actionValueTerritoryVariation;
	 
	 boolean noFloorBonus;
	 boolean characterCardsDiscount; //This boolean is true if "DAMA"'s effect is active.
	 boolean buildingCardsDiscount;  //This boolean is true if "COSTRUTTORE"'s effect is active.
	 
	public int getHarvestVariation() {
		return harvestVariation;
	}
	public void setHarvestVariation(int harvestVariation) {
		this.harvestVariation = harvestVariation;
	}
	public int getProductionVariation() {
		return productionVariation;
	}
	public void setProductionVariation(int productionVariation) {
		this.productionVariation = productionVariation;
	}
	public int getActionValueBuildingVariation() {
		return actionValueBuildingVariation;
	}
	public void setActionValueBuildingVariation(int actionValueBuildingVariation) {
		this.actionValueBuildingVariation = actionValueBuildingVariation;
	}
	public int getActionValueVentureVariation() {
		return actionValueVentureVariation;
	}
	public void setActionValueVentureVariation(int actionValueVentureVariation) {
		this.actionValueVentureVariation = actionValueVentureVariation;
	}
	public int getActionValueCharacterVariation() {
		return actionValueCharacterVariation;
	}
	public void setActionValueCharacterVariation(int actionValueCharacterVariation) {
		this.actionValueCharacterVariation = actionValueCharacterVariation;
	}
	public int getActionValueTerritoryVariation() {
		return actionValueTerritoryVariation;
	}
	public void setActionValueTerritoryVariation(int actionValueTerritoryVariation) {
		this.actionValueTerritoryVariation = actionValueTerritoryVariation;
	}
	public boolean isNoFloorBonus() {
		return noFloorBonus;
	}
	public void setNoFloorBonus(boolean noFloorBonus) {
		this.noFloorBonus = noFloorBonus;
	}
	public boolean isCharacterCardsDiscount() {
		return characterCardsDiscount;
	}
	public void setCharacterCardsDiscount(boolean characterCardsDiscount) {
		this.characterCardsDiscount = characterCardsDiscount;
	}
	public boolean isBuildingCardsDiscount() {
		return buildingCardsDiscount;
	}
	public void setBuildingCardsDiscount(boolean buildingCardsDiscount) {
		this.buildingCardsDiscount = buildingCardsDiscount;
	}
	 
	   
	 
}

