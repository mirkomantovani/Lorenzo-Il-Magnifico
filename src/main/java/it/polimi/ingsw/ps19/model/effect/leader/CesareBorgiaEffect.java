package it.polimi.ingsw.ps19.model.effect.leader;

import it.polimi.ingsw.ps19.model.effect.CardEffect;

/**
 * @author matteo
 *
 */
public class CesareBorgiaEffect extends CardEffect {

	@Override
	public void applyEffect() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("You can pick territory cards without having the military points required");
		return builder.toString();
	}

	
	
}
