package it.polimi.ingsw.ps19.model.excommunicationtile;

import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.model.effect.Effect;

public class SetNoMarketActionEffect extends Effect {

	@Override
	public void applyEffect(Player player) {
		player.getBonuses().setNoMarketActionActive(true);
	}

}
