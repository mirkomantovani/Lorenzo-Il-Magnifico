package it.polimi.ingsw.ps19.model.effect.leader;

import it.polimi.ingsw.ps19.model.FamilyMember;
import it.polimi.ingsw.ps19.model.Player;
import it.polimi.ingsw.ps19.model.effect.Effect;

/**
 * The Class FedericoDaMontefeltroEffect.
 * This class represents the effect of the leader card with the same name
 *
 * @author matteo
 */
public class FedericoDaMontefeltroEffect extends Effect implements Disapplyable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8059701163863953144L;

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.effect.Effect#applyEffect(it.polimi.ingsw.ps19.Player)
	 */
	@Override
	public void applyEffect(Player p) {

			for(FamilyMember member : p.getFamilyMembers().values()){
				member.setActionValueImposition(6);
			}
			
	}
	
	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.effect.leader.Disapplyable#disapplyEffect(it.polimi.ingsw.ps19.Player)
	 */
	public void disapplyEffect(Player p){
		for(FamilyMember member : p.getFamilyMembers().values()){
			member.setActionValueImposition(-1);
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Choose one familiar excepts for your neutral one and set its action value "
				+ "to six");
		return builder.toString();
	}

	
}
