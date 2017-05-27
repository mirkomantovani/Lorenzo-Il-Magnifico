package it.polimi.ingsw.ps19.model.effect.leader;

import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.model.effect.Effect;

/**
 * @author matteo
 *
 */
public class FilippoBrunelleschiEffect extends Effect {

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("You can place one familiar in a tower alredy occupied without paying 3 coins");
		return builder.toString();
	}

	@Override
	public void applyEffect(Player p) {
		p.getBonuses().setDiscountOccupiedTower(true);
	}
	
	public void disapplyEffect(Player p){
		p.getBonuses().setDiscountOccupiedTower(false);
	}

	
	
}
