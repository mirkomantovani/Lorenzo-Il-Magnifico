package it.polimi.ingsw.ps19.model.effect.leader;

import it.polimi.ingsw.ps19.model.Player;

/**
 * The Interface Disapplyable.
 */
public interface Disapplyable {

	/**
	 * Disapply effect.
	 *
	 * @param p the p
	 */
	public abstract void disapplyEffect(Player p);

	/**
	 * Apply effect.
	 *
	 * @param player the player
	 */
	public abstract void applyEffect(Player player);
}
