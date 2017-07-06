package it.polimi.ingsw.ps19.model.effect;


import java.io.Serializable;

import it.polimi.ingsw.ps19.Player;

/**
 * The abstract class Effect, it only has one method: applyEffect
 *
 * @author Mirko
 */

public abstract class Effect implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2481175952685522509L;

	/**
	 * Apply effect.
	 *
	 * @param player the player
	 */
	public abstract void applyEffect(Player player);
	
}