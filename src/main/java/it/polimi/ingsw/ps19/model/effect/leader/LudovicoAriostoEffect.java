package it.polimi.ingsw.ps19.model.effect.leader;

import it.polimi.ingsw.ps19.model.Player;
import it.polimi.ingsw.ps19.model.effect.Effect;

/**
 * The Class LudovicoAriostoEffect.
 * This class represents the effect of the leader card with the same name
 *
 * @author matteo
 */
public class LudovicoAriostoEffect extends Effect implements Disapplyable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2968684924820150729L;

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("You can place your familiars in action spaces alredy taken.");
		return builder.toString();
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.effect.Effect#applyEffect(it.polimi.ingsw.ps19.Player)
	 */
	@Override
	public void applyEffect(Player p) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.effect.leader.Disapplyable#disapplyEffect(it.polimi.ingsw.ps19.Player)
	 */
	@Override
	public void disapplyEffect(Player p) {
		// TODO Auto-generated method stub
		
	}
	
	

}
