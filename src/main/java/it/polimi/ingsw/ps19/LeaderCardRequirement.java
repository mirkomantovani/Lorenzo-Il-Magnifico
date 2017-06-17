package it.polimi.ingsw.ps19;

import java.io.Serializable;

import it.polimi.ingsw.ps19.model.resource.ResourceChest;

public class LeaderCardRequirement implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2973716369334411303L;

	ResourceChest resourcesRequired;
	
	private int buildingCardRequired;
	private int characterCardRequired;
	private int ventureCardRequired;
	private int territoryCardRequired;
	private int anyCardRequired;
	
	public LeaderCardRequirement(ResourceChest resources, int territory, int building, int character, int venture, int any){
		this.resourcesRequired = resources;
		this.territoryCardRequired = territory;
		this.buildingCardRequired = building;
		this.characterCardRequired = character;
		this.ventureCardRequired = venture;
		this.anyCardRequired = any;
	}
	
	
	
	public int getAnyCardRequired() {
		return anyCardRequired;
	}



	public void setAnyCardRequired(int anyCardRequired) {
		this.anyCardRequired = anyCardRequired;
	}



	public ResourceChest getResourcesRequired() {
		return resourcesRequired;
	}
	public void setResourcesRequired(ResourceChest resourcesRequired) {
		this.resourcesRequired = resourcesRequired;
	}
	public int getBuildingCardRequired() {
		return buildingCardRequired;
	}
	public void setBuildingCardRequired(int buildingCardRequired) {
		this.buildingCardRequired = buildingCardRequired;
	}
	public int getCharacterCardRequired() {
		return characterCardRequired;
	}
	public void setCharacterCardRequired(int characterCardRequired) {
		this.characterCardRequired = characterCardRequired;
	}
	public int getVentureCardRequired() {
		return ventureCardRequired;
	}
	public void setVentureCardRequired(int ventureCardRequired) {
		this.ventureCardRequired = ventureCardRequired;
	}
	public int getTerritoryCardRequired() {
		return territoryCardRequired;
	}
	public void setTerritoryCardRequired(int territoryCardRequired) {
		this.territoryCardRequired = territoryCardRequired;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("To activate the special effect you must have: ");
		builder.append(resourcesRequired);
		builder.append(", ");
		builder.append(buildingCardRequired);
		builder.append(" building cards, ");
		builder.append(characterCardRequired);
		builder.append(" character cards, ");
		builder.append(ventureCardRequired);
		builder.append(" venture cards, ");
		builder.append(territoryCardRequired);
		builder.append(" territory cards.");
		return builder.toString();
	}
	
	
	

}
