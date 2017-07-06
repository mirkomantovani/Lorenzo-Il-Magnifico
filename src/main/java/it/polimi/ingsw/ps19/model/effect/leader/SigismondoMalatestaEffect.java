package it.polimi.ingsw.ps19.model.effect.leader;

import it.polimi.ingsw.ps19.Color;
import it.polimi.ingsw.ps19.Dice;
import it.polimi.ingsw.ps19.FamilyMember;
import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.model.effect.Effect;

/**
 * The Class SigismondoMalatestaEffect.
 * This class represents the effect of the leader card with the same name
 *
 * @author matteo
 */
public class SigismondoMalatestaEffect extends Effect implements Disapplyable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4556068502386357940L;

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Your neutral familiar has a +3 bonus on its action value. You can also "
				+ "increase this value by spending servants or by using a character card effect.");
		return builder.toString();
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.effect.Effect#applyEffect(it.polimi.ingsw.ps19.Player)
	 */
	@Override
	public void applyEffect(Player p) {
		for(FamilyMember member : p.getFamilyMembers().values()){
			if(member.getColor() == Color.NEUTRAL){
				member.setActionValueVariation(3);
			}
		}
		
	}
	
	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.effect.leader.Disapplyable#disapplyEffect(it.polimi.ingsw.ps19.Player)
	 */
	public void disapplyEffect(Player p){
		for(FamilyMember member : p.getFamilyMembers().values()){
			if(member.getDice() == Dice.NEUTRAL_DICE){
				member.setActionValueVariation(0);
			}
		}
		
	}
	
	

}
