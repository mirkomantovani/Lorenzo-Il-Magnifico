package it.polimi.ingsw.ps19.model.excommunicationtile;

import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.model.effect.Effect;

/**
 * The Class SetSkipRoundEffect.
 *
 * @author Mirko
 */
public class SetSkipRoundEffect extends Effect {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2678249263612511472L;

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.effect.Effect#applyEffect(it.polimi.ingsw.ps19.Player)
	 */
	@Override
	public void applyEffect(Player player) {
		player.getBonuses().setSkipRoundActive(true);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "When it's your turn you skip the turn and play after everyone"; 
	}
	
	

}
