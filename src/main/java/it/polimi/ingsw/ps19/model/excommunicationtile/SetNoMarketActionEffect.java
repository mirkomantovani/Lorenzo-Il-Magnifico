package it.polimi.ingsw.ps19.model.excommunicationtile;

import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.model.effect.Effect;

/**
 * @author Mirko
 *
 */
public class SetNoMarketActionEffect extends Effect {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6954023005307137525L;

	@Override
	public void applyEffect(Player player) {
		player.getBonuses().setNoMarketActionActive(true);
	}

}
