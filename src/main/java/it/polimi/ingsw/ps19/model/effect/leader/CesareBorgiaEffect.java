package it.polimi.ingsw.ps19.model.effect.leader;

import it.polimi.ingsw.ps19.model.Player;
import it.polimi.ingsw.ps19.model.effect.Effect;

/**
 * The Class CesareBorgiaEffect.
 * This class represents the effect of the leader card with the same name
 *
 * @author matteo
 */
public class CesareBorgiaEffect extends Effect implements Disapplyable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1765669019505493547L;

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.effect.Effect#applyEffect(it.polimi.ingsw.ps19.Player)
	 */
	@Override
	public void applyEffect(Player p) {
		p.getBonuses().setNoMilitaryPointsRequiredForTerritories(true);
		
	}
	
	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.effect.leader.Disapplyable#disapplyEffect(it.polimi.ingsw.ps19.Player)
	 */
	public void disapplyEffect(Player p){
		p.getBonuses().setNoMilitaryPointsRequiredForTerritories(false);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("You can pick territory cards without having the military points required");
		return builder.toString();
	}

	
	
}
