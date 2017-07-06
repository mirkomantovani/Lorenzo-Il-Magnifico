package it.polimi.ingsw.ps19.model.excommunicationtile;

import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.model.effect.Effect;

/**
 * The Class SetNoMarketActionEffect.
 *
 * @author Mirko
 */
public class SetNoMarketActionEffect extends Effect {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6954023005307137525L;

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.effect.Effect#applyEffect(it.polimi.ingsw.ps19.Player)
	 */
	@Override
	public void applyEffect(Player player) {
		player.getBonuses().setNoMarketActionActive(true);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "You can't place family members in the market anymore";
	}

}
