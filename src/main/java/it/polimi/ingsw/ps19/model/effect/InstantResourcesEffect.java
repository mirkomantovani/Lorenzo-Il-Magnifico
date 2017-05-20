package it.polimi.ingsw.ps19.model.effect;

import it.polimi.ingsw.ps19.model.resource.ResourceChest;

/**
 * This class implements the InstantResourceEffect that gives a certain amount
 * of a certain resource to a player. 
 * 
 * @author Jimmy
 *
 */
public class InstantResourcesEffect extends Effect {
	   
	private ResourceChest effectResourceChest;	
	/**
	 * class constructor
	 * 
	 * @param effectResourceChest it contains all the resources that a player gets as the 
	 *                            result of the effect.  
	 */
	public InstantResourcesEffect(ResourceChest effectResourceChest){
		this.effectResourceChest = effectResourceChest;
	}	
	
	public InstantResourcesEffect(ResourceChest effectResourceChest,CouncilPrivilegeEffect councilPrivilegeEffect){
		this.effectResourceChest = effectResourceChest;
	}	
	
	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.effect.Effect#applyEffect()
	 */
	public void applyEffect() {
		effectResourceChest.pourInto(this.getAssociatedPlayer()
				.getResourceChest());
	}

	/* 
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * @author matteo
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("You gain ");
		builder.append(effectResourceChest.toString());
		
		return builder.toString();
	}
	
	
}
