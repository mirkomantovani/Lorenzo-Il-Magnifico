package it.polimi.ingsw.ps19.model.effect;

import it.polimi.ingsw.ps19.model.Player;

/**
 * The Class HarvestBonusEffect.
 *
 * @author matteo
 */
public class HarvestBonusEffect extends Effect {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2823865542154838135L;
	
	/** The value. */
	int value;
	
	/**
	 * Instantiates a new harvest bonus effect.
	 *
	 * @param value the value
	 */
	public HarvestBonusEffect(int value){
	this.value = value;	
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.effect.Effect#applyEffect(it.polimi.ingsw.ps19.Player)
	 */
	@Override
	public void applyEffect(Player p) {
		p.getBonuses().setHarvestVariation(value);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if(value >= 0)
			return "You gain a + " + value + " to your harvest value";
		else
			return "You gain a " + value + " to your harvest value";
	}
	

}
