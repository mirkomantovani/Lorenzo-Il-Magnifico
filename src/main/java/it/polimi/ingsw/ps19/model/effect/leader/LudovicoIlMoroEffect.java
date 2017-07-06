package it.polimi.ingsw.ps19.model.effect.leader;

import it.polimi.ingsw.ps19.FamilyMember;
import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.model.effect.Effect;

/**
 * The Class LudovicoIlMoroEffect.
 *
 * @author matteo
 */
public class LudovicoIlMoroEffect extends Effect implements Disapplyable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2211372359567972605L;

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.effect.Effect#applyEffect(it.polimi.ingsw.ps19.Player)
	 */
	@Override
	public void applyEffect(Player p) {
		
		for(FamilyMember member : p.getFamilyMembers().values()){
			member.setActionValueImposition(5);
		}
		
	}
	
	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.effect.leader.Disapplyable#disapplyEffect(it.polimi.ingsw.ps19.Player)
	 */
	public void disapplyEffect(Player p){
		
		for(FamilyMember member : p.getFamilyMembers().values()){
			member.setActionValueImposition(0);
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Your familiars, excepts for the neutral one, have an action value of 5."
				+ "The related dice value is not relevant. You can also increase the action "
				+ "value by spending servants or using character cards effects");
		return builder.toString();
	}

	
	
}
