package it.polimi.ingsw.ps19.model.excommunicationtile;

import it.polimi.ingsw.ps19.Dice;
import it.polimi.ingsw.ps19.FamilyMember;
import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.model.effect.Effect;

public class ColoredFamiliarsVariationEffect extends Effect{
	
	private int variation;
	
	public ColoredFamiliarsVariationEffect(int variation){
		this.variation = variation;
	}

	@Override
	public void applyEffect(Player player) {
		for(FamilyMember f : player.getFamilyMembers().values()){
			if(f.getDice() != Dice.NEUTRAL_DICE){
				f.addActionValueVariation(variation);
			}
		}
			
		
	}
	
	

}
