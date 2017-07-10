package it.polimi.ingsw.ps19.model.effect;

import it.polimi.ingsw.ps19.model.Player;

/**
 * The Class NoEffect was needed in order not to check if a card's effect is null
 * before applying it
 * 
 * @author Mirko
 *
 */
public class NoEffect extends Effect {
	
	

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7758404621897411258L;

	/* 
	 * It's being left blank on purpose
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
