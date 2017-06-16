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
	ActionSpace actionSpace;

	
	
	/**
	 * 
	 * @param familyMember
	 * 				A familyMember is passed so that the action can identify the player that is calling the action  // totaly not needed
	 * @param industrialArea
	 * 				The industrialArea will tell whether the industrial area refers to a production area or a harvest area 
	 * @param actionSpace
	 * 				This needs to say whether it is passed a single or a multiple slot through its effect
	 */
	public IndustrialAction(FamilyMember familyMember, IndustrialArea industrialArea, ActionSpace actionSpace) {
		//Non mi sembra necessario controllare se il familyMember è diverso da null o meno in quanto alla fine sarà il comando a garantire tale condizione
		super(familyMember);
		this.industrialArea = industrialArea;
		this.actionSpace = actionSpace;
		System.out.println(this.player.getName());
	}


	@Override
	public boolean isApplicable() {
		return false;
	}
	
	public boolean isApplicable(DevelopmentCard card){
		System.out.println("action value fm: " + this.familyMember.getActionValue());
		System.out.println("players bonuses: " + this.getPlayer().getBonuses()
				.getActivationVariation(card.getCardType()));
		System.out.println("card activation cost" + card.getActivationCost());
		return this.familyMember.getActionValue() + this.getPlayer().getBonuses()
				.getActivationVariation(card.getCardType()) >= card.getActivationCost(); 		
	}

	@Override
	public void apply() throws NotApplicableException {  
		if(actionSpace.getEffect()!=null){
			System.out.println("Non è null quindi è multipla");
			actionSpace.getEffect().applyEffect(player);
		}
		for(DevelopmentCard card : industrialArea.getPlayerCards(this.player)){
			if(isApplicable(card))
				card.getPermanentEffect().applyEffect(this.player);
			else
				System.out.println("Not applicable!");
		}
	}	

}
