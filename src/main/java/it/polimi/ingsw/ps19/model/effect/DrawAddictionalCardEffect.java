package it.polimi.ingsw.ps19.model.effect;

import it.polimi.ingsw.ps19.model.card.DevelopmentCard;

/**
 * @author matteo
 *
 */
public class DrawAddictionalCardEffect extends Effect{
	
	private DevelopmentCard cardType;
	
	private int cardCost;
	
	public DrawAddictionalCardEffect(DevelopmentCard card, int cost){
		
		super();
		cardType = card;
		cardCost = cost;
		
	}

	@Override
	public void applyEffect() {
		// TODO Auto-generated method stub
		
	}
	
	

}
