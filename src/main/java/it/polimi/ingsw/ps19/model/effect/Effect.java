
package it.polimi.ingsw.ps19.model.effect;

import it.polimi.ingsw.ps19.Player;
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
	 * remember that we have to establish an association between effect and card at the moment of instantiating the cards, (from file)
	 * @param card
	 */
	public void setCard(DevelopmentCard card) {
		this.card = card;
	}
	
	/**
	 * @author Jimmy 
	 * @return Player, the player associated with the card that has the specific effect, this method is created to make the "call chain" shorter.
	 */
	public Player getAssociatedPlayer(){
		return card.getPlayer();
	}
	
}
