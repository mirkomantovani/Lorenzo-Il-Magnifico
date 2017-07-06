package it.polimi.ingsw.ps19;

import java.io.Serializable;

import it.polimi.ingsw.ps19.model.resource.ResourceChest;

/**
 * The Class LeaderCardRequirement.
 */
public class LeaderCardRequirement implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2973716369334411303L;

	/** The resources required. */
	ResourceChest resourcesRequired;
	
	/** The building card required. */
	private int buildingCardRequired;
	
	/** The character card required. */
	private int characterCardRequired;
	
	/** The venture card required. */
	private int ventureCardRequired;
	
	/** The territory card required. */
	private int territoryCardRequired;
	
	/** The any card required. */
	private int anyCardRequired;
	
	/**
	 * Instantiates a new leader card requirement.
	 *
	 * @param resources the resources
	 * @param territory the territory
	 * @param building the building
	 * @param character the character
	 * @param venture the venture
	 * @param any the any
	 */
	public LeaderCardRequirement(ResourceChest resources, int territory, int building, int character, int venture, int any){
		this.resourcesRequired = resources;
		this.territoryCardRequired = territory;
		this.buildingCardRequired = building;
		this.characterCardRequired = character;
		this.ventureCardRequired = venture;
		this.anyCardRequired = any;
	}
	
	
	
	/**
	 * Gets the any card required.
	 *
	 * @return the any card required
	 */
	public int getAnyCardRequired() {
		return anyCardRequired;
	}



	/**
	 * Sets the any card required.
	 *
	 * @param anyCardRequired the new any card required
	 */
	public void setAnyCardRequired(int anyCardRequired) {
		this.anyCardRequired = anyCardRequired;
	}



	/**
	 * Gets the resources required.
	 *
	 * @return the resources required
	 */
	public ResourceChest getResourcesRequired() {
		return resourcesRequired;
	}
	
	/**
	 * Sets the resources required.
	 *
	 * @param resourcesRequired the new resources required
	 */
	public void setResourcesRequired(ResourceChest resourcesRequired) {
		this.resourcesRequired = resourcesRequired;
	}
	
	/**
	 * Gets the building card required.
	 *
	 * @return the building card required
	 */
	public int getBuildingCardRequired() {
		return buildingCardRequired;
	}
	
	/**
	 * Sets the building card required.
	 *
	 * @param buildingCardRequired the new building card required
	 */
	public void setBuildingCardRequired(int buildingCardRequired) {
		this.buildingCardRequired = buildingCardRequired;
	}
	
	/**
	 * Gets the character card required.
	 *
	 * @return the character card required
	 */
	public int getCharacterCardRequired() {
		return characterCardRequired;
	}
	
	/**
	 * Sets the character card required.
	 *
	 * @param characterCardRequired the new character card required
	 */
	public void setCharacterCardRequired(int characterCardRequired) {
		this.characterCardRequired = characterCardRequired;
	}
	
	/**
	 * Gets the venture card required.
	 *
	 * @return the venture card required
	 */
	public int getVentureCardRequired() {
		return ventureCardRequired;
	}
	
	/**
	 * Sets the venture card required.
	 *
	 * @param ventureCardRequired the new venture card required
	 */
	public void setVentureCardRequired(int ventureCardRequired) {
		this.ventureCardRequired = ventureCardRequired;
	}
	
	/**
	 * Gets the territory card required.
	 *
	 * @return the territory card required
	 */
	public int getTerritoryCardRequired() {
		return territoryCardRequired;
	}
	
	/**
	 * Sets the territory card required.
	 *
	 * @param territoryCardRequired the new territory card required
	 */
	public void setTerritoryCardRequired(int territoryCardRequired) {
		this.territoryCardRequired = territoryCardRequired;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
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
