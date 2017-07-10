package it.polimi.ingsw.ps19.model.effect.leader;

import it.polimi.ingsw.ps19.model.Player;
import it.polimi.ingsw.ps19.model.effect.Effect;

/**
 * The Class PicoDellaMirandolaEffect.
 * This class represents the effect of the leader card with the same name
 *
 * @author matteo
 */
public class PicoDellaMirandolaEffect extends Effect implements Disapplyable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6583399799257349121L;

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("When you take a development card, if it costs coins, you save up 3 coins."
				+ "This is not a discount on the coins that you must pay in case of occupied tower");
		return builder.toString();
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.effect.Effect#applyEffect(it.polimi.ingsw.ps19.Player)
	 */
	@Override
	public void applyEffect(Player p) {
		p.getBonuses().setCardCostCoinDiscount(3);
	}
	
	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.effect.leader.Disapplyable#disapplyEffect(it.polimi.ingsw.ps19.Player)
	 */
	public void disapplyEffect(Player p){
		p.getBonuses().setCardCostCoinDiscount(0);
	}

	
}
