package it.polimi.ingsw.ps19.model.effect.leader;

import it.polimi.ingsw.ps19.model.effect.CardEffect;

/**
 * @author matteo
 *
 */
public class LudovicoAriostoEffect extends CardEffect {

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("You can place your familiars in action spaces alredy taken.");
		return builder.toString();
	}

	@Override
	public void applyEffect() {
		// TODO Auto-generated method stub
		
	}
	
	

}
