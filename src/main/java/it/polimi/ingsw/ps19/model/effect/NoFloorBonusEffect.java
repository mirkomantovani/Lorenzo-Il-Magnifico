package it.polimi.ingsw.ps19.model.effect;

import it.polimi.ingsw.ps19.Player;

public class NoFloorBonusEffect extends Effect{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6422690715195253118L;

	@Override
	public void applyEffect(Player p) {
		p.getBonuses().setNoFloorBonus(true);
	}
	
	@Override
	public String toString() {
		return "You can't take any bonus from a tower floor";
	}
	
}
