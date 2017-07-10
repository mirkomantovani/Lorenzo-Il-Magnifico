package it.polimi.ingsw.ps19.model.effect;

import it.polimi.ingsw.ps19.model.Player;
import it.polimi.ingsw.ps19.model.effect.leader.Disapplyable;

/**
 * This class implements the harvest effect that gives only resources,
 * hence a resource chest. The harvest effect is the same as an instantResourcesEffect
 * except for the fact that it has a condition that triggers it. 
 * 
 * @author Jimmy
 *
 */
public class HarvestEffect extends Effect implements Disapplyable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1643184690128815176L;
	
	/** The instant effect. */
	Effect instantEffect;
	
	/**
	 * class constructor.
	 *
	 * @param instantEffect the instant effect
	 */
	public HarvestEffect(Effect instantEffect){
		this.instantEffect=instantEffect;
	
	}
	
	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.effect.Effect#applyEffect()
	 */
	public void applyEffect(Player p) {
		instantEffect.applyEffect(p);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return instantEffect.toString();
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.effect.leader.Disapplyable#disapplyEffect(it.polimi.ingsw.ps19.Player)
	 */
	@Override
	public void disapplyEffect(Player p) {
		// TODO Auto-generated method stub
		
	}

}
