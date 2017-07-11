package it.polimi.ingsw.ps19.model.effect;

import it.polimi.ingsw.ps19.model.Player;
import it.polimi.ingsw.ps19.model.effect.leader.Disapplyable;

/**
 * The Class ProductionBonusEffect.
 *
 * @author matteo
 */
public class ProductionBonusEffect extends Effect implements Disapplyable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -546425703104740489L;
	
	/** The value. */
	int value;
	
	/**
	 * Instantiates a new production bonus effect.
	 *
	 * @param value the value
	 */
	public ProductionBonusEffect(int value){
		this.value = value;
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.effect.Effect#applyEffect(it.polimi.ingsw.ps19.Player)
	 */
	@Override
	public void applyEffect(Player p) {
		p.getBonuses().setProductionVariation(value);
		
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if(value >= 0)
			return "You gain a + " + value + " to your production value";
		else
			return "You gain a " + value + " to your production value";
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.effect.leader.Disapplyable#disapplyEffect(it.polimi.ingsw.ps19.Player)
	 */
	@Override
	public void disapplyEffect(Player p) {
		// TODO Auto-generated method stub
		
	}
	

}
