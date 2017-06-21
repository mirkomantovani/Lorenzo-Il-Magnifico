package it.polimi.ingsw.ps19.model.action;

import java.util.ArrayList;

import it.polimi.ingsw.ps19.FamilyMember;
import it.polimi.ingsw.ps19.exception.NotApplicableException;
import it.polimi.ingsw.ps19.model.area.ActionSpace;
import it.polimi.ingsw.ps19.model.area.IndustrialArea;
import it.polimi.ingsw.ps19.model.card.DevelopmentCard;
import it.polimi.ingsw.ps19.model.resource.Servant;

/**
 * @author Jimmy
 *
 */
public class IndustrialAction extends Action {
	
	IndustrialArea industrialArea;
	ArrayList<? extends DevelopmentCard> playerCards;
	ActionSpace actionSpace;
	int paidServants;

	
	
	/**
	 * 
	 * @param familyMember
	 * 				A familyMember is passed so that the action can identify the player that is calling the action  // totaly not needed
	 * @param industrialArea
	 * 				The industrialArea will tell whether the industrial area refers to a production area or a harvest area 
	 * @param actionSpace
	 * 				This needs to say whether it is passed a single or a multiple slot through its effect
	 * 
	 * @param paid servants that increment player's family member action value
	 */
	public IndustrialAction(FamilyMember familyMember, IndustrialArea industrialArea, ActionSpace actionSpace, int paidServants) {
		//Non mi sembra necessario controllare se il familyMember è diverso da null o meno in quanto alla fine sarà il comando a garantire tale condizione
		super(familyMember);
		this.industrialArea = industrialArea;
		this.actionSpace = actionSpace;
		System.out.println("Industrial action: " + this.player.getName());
		this.paidServants = paidServants;
	}


	@Override
	public boolean isApplicable() {
		return false;
	}
	
	public boolean isApplicable(DevelopmentCard card){
			System.out.println("Industrial Action: SONO NELLA APPLICABLE E STO CONTROLLANDO LA CARTA: !" + card.toString());
			System.out.println("action value fm: " + this.familyMember.getActionValue());
			System.out.println("players bonuses: " + this.getPlayer().getBonuses()
				.getActivationVariation(card.getCardType()));
			System.out.println("card activation cost" + card.getActivationCost());
			return (this.familyMember.getActionValue() + this.getPlayer().getBonuses()
					.getActivationVariation(card.getCardType()) + paidServants >= card.getActivationCost());
	}

	private boolean canBePlaced() {
		System.out.println("\nINDUSTRIAL ACTION: Sono nella canBePlaced\n");
		return actionSpace.isOccupable(this.familyMember);
	}


	@Override
	public void apply() throws NotApplicableException {  
		if(canBePlaced()){
			actionSpace.setFamilyMember(familyMember);
			this.player.removeFamilyMember(familyMember.getColor());
			this.player.getResourceChest().subResource(new Servant(paidServants));
			System.out.println("\nIndustrial action: sto applicando la industrialAction\n");
			if(actionSpace.getEffect()!=null){
				System.out.println("\nNon è null quindi è una action space multipla\n");
				actionSpace.getEffect().applyEffect(player);
			}
			for(DevelopmentCard card : industrialArea.getPlayerCards(this.player)){
				if(isApplicable(card)){
					System.out.println("\nIndustrial action: LA CARTA DI PUO' ATTIVARE!\n");
					card.getPermanentEffect().applyEffect(this.player);
					System.out.println("\nINDUSTRIAL ACTION: STO ATTIVANDO LA CARTA: " + card.toString() + "\n");			
				}
				else
					System.out.println("CannotActivate card effect");
			}
		}else{
			System.out.println("You cannot put your member here");
			throw new NotApplicableException("You cannot put your member here!");
		}
	}	

}
