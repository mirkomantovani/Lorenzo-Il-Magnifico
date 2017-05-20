package it.polimi.ingsw.ps19.model.effect;

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
		builder.append("One of your Familiar, between those linked with a dice, "
				+ "has an action value of six, that could be increased by spending servants");
		return builder.toString();
	}

	
}
