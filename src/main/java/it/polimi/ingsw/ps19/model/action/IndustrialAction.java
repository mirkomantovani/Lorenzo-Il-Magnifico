package it.polimi.ingsw.ps19.model.action;

import java.util.ArrayList;

import it.polimi.ingsw.ps19.FamilyMember;
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

	@Override
	public void apply() throws NotApplicableException {
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
		return this.familyMember.getActionValue() + this.getPlayer().getBonuses().getHarvestVariation() >= card.getHarvestActivationCost() || this.familyMember.getActionValue() + this.getPlayer().getBonuses().getProductionVariation() >= card.getProductionActivationCost();
		
	}	

}
