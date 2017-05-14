package it.polimi.ingsw.ps19.model.effect;

/**
 * This class represents the production effect of a building card, it differs from other effects because its actual effect
 * is applied only when a production on the card is activated
 * 
 * @author Mirko
 *
 */
public class ProductionEffect extends Effect{


	/**
	 * actualEffect is the the effect to be applied when the production on the card is activated.
	 * actualEffect at runtime will have only three possible dynamic types: ResourceExchangeEffect, InstantResourceEffect
	 * or ForEachCardTypeEffect
	 * 
	 */
	private Effect actualEffect;
	
	public ProductionEffect(Effect actualEffect) {
		super();
		this.actualEffect = actualEffect;
	}

	public void applyEffect() {
		actualEffect.applyEffect();
	}

}
