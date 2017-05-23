package it.polimi.ingsw.ps19.model.effect.leader;

import it.polimi.ingsw.ps19.model.effect.CardEffect;

public class NoFloorBonusEffect extends CardEffect{

	@Override
	public void applyEffect() {
		this.getAssociatedPlayer().getBonuses().setNoFloorBonus(true);
	}
	
	@Override
	public String toString() {
		return "You can't take any bonus from a tower floor";
	}
	
}
