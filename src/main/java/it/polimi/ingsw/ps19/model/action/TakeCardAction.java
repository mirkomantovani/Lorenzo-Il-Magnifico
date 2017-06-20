package it.polimi.ingsw.ps19.model.action;

import java.util.List;

import it.polimi.ingsw.ps19.Color;
import it.polimi.ingsw.ps19.FamilyMember;
import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.constant.CardConstants;
import it.polimi.ingsw.ps19.exception.NotApplicableException;
import it.polimi.ingsw.ps19.model.area.Board;
import it.polimi.ingsw.ps19.model.area.Floor;
import it.polimi.ingsw.ps19.model.card.CardType;
import it.polimi.ingsw.ps19.model.card.DevelopmentCard;
import it.polimi.ingsw.ps19.model.resource.Coin;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;
import it.polimi.ingsw.ps19.model.resource.ResourceType;
import it.polimi.ingsw.ps19.model.resource.Servant;

/**
 * This class represents the action of placing a family member onto an action
 * space of a tower and taking the corresponding card, it throws a NotApplicableException with a field
 * that states why the action is not applicable 
 * 
 * @author Mirko
 * 
 *         
 *
 */
public class TakeCardAction extends Action {

	private DevelopmentCard card;
	private Servant paidServants;
	private Floor floor;
	private int actionValueVariation;
	private String notApplicableCode = "You can't apply this action";

	public TakeCardAction(FamilyMember familyMember, Floor floor, Servant paidServants) {
		super(familyMember);

		System.out.println("takecardaction: costruttore");

		this.card = floor.getCard();
		this.paidServants = paidServants;
		this.floor = floor;
		this.actionValueVariation = calculateActionValueVariation();

	}

	private int calculateActionValueVariation() {

		return this.player.getBonuses().getCardTypeActionVariation(this.card.getCardType());

	}

	@Override
	public void apply() throws NotApplicableException {
		if (this.isApplicable()) {

			System.out.println("takecardaction: IS APPLICABLE and i'm applying");

			player.addCard(card);
			floor.setCard(null); // set to null when the player buys the card
			player.subResources(card.getCost());
			// if the player has a discount given by a leader card
			player.removeFamilyMember(familyMember.getColor());
			player.getResourceChest().addResource(new Coin(player.getBonuses().getCardCostCoinDiscount()));
			card.getImmediateEffect().applyEffect(familyMember.getPlayer());
			if (player.getBonuses().isDoubleResourcesFromCards())
				card.getImmediateEffect().applyEffect(familyMember.getPlayer());

			this.floor.getActionSpace().setFamilyMember(familyMember);

			this.floor.getActionSpace().getEffect().applyEffect(player);
			// TODO rimuovere familyMember dal player!
		} else
			throw new NotApplicableException(notApplicableCode);
	}

	@Override
	public boolean isApplicable() {

		if (!validParams())
			return false;
		if (this.card.getCardType() == CardType.TERRITORY) {
			if (!player.getBonuses().isNoMilitaryPointsRequiredForTerritories()) {
				if ((int) Board.getMilitaryRequirementsForTerritories()
						.get(player.getDeckOfType(card.getCardType()).size() + 1) > player.getResourceChest()
								.getResourceInChest(ResourceType.MILITARYPOINT).getAmount())
					this.notApplicableCode = "you don't have the required military points to take this card";
				return false;
			}
		}

		// leader card discount (coin)
		ResourceChest realCost;
		if (player.getBonuses().getCardCostCoinDiscount() != 0) {

			try {
				realCost = (ResourceChest) card.getCost().clone();
				realCost.subResource(new Coin(player.getBonuses().getCardCostCoinDiscount()));
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
				realCost = new ResourceChest();
			}
		} else {
			realCost = card.getCost();

		}
		
		//adding 3 additional coins if someone else is occupying the tower
		if (isSomeoneInTheTower())
			realCost.addResource(new Coin(3));

		if (!player.getResourceChest().isGreaterEqualThan(realCost)) {
			this.notApplicableCode = "you don't have enough resources to take this card";
			return false;
		}
		// controlling if the player has space in the corresponding deck
		if (player.getDeckOfType(card.getCardType()).size() >= CardConstants.MAX_PERSONAL_DECK_SIZE) {
			this.notApplicableCode = "you don't have space in your personal board to take this card";
			return false;
		}
		return this.canBePlaced();

	}

	private boolean isSomeoneInTheTower() {
		List<Floor> floors;
		floors = this.floor.getTower().getFloors();
		for (Floor fl : floors) {
			if (fl.getActionSpace().isOccupied())
				return true;
		}
		return false;

	}

	private boolean validParams() {
		if (this.familyMember == null) {
			this.notApplicableCode = "you don't have this family member";
			return false;
		}
		if (this.floor == null) {
			this.notApplicableCode = "ERROR: floor is null";
			return false;
		}
		if (this.card == null) {
			this.notApplicableCode = "there is not a card on that floor";
			return false;
		}
		return true;
	}

	/**
	 * @return true if this.familymember can be placed in this.actionspace
	 */
	private boolean canBePlaced() {
		// I have to control the special effects e.g. ludovico ariosto
		if (!(this.isActionValueEnough() && !floor.getActionSpace().isOccupied()
				&& (familyMember.getDice().getColor() == Color.NEUTRAL
						|| this.noSamePlayerMembers(familyMember.getPlayer())))) {
			this.notApplicableCode = "your family member can't be placed here because either the floor is occupied, your actionvalue isn't enough"
					+ "or another floor of this tower is occupied by a family member of your color";
			return false;
		}
		return true;

	}

	/**
	 * @param player
	 * @return true if there are no other family members of the specified player
	 *         in the tower
	 */
	private boolean noSamePlayerMembers(Player player) {
		List<Floor> floors;
		floors = this.floor.getTower().getFloors();
		for (Floor fl : floors) {
			if (fl.getActionSpace().isOccupied() && fl != this.floor
					&& fl.getActionSpace().getFamilyMember().getPlayer() == player)
				return false;
		}

		return true;
	}

	/**
	 * //controlling if the action value of the family member is enough to place
	 * it in this action space
	 * 
	 * @return
	 */
	private boolean isActionValueEnough() {
		// personal bonuses to add
		return (familyMember.getActionValue() + this.paidServants.getAmount() + this.actionValueVariation >= this.floor
				.getActionSpace().getActionValueRequired());

	}

}
