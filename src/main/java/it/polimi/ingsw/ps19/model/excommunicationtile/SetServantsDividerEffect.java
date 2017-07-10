package it.polimi.ingsw.ps19.model.excommunicationtile;

import it.polimi.ingsw.ps19.model.Player;
import it.polimi.ingsw.ps19.model.effect.Effect;

/**
 * This effect when applied only sets a value for the servantsDivider field in bonus of a player.
 *
 * @author Mirko
 */
public class SetServantsDividerEffect extends Effect {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6964246372125181142L;
	
	/** The divider. */
	private int divider;
	
	/**
	 * Instantiates a new sets the servants divider effect.
	 *
	 * @param divider the divider
	 */
	public SetServantsDividerEffect(int divider) {
		this.divider = divider;
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.effect.Effect#applyEffect(it.polimi.ingsw.ps19.Player)
	 */
	@Override
	public void applyEffect(Player player) {

		player.getBonuses().setServantsDivider(divider);
	
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "You have to spend 2 servants to raise the value of your action of 1";
	}
	
	

}
