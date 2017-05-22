package it.polimi.ingsw.ps19.model.effect.leader;

import it.polimi.ingsw.ps19.model.effect.Effect;

/**
 * @author matteo
 *
 */
public class FedericoDaMontefeltroEffect extends Effect{

	@Override
	public void applyEffect() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Choose one familiar excepts for your neutral one and set its action value "
				+ "to six");
		return builder.toString();
	}

	
}
