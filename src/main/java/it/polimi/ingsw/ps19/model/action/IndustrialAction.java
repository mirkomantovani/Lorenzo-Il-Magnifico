package it.polimi.ingsw.ps19.model.action;

import java.util.ArrayList;

import it.polimi.ingsw.ps19.FamilyMember;
import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.exception.NotApplicableException;
import it.polimi.ingsw.ps19.model.area.ActionSpace;
import it.polimi.ingsw.ps19.model.area.IndustrialArea;
import it.polimi.ingsw.ps19.model.card.DevelopmentCard;

/**
 * @author Jimmy
 *
 */
public class IndustrialAction extends Action {
	
	IndustrialArea industrialArea;
	ArrayList<? extends DevelopmentCard> playerCards;

	public IndustrialAction(FamilyMember familyMember, IndustrialArea industrialArea) {
		super(familyMember);
		this.industrialArea = industrialArea;
	}

	
	public void apply(ActionSpace actionSpace, Player player) throws NotApplicableException {
		if(actionSpace.getEffect()!=null){
			actionSpace.getEffect().applyEffect(player);
		}
		for(DevelopmentCard card : industrialArea.getPlayerCards(this.getPlayer())){
			if(isApplicable(card))
				card.getPermanentEffect().applyEffect(this.getPlayer());
		}
	}

	@Override
	public boolean isApplicable() {
		return false;
	}
	
	public boolean isApplicable(DevelopmentCard card){
		return this.familyMember.getActionValue() + this.getPlayer().getBonuses()
				.getActivationVariation(card.getCardType()) >= card.getActivationCost(); 		
	}

	@Override
	public void apply() throws NotApplicableException {  
		//This inherited methd is not used
	}	

}
