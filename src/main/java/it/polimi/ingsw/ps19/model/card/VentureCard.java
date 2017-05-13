package it.polimi.ingsw.ps19.model.card;

import it.polimi.ingsw.ps19.Effect;
import it.polimi.ingsw.ps19.Period;
import it.polimi.ingsw.ps19.ResourceChest;


/**
 * The Class VentureCard.
 */
/**
 * @author Mirko
 *
 */
public class VentureCard extends DevelopmentCard {

	/**
	 * Instantiates a new venture card.
	 *
	 * @param id the id
	 * @param name the name
	 * @param period the period
	 * @param cost the cost
	 * @param immediateEffect the immediate effect
	 * @param permanentEffect the permanent effect
	 */
	public VentureCard(int id, String name, Period period, ResourceChest cost, Effect immediateEffect,
			Effect permanentEffect) {
		super(id, name, period, cost, immediateEffect, permanentEffect);
	}

	

}
