package it.polimi.ingsw.ps19.model.action;

import java.util.List;

import it.polimi.ingsw.ps19.Color;
import it.polimi.ingsw.ps19.FamilyMember;
import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.model.area.Floor;
import it.polimi.ingsw.ps19.model.card.CardConstants;
import it.polimi.ingsw.ps19.model.card.DevelopmentCard;
import it.polimi.ingsw.ps19.model.resource.Servant;

/**
 * This class represents the action of placing a family member onto an action
 * space of a tower and taking the corresponding card
 * 
 * @author Mirko
 *
 */
public class TakeCardAction extends Action {

	private DevelopmentCard card;
	private Servant paidServants;
	private Floor floor;
	private int actionValueVariation;

	public TakeCardAction(FamilyMember familyMember, Floor floor, Servant paidServants) {
		super(familyMember);
		this.card = floor.getCard();
		this.paidServants = paidServants;
		this.floor = floor;
		this.actionValueVariation=calculateActionValueVariation();

	}

	private int calculateActionValueVariation() {
		return this.player.getBonuses().getCardTypeActionVariation(
				this.card.getCardType());
		
	}

	@Override
	public void apply() throws NotApplicableException {
		if (this.isApplicable()) {
			player.addCard(card);
			player.getResourceChest().subChest(card.getCost());
			card.getImmediateEffect().applyEffect(familyMember.getPlayer());
		} else
			throw new NotApplicableException();
	}

	@Override
	public boolean isApplicable() {  //TODO: excommunicationeffect, bonus effects of the player
		//family member in tower, floor.card, actionvalue of familymember must me greater than actionspace.actionvaluerequired
		//controlling if the player can afford the price
		if (!player.getResourceChest().isGreaterEqualThan(card.getCost()))
			return false;
		//controlling if the player has space in the corresponding deck 
		if (player.getRightArrayList(card.getCardType()).size() >= CardConstants.MAX_PERSONAL_DECK_SIZE)
			return false;

		return this.canBePlaced();

	}
	
	/**
	 * @return true if this.familymember can be placed in this.actionspace
	 */
	private boolean canBePlaced(){
		//I have to control the special effects e.g. ludovico ariosto
		return this.isActionValueEnough()&&!floor.getActionSpace().isOccupied()
				&&(familyMember.getDice().getColor()==Color.NEUTRAL
					||this.noSamePlayerMembers(familyMember.getPlayer()));
		
	}
	
	/**
	 * @param player
	 * @return true if there are no other family members of the specified player in the tower
	 */
	private boolean noSamePlayerMembers(Player player) {
		List<Floor> floors;
		floors=this.floor.getTower().getFloors();
		for(Floor fl : floors){
			if(fl!=this.floor
				&&fl.getActionSpace().getFamilyMember().getPlayer()==player)return false;
		}
		
		return true;
	}

	/**
	 * //controlling if the action value of the family member is enough to place it in this action space
	 * @return
	 */
	private boolean isActionValueEnough(){
		//personal bonuses to add
		return (familyMember.getActionValue()+this.paidServants.getAmount()+this.actionValueVariation
			>=this.floor.getActionSpace().getActionValueRequired());
		
	}

}
