package it.polimi.ingsw.ps19.model.effect.leader;

import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.model.effect.Effect;

/**
 * @author matteo
 *
 */
public class PicoDellaMirandolaEffect extends Effect{

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("When you take a development card, if it costs coins, you save up 3 coins."
				+ "This is not a discount on the coins that you must pay in case of occupied tower");
		return builder.toString();
	}

	@Override
	public void applyEffect(Player p) {
		// TODO Auto-generated method stub
		
	}

	
}
