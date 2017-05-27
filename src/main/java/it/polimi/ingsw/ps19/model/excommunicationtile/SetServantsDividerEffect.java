package it.polimi.ingsw.ps19.model.excommunicationtile;

import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.model.effect.Effect;

/**
 * This effect when applied only sets a value for the servantsDivider field in bonus of a player
 * @author Mirko
 *
 */
public class SetServantsDividerEffect extends Effect {

	private int divider;
	
	public SetServantsDividerEffect(int divider) {
		this.divider = divider;
	}

	@Override
	public void applyEffect(Player player) {

		player.getBonuses().setServantsDivider(divider);
	
	}

}
