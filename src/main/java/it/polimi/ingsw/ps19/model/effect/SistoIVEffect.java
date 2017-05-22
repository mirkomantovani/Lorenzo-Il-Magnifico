package it.polimi.ingsw.ps19.model.effect;

/**
 * @author matteo
 *
 */
public class SistoIVEffect extends Effect{

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("At the end of each period, if you support the church, you gain additional"
				+ "5 victory points ");
		return builder.toString();
	}

	@Override
	public void applyEffect() {
		// TODO Auto-generated method stub
		
	}

	
	
}
