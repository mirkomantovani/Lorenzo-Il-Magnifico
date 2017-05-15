package it.polimi.ingsw.ps19.model.effect;

/**
 * This class implements the harvest effect that gives only resources,
 * hence a resource chest. The harvest effect is the same as an instantResourcesEffect
 * except for the fact that it has a condition that triggers it. 
 * 
 * @author Jimmy
 *
 */
public class HarvestEffect extends Effect {
	
	InstantResourcesEffect instantEffect;
	
	/**
	 * class constructor
	 * 
	 * @param effectResourceChest  the chest that contains the rewarded resources
	 */
	public HarvestEffect(InstantResourcesEffect instantEffect){
		this.instantEffect=instantEffect;
	}
	
	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.effect.Effect#applyEffect()
	 */
	public void applyEffect() {
		instantEffect.applyEffect();
	}

}
