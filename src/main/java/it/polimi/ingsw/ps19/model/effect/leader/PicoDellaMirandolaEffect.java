package it.polimi.ingsw.ps19.model.effect.leader;

import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.model.effect.Effect;

/**
 * @author matteo
 *
 */
public class PicoDellaMirandolaEffect extends Effect implements Disapplyable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6583399799257349121L;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("When you take a development card, if it costs coins, you save up 3 coins."
				+ "This is not a discount on the coins that you must pay in case of occupied tower");
		return builder.toString();
	}

	@Override
	public void applyEffect(Player p) {
		p.getBonuses().setCardCostCoinDiscount(3);
	}
	
	public void disapplyEffect(Player p){
		p.getBonuses().setCardCostCoinDiscount(0);
	}

	
}
