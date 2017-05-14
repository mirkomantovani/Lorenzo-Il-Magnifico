
package it.polimi.ingsw.ps19.model.effect;

import it.polimi.ingsw.ps19.model.card.DevelopmentCard;

/**
 * @author Mirko
 *
 */
public abstract class Effect {
	
	/**
	 * Every effect is associated to a card
	 * This needs to be protected in order to be visible for the subclasses of Effect.
	 */
	protected DevelopmentCard card;

	public abstract void applyEffect();

//	/**
//	 * @return
//	 */
//	public DevelopmentCard getCard() {
//		return card;
//	}

	/**
	 * @param card
	 */
	public void setCard(DevelopmentCard card) {
		this.card = card;
	}
	
}
