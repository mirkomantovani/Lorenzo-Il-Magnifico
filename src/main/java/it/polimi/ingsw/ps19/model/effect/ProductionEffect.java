package it.polimi.ingsw.ps19.model.effect;

import it.polimi.ingsw.ps19.exception.NotAnExchangeEffectException;
import it.polimi.ingsw.ps19.model.Player;
import it.polimi.ingsw.ps19.model.effect.leader.Disapplyable;

/**
 * This class represents the production effect of a building card, it differs from other effects because its actual effect
 * is applied only when a production on the card is activated.
 *
 * @author Mirko
 */
public class ProductionEffect extends Effect implements Disapplyable{


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4899398635817616168L;
	/**
	 * actualEffect is the the effect to be applied when the production on the card is activated.
	 * actualEffect at runtime will have only three possible dynamic types: ResourceExchangeEffect, InstantResourceEffect
	 * or ForEachCardTypeEffect
	 * 
	 */
	private Effect actualEffect;
	
	/**
	 * Instantiates a new production effect.
	 *
	 * @param actualEffect the actual effect
	 */
	public ProductionEffect(Effect actualEffect) {
		super();
		this.actualEffect = actualEffect;
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.effect.Effect#applyEffect(it.polimi.ingsw.ps19.Player)
	 */
	@Override
	public void applyEffect(Player p) {
		actualEffect.applyEffect(p);
	}
	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return actualEffect.toString();
	}
	
	/**
	 * Checks if is resources exchange effect.
	 *
	 * @return true, if is resources exchange effect
	 */
	public boolean isResourcesExchangeEffect(){
		if(this.actualEffect instanceof ResourcesExchangeEffect)
			return true;
		else
			return false;
	}
	
	/**
	 * Gets the resources exchange effect.
	 *
	 * @return the resources exchange effect
	 */
	public ResourcesExchangeEffect getResourcesExchangeEffect(){
		if(this.actualEffect instanceof ResourcesExchangeEffect)
			return (ResourcesExchangeEffect)this.actualEffect;			
		else throw new NotAnExchangeEffectException();
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.effect.leader.Disapplyable#disapplyEffect(it.polimi.ingsw.ps19.Player)
	 */
	@Override
	public void disapplyEffect(Player p) {
		// TODO Auto-generated method stub
		
	}
}

