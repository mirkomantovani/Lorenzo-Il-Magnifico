package it.polimi.ingsw.ps19.model.effect.leader;

import it.polimi.ingsw.ps19.model.FamilyMember;
import it.polimi.ingsw.ps19.model.Player;
import it.polimi.ingsw.ps19.model.effect.Effect;

/**
 * The Class LucreziaBorgiaEffect.
 * This class represents the effect of the leader card with the same name
 *
 * @author matteo
 */
public class LucreziaBorgiaEffect extends Effect implements Disapplyable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 9063070050082460745L;

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.effect.Effect#applyEffect(it.polimi.ingsw.ps19.Player)
	 */
	@Override
	public void applyEffect(Player p) {
		for(FamilyMember member : p.getFamilyMembers().values()){
			member.setActionValueVariation(2);
		}
		
	}
	
	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.effect.leader.Disapplyable#disapplyEffect(it.polimi.ingsw.ps19.Player)
	 */
	public void disapplyEffect(Player p){
		for(FamilyMember member : p.getFamilyMembers().values()){
			member.setActionValueVariation(0);
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Your familiars, excepts for the neutral one, have a +2 bonus to their"
				+ "action value. You can also increase the value by spending servants or by"
				+ "using character cards effects.");
		return builder.toString();
	}

	
	
}
