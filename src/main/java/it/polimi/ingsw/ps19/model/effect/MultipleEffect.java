package it.polimi.ingsw.ps19.model.effect;

import it.polimi.ingsw.ps19.model.Player;

/**
 * This effect takes two effects and apply them in a row.
 *
 * @author Jimmy
 */
public class MultipleEffect extends Effect {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7018409853938720869L;
	
	/** The first effect. */
	Effect firstEffect;
	
	/** The second effect. */
	Effect secondEffect;

	
	
	/**
	 * Instantiates a new multiple effect.
	 *
	 * @param firstEffect the first effect
	 * @param secondEffect the second effect
	 */
	public MultipleEffect(Effect firstEffect, Effect secondEffect){
		this.firstEffect = firstEffect;
		this.secondEffect = secondEffect;	

	}
	


	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.effect.Effect#applyEffect(it.polimi.ingsw.ps19.Player)
	 */
	public void applyEffect(Player p) {
		firstEffect.applyEffect(p);
		secondEffect.applyEffect(p);
		System.out.println("multiple, effetto applicato");
	}
	
	
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append(firstEffect.toString() + "\n");
		string.append("\t\t  and " + secondEffect.toString());
		return string.toString();
	}

}
