package it.polimi.ingsw.ps19.model.effect;

import it.polimi.ingsw.ps19.Player;

/**
 * The Class NoFloorBonusEffect.
 */
public class NoFloorBonusEffect extends Effect{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6422690715195253118L;

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.effect.Effect#applyEffect(it.polimi.ingsw.ps19.Player)
	 */
	@Override
	public void applyEffect(Player p) {
		p.getBonuses().setNoFloorBonus(true);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "You can't take any bonus from a tower floor";
	}
	
}
