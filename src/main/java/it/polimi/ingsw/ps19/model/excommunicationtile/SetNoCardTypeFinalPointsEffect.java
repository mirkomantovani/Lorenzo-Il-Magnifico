package it.polimi.ingsw.ps19.model.excommunicationtile;

import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.model.card.CardType;
import it.polimi.ingsw.ps19.model.effect.Effect;

/**
 * Excommunication effect: you don't get the final victory points from the "cardType"
 * Card Type
 *
 * @author Mirko
 */
public class SetNoCardTypeFinalPointsEffect extends Effect {
 
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3114860811278517273L;
	
	/** The card type. */
	private CardType cardType;
	
	/**
	 * Instantiates a new sets the no card type final points effect.
	 *
	 * @param cardType the card type
	 */
	public SetNoCardTypeFinalPointsEffect(CardType cardType) {
		this.cardType = cardType;
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.effect.Effect#applyEffect(it.polimi.ingsw.ps19.Player)
	 */
	@Override
	public void applyEffect(Player player) {
		player.getBonuses().setNoCardTypeFinalPoints(cardType);

	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "You don't get the final points relative to the "+cardType.toString().toLowerCase()+" cards";
	}

}
