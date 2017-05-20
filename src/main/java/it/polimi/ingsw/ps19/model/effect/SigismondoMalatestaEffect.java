package it.polimi.ingsw.ps19.model.effect;


/**
 * @author matteo
 *
 */
public class SigismondoMalatestaEffect extends Effect {

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Your neutral familiar has a +3 bonus on its action value. You can also "
				+ "increase this value by spending servants or by using a character card effect.");
		return builder.toString();
	}

	@Override
	public void applyEffect() {
		// TODO Auto-generated method stub
		
	}
	
	

}
