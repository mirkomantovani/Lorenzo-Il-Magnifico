package it.polimi.ingsw.ps19.model.effect;


import java.io.Serializable;

import it.polimi.ingsw.ps19.Player;

/**
 * @author Mirko
 *
 */

public abstract class Effect implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2481175952685522509L;

	public abstract void applyEffect(Player player);
	
}