package it.polimi.ingsw.ps19.model.card;

import it.polimi.ingsw.ps19.Period;
import it.polimi.ingsw.ps19.exception.CardTypeException;
import it.polimi.ingsw.ps19.model.effect.Effect;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;

/**
 * A factory for creating Card objects.
 */
public class CardFactory {              

	/** The Constant TERRITORY. */
	private static final int TERRITORY=1;
	
	/** The Constant BUILDING. */
	private static final int BUILDING=2;
	
	/** The Constant CHARACTER. */
	private static final int CHARACTER=3;
	
	/** The Constant VENTURE. */
	private static final int VENTURE=4;
	
	/**
	 * Gets the card.
	 *
	 * @param code the code
	 * @param id the id
	 * @param name the name
	 * @param period the period
	 * @param cost the cost
	 * @param immediateEffect the immediate effect
	 * @param permanentEffect the permanent effect
	 * @param harvestActivationCost the harvest activation cost
	 * @param productionActivationCost the production activation cost
	 * @return the card
	 */
	public static DevelopmentCard getCard(int code, int id,String name,Period period,ResourceChest cost,Effect immediateEffect,Effect permanentEffect, int harvestActivationCost, int productionActivationCost){
		switch(code){
		case TERRITORY:
			return new TerritoryCard(id, name, period, immediateEffect, permanentEffect, harvestActivationCost);    
		case BUILDING:
			return new BuildingCard(id, name, period, cost, immediateEffect, permanentEffect, productionActivationCost); 
		case CHARACTER:
			return new CharacterCard(id, name, period, cost, immediateEffect, permanentEffect);
		case VENTURE:
			return new VentureCard(id, name, period, cost, immediateEffect, permanentEffect);
		case 0:
			return null;
			default:
				throw new CardTypeException();
		}
	}
	
}
