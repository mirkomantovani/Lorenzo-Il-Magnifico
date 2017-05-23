package it.polimi.ingsw.ps19.model.effect.leader;

import it.polimi.ingsw.ps19.model.effect.CardEffect;

/**
 * @author matteo
 *
 */
public class LudovicoIlMoroEffect extends CardEffect {

	@Override
	public void applyEffect() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Your familiars, excepts for the neutral one, have an action value of 5."
				+ "The related dice value is not relevant. You can also increase the action "
				+ "value by spending servants or using character cards effects");
		return builder.toString();
	}

	
	
}
