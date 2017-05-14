package it.polimi.ingsw.ps19.model.effect;

import it.polimi.ingsw.ps19.ResourceChest;

/**
 * This class implements the InstantResourceEffect that gives a certain amount
 * of a certain resource to a player. 
 * 
 * @author Jimmy
 *
 */
public class InstantResourcesEffect extends Effect {
	   
	private ResourceChest effectResourceChest;
	private ResourceChest playersResourceChest;
	
	/**
	 * class constructor
	 * 
	 * @param playersResourceChest player's resource chest.
	 * @param effectResourceChest it contains all the resources that a player gets as the 
	 *                            result of the effect.  
	 */
	public InstantResourcesEffect(ResourceChest playersResourceChest, ResourceChest effectResourceChest){
		this.effectResourceChest = effectResourceChest;
		this.playersResourceChest = playersResourceChest;
	}	
	
	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.effect.Effect#applyEffect()
	 */
	public void applyEffect() {
		effectResourceChest.pourInto(playersResourceChest);
	}
}
