package it.polimi.ingsw.ps19.model.excommunicationtile;

import it.polimi.ingsw.ps19.Dice;
import it.polimi.ingsw.ps19.FamilyMember;
import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.model.effect.Effect;

/**
 * The Class ColoredFamiliarsVariationEffect.
 *
 * @author matteo
 */
public class ColoredFamiliarsVariationEffect extends Effect{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3421157855376061112L;
	
	/** The variation. */
	private int variation;
	
	/**
	 * Instantiates a new colored familiars variation effect.
	 *
	 * @param variation the variation
	 */
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
	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Your colored family members' action variation is decreased by"+variation;
	}
	

}
