package it.polimi.ingsw.ps19.model.effect;


/**
 * @author matteo
 *
 */
public class LudovicoAriostoEffect extends Effect {

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
