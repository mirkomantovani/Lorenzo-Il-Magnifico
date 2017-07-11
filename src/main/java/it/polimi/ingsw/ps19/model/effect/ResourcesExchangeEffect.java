package it.polimi.ingsw.ps19.model.effect;

import it.polimi.ingsw.ps19.model.Player;

/**
 * The Class ResourcesExchangeEffect represents a type of effect which can be found only in
 * a Building Card and can only be activated when the player activates a production
 * The exchange gives the player the possibility to pay a certain amount of up to three
 * resources type in order to receive a certain amount of up to two resources type
 * This effect also includes the possibility to have a XOR choice on the exchange .
 *
 * @author Mirko
 */

public class ResourcesExchangeEffect extends Effect {


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2825814236559993863L;
	
	/** The normal exchange effect. */
	private AtomicExchangeEffect normalExchangeEffect;
	
	/** The alternative exchange effect. */
	private AtomicExchangeEffect alternativeExchangeEffect;
	
	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.effect.Effect#applyEffect()
	 */
	
	/**
	 * Instantiates a new resources exchange effect.
	 *
	 * @param normalExchangeEffect the normal exchange effect
	 * @param alternativeExchangeEffect the alternative exchange effect
	 */
	public ResourcesExchangeEffect(AtomicExchangeEffect normalExchangeEffect,
			AtomicExchangeEffect alternativeExchangeEffect) {
		super();
		this.normalExchangeEffect = normalExchangeEffect;
		this.alternativeExchangeEffect = alternativeExchangeEffect;
	}
	
	/**
	 * Apply effect.
	 *
	 * @param chosenExchangeEffect the chosen exchange effect
	 * @param player the player
	 */
	private void applyEffect(AtomicExchangeEffect chosenExchangeEffect,Player player){
		System.out.println("resourcesexchangeeffect applying atomic exchange effect");
		chosenExchangeEffect.applyEffect(player);
		
	}
	
	
	/**
	 * Checks for alternative effect.
	 *
	 * @return true if the effect has a possible alternative Atomic exchange effect
	 */
	public boolean hasAlternativeEffect(){
		return alternativeExchangeEffect!=null;
		
	}
	
	/**
	 * Gets the normal effect to string.
	 *
	 * @return the normal effect to string
	 */
	public String getNormalEffectToString(){
		return this.normalExchangeEffect.toString();
	}
	
	/**
	 * Gets the alternative effect to string.
	 *
	 * @return the alternative effect to string
	 */
	public String getAlternativeEffectToString(){
		return this.alternativeExchangeEffect.toString();
	}
	
	
	/**
	 * method to apply the chosen exchange alternative, if choice is 1,it applies the normal effect
	 * 	(the first in the card image) otherwise it applies the second one
	 * 	we might change the 1 and 2 notation to a better one like using constants or an Enumeration.
	 *
	 * @param choice the choice
	 * @param player the player
	 */
	public void applyEffect(int choice, Player player) {
		if(choice==1){
			System.out.println("ResourcesExchangeEffect: choice=1");
			applyEffect(normalExchangeEffect,player);
		}
		else if(choice==2){
			System.out.println("ResourcesExchangeEffect: choice =2 ");
			applyEffect(alternativeExchangeEffect,player);
		}
		//else exception?, but it shouldn't be possible to have another value 
	
	}

	
	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.effect.Effect#applyEffect()
	 * 
	 */
	@Override
	public void applyEffect(Player player) {
		applyEffect(normalExchangeEffect,player);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if(alternativeExchangeEffect!=null)
		return normalExchangeEffect.toString()+
				" or"+alternativeExchangeEffect.toString();
		else return normalExchangeEffect.toString();
		
	}


}
