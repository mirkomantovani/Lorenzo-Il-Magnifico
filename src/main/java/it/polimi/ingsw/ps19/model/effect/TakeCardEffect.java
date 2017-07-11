package it.polimi.ingsw.ps19.model.effect;

import it.polimi.ingsw.ps19.model.Player;
import it.polimi.ingsw.ps19.model.card.CardType;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;

/**
 * The Class TakeCardEffect.
 *
 * @author Matteo, Jimmy
 */
public class TakeCardEffect extends Effect{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7178424807692191463L;
	
	/** The card type. */
	private CardType cardType;
	
	/** The card value. */
	private int cardValue;
	
	/** The discount chest. */
	private ResourceChest discountChest;
	
	
	/**
	 * Instantiates a new take card effect.
	 *
	 * @param card the card
	 * @param value the value
	 * @param discountChest this parameter can be an empty ResourceChest if there is no discount
	 */
	public TakeCardEffect(CardType card, int value, ResourceChest discountChest){
		cardType = card;
		cardValue = value;
		this.discountChest = discountChest;
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.effect.Effect#applyEffect(it.polimi.ingsw.ps19.Player)
	 */
	@Override
	public void applyEffect(Player p) {
		
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Take a ");
		builder.append(cardType.toString().toLowerCase());
		builder.append(" card, from an action space with an action value of ");
		builder.append(cardValue);
		builder.append(" and you receive the following discount:" + discountChest.toString());
		return builder.toString();
	}
	
	
	
	

}
