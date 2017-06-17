package it.polimi.ingsw.ps19.model.excommunicationtile;

import it.polimi.ingsw.ps19.Dice;
import it.polimi.ingsw.ps19.FamilyMember;
import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.model.effect.Effect;

/**
 * @author matteo
 * 
 *
 */
public class ColoredFamiliarsVariationEffect extends Effect{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3421157855376061112L;
	private int variation;
	
	public ColoredFamiliarsVariationEffect(int variation){
		this.variation = variation;
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.effect.Effect#applyEffect(it.polimi.ingsw.ps19.Player)
	 */
	@Override
	public void applyEffect(Player player) {
		for(FamilyMember f : player.getFamilyMembers().values()){
			if(f.getDice() != Dice.NEUTRAL_DICE){
				f.addActionValueVariation(variation);
			}
		}
			
		
	}
	

	@Override
	public String toString() {
		return "Your colored family members' action variation is decreased by"+variation;
	}
	

}
