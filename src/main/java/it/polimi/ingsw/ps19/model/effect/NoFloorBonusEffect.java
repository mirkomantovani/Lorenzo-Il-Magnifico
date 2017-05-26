package it.polimi.ingsw.ps19.model.effect;

import it.polimi.ingsw.ps19.Player;

public class NoFloorBonusEffect extends Effect{

	@Override
	public void applyEffect(Player p) {
		p.getBonuses().setNoFloorBonus(true);
	}
	
	@Override
	public String toString() {
		return "You can't take any bonus from a tower floor";
	}
	
}
