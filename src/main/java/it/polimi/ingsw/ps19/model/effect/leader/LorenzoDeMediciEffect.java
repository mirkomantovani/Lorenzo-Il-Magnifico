package it.polimi.ingsw.ps19.model.effect.leader;

import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.model.effect.Effect;

/**
 * @author matteo
 *
 */
public class LorenzoDeMediciEffect extends Effect{

	@Override
	public void applyEffect(Player p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Copy the ability of a leader card played by any other player."
				+ "Once you choose one ability, it can't be changed");
		return builder.toString();
	}

	
	
}
