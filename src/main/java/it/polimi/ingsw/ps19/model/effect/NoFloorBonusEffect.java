package it.polimi.ingsw.ps19.model.effect;

public class NoFloorBonusEffect extends Effect{

	@Override
	public void applyEffect() {
		this.getAssociatedPlayer().getBonuses().setNoFloorBonus(true);
	}
	
	@Override
	public String toString() {
		return "You can't take any bonus from a tower floor";
	}
	
}
