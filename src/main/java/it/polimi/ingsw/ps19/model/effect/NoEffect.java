package it.polimi.ingsw.ps19.model.effect;

import it.polimi.ingsw.ps19.Player;

/**
 * The Class NoEffect.
 */
public class NoEffect extends Effect {
	
	

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7758404621897411258L;

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.effect.Effect#applyEffect(it.polimi.ingsw.ps19.Player)
	 */
	@Override
	public void applyEffect(Player player) {

	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "No effect";
	}
	
	

}
