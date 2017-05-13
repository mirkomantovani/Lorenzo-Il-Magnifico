package it.polimi.ingsw.ps19.model.effect;

/**
 * The Class ResourcesExchangeEffect represents a type of effect which can be found only in
 * a Building Card and can only be activated when the player activates a production
 * The exchange gives the player the possibility to pay a certain amount of up to three
 * resources type in order to receive a certain amount of up to two resources type
 * This effect also includes the possibility to have a XOR choice on the exchange 
 *
 * @author Mirko
 */

public class ResourcesExchangeEffect extends Effect {


	private AtomicExchangeEffect normalExchangeEffect;
	private AtomicExchangeEffect alternativeExchangeEffect;
	
	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.effect.Effect#applyEffect()
	 */
	
	
	
	private void applyEffect(AtomicExchangeEffect chosenExchangeEffect){
		chosenExchangeEffect.applyEffect();
	}
	
	public ResourcesExchangeEffect(AtomicExchangeEffect normalExchangeEffect,
			AtomicExchangeEffect alternativeExchangeEffect) {
		super();
		this.normalExchangeEffect = normalExchangeEffect;
		this.alternativeExchangeEffect = alternativeExchangeEffect;
	}

	/**
	 * @return true if the effect has a possible alternative Atomic exchange effect
	 */
	public boolean hasAlternativeEffect(){
		return alternativeExchangeEffect!=null;
		
	}
	
	
	/**
	 * method to apply the chosen exchange alternative, if choice is 1,it applies the normal effect
	(the first in the card image) otherwise it applies the second one
	we might change the 1 and 2 notation to a better one like using constants or an Enumeration
	 * 
	 * @param choice
	 * 
	 * 
	 */
	public void applyChosenEffect(int choice) {
		if(choice==1)applyEffect(normalExchangeEffect);
		else if(choice==2)applyEffect(alternativeExchangeEffect);
		//else exception?, but it shouldn't be possible to has another value 
	
	}

	
	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.effect.Effect#applyEffect()
	 * 
	 */
	@Override
	public void applyEffect() {
		applyEffect(normalExchangeEffect);
	}

}
