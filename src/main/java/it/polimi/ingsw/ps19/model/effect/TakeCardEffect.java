package it.polimi.ingsw.ps19.model.effect;

import it.polimi.ingsw.ps19.model.card.CardType;
import it.polimi.ingsw.ps19.model.card.DevelopmentCard;

/**
 * @author matteo
 *
 */
public class TakeCardEffect extends Effect{
	
	private CardType cardType;
	
	private int cardCost;
	
	public TakeCardEffect(CardType card, int cost){
		
		super();
		cardType = card;
		cardCost = cost;
		
	}

	@Override
	public void applyEffect() {
		// TODO Auto-generated method stub
		
	}
	
	

}
