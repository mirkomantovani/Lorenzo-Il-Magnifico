package it.polimi.ingsw.ps19.model.effect.leader;

import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.model.effect.Effect;

/**
 * @author matteo
 *
 */
public class LucreziaBorgiaEffect extends Effect {

	@Override
	public void applyEffect(Player p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Your familiars, excepts for the neutral one, have a +2 bonus to their"
				+ "action value. You can also increase the value by spending servants or by"
				+ "using character cards effects.");
		return builder.toString();
	}

	
	
}
