package it.polimi.ingsw.ps19.model.excommunicationtile;

import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.model.effect.Effect;

/**
 * @author Mirko
 *
 */
public class SetSkipRoundEffect extends Effect {

	@Override
	public void applyEffect(Player player) {
		player.getBonuses().setSkipRoundActive(true);
	}

}
