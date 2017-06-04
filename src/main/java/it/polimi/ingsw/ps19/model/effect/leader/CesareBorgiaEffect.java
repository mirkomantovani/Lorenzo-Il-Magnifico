package it.polimi.ingsw.ps19.model.effect.leader;

import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.model.effect.Effect;

/**
 * @author matteo
 *
 */
public class CesareBorgiaEffect extends Effect {

	@Override
	public void applyEffect(Player p) {
		p.getBonuses().setNoMilitaryPointsRequiredForTerritories(true);
		
	}
	
	public void disapplyEffect(Player p){
		p.getBonuses().setNoMilitaryPointsRequiredForTerritories(false);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("You can pick territory cards without having the military points required");
		return builder.toString();
	}

	
	
}
