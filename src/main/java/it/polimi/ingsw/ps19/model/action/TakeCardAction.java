package it.polimi.ingsw.ps19.model.action;

import java.util.List;

import it.polimi.ingsw.ps19.Color;
import it.polimi.ingsw.ps19.FamilyMember;
import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.constant.CardConstants;
import it.polimi.ingsw.ps19.exception.NotApplicableException;
import it.polimi.ingsw.ps19.model.area.Board;
import it.polimi.ingsw.ps19.model.area.Floor;
import it.polimi.ingsw.ps19.model.card.DevelopmentCard;
import it.polimi.ingsw.ps19.model.resource.Coin;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;
import it.polimi.ingsw.ps19.model.resource.ResourceType;
import it.polimi.ingsw.ps19.model.resource.Servant;

/**
 * This class represents the action of placing a family member onto an action
 * space of a tower and taking the corresponding card
 * 
 * @author Mirko
 * 
 *Should the actions be static?
 *
 */
public class TakeCardAction extends Action {

	private DevelopmentCard card;
	private Servant paidServants;
	private Floor floor;
	private int actionValueVariation;

	public TakeCardAction(FamilyMember familyMember, Floor floor, Servant paidServants) {
		super(familyMember);
		
		if(familyMember==null)System.out.println("fm null");
		if(player==null)System.out.println("player null");
		
		this.card = floor.getCard();
		this.paidServants = paidServants;
		this.floor = floor;
		this.actionValueVariation=calculateActionValueVariation();

	}

	private int calculateActionValueVariation() {
		
		if(player==null)System.out.println("player null");
		if(player.getBonuses()==null)System.out.println("bonus null");
		if(player.getBonuses().getCardActionValueVariation()==null)
			System.out.println("actionvalue null");
		
//		if(player==null)System.out.println("player null");
		return this.player.getBonuses().getCardTypeActionVariation(
				this.card.getCardType());
		
	}

	@Override
	public void apply() throws NotApplicableException {
		if (this.isApplicable()) {
			
			player.addCard(card);
			floor.setCard(null);  //set to null when the player buys the card
			player.getResourceChest().subChest(card.getCost());
			//if the player has a discount given by a leader card
			player.getResourceChest().addResource(new Coin(player.getBonuses().getCardCostCoinDiscount()));
			card.getImmediateEffect().applyEffect(familyMember.getPlayer());
			if(player.getBonuses().isDoubleResourcesFromCards())
			card.getImmediateEffect().applyEffect(familyMember.getPlayer());
			this.floor.getActionSpace().setFamilyMember(familyMember);
			
			this.floor.getActionSpace().getEffect().applyEffect(player);
		} else
			throw new NotApplicableException();
	}

	@Override
	public boolean isApplicable() {  
		
		if(!player.getBonuses().isNoMilitaryPointsRequiredForTerritories()){
			if((int)Board.getMilitaryRequirementsForTerritories().get(
					player.getDeckOfType(card.getCardType()).size()+1)>
			player.getResourceChest().getResourceInChest(ResourceType.MILITARYPOINT).getAmount())
				return false;
		}
		
		
		//leader card discount (coin)
		ResourceChest realCost;
		if(player.getBonuses().getCardCostCoinDiscount()!=0){
		
			try {
				realCost=(ResourceChest)card.getCost().clone();
				realCost.subResource(new Coin(player.getBonuses().getCardCostCoinDiscount()));
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
				realCost=new ResourceChest();
			}
			}
			else {
				realCost=card.getCost();
				
			}
		if (!player.getResourceChest().isGreaterEqualThan(realCost))
			return false;
		//controlling if the player has space in the corresponding deck 
		if (player.getDeckOfType(card.getCardType()).size() >= CardConstants.MAX_PERSONAL_DECK_SIZE)
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
			if(fl.getActionSpace().isOccupied()&&fl!=this.floor
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
		System.out.println(familyMember.getActionValue()+this.paidServants.getAmount()+this.actionValueVariation);
		System.out.println(this.floor.getActionSpace().getActionValueRequired());
		return (familyMember.getActionValue()+this.paidServants.getAmount()+this.actionValueVariation
			>=this.floor.getActionSpace().getActionValueRequired());
		
	}

}
