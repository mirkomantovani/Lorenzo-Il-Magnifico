package it.polimi.ingsw.ps19.model.effect.leader;

import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.model.effect.Effect;

/**
 * The Class FilippoBrunelleschiEffect.
 * This class represents the effect of the leader card with the same name
 *
 * @author matteo
 */
public class FilippoBrunelleschiEffect extends Effect implements Disapplyable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 770128159487460222L;

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("You can place one familiar in a tower alredy occupied without paying 3 coins");
		return builder.toString();
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.effect.Effect#applyEffect(it.polimi.ingsw.ps19.Player)
	 */
	@Override
	public void applyEffect(Player p) {
		p.getBonuses().setDiscountOccupiedTower(true);
	}
	
	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.effect.leader.Disapplyable#disapplyEffect(it.polimi.ingsw.ps19.Player)
	 */
	public void disapplyEffect(Player p){
		p.getBonuses().setDiscountOccupiedTower(false);
	}

	
	
}
