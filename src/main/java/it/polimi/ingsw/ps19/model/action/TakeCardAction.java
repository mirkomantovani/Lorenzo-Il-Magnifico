package it.polimi.ingsw.ps19.model.action;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.ps19.Color;
import it.polimi.ingsw.ps19.FamilyMember;
import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.constant.CardConstants;
import it.polimi.ingsw.ps19.exception.NotApplicableException;
import it.polimi.ingsw.ps19.model.area.Board;
import it.polimi.ingsw.ps19.model.area.Floor;
import it.polimi.ingsw.ps19.model.card.CardType;
import it.polimi.ingsw.ps19.model.card.CharacterCard;
import it.polimi.ingsw.ps19.model.card.DevelopmentCard;
import it.polimi.ingsw.ps19.model.effect.Effect;
import it.polimi.ingsw.ps19.model.resource.Coin;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;
import it.polimi.ingsw.ps19.model.resource.ResourceType;
import it.polimi.ingsw.ps19.model.resource.Servant;

/**
 * This class represents the action of placing a family member onto an action
 * space of a tower and taking the corresponding card, it throws a NotApplicableException with a field
 * that states why the action is not applicable .
 *
 * @author Mirko
 * 
 *         
 */
public class TakeCardAction extends Action {

	/** The card. */
	private DevelopmentCard card;
	
	/** The paid servants. */
	private Servant paidServants;
	
	/** The floor. */
	private Floor floor;
	
	/** The action value variation. */
	private int actionValueVariation;
	
	/** The not applicable code. */
	private String notApplicableCode = "You can't apply this action";

	/**
	 * Instantiates a new take card action.
	 *
	 * @param familyMember the family member
	 * @param floor the floor
	 * @param paidServants the paid servants
	 */
	public TakeCardAction(FamilyMember familyMember, Floor floor, Servant paidServants) {
		super(familyMember);

		System.out.println("takecardaction: costruttore");

		this.card = floor.getCard();
		this.paidServants = paidServants;
		this.floor = floor;
		
		System.out.println("takecardaction: prima del calculateactionvaluevariation");
		
		this.actionValueVariation = calculateActionValueVariation();
		
		System.out.println("takecardaction: finished constructing action");

	}

	/**
	 * Calculate action value variation.
	 *
	 * @return the int
	 */
	private int calculateActionValueVariation() {

		return this.player.getBonuses().getCardTypeActionVariation(this.card.getCardType());

	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.action.Action#apply()
	 */
	@Override
	public void apply() throws NotApplicableException {
		if (this.isApplicable()) {

			System.out.println("takecardaction: IS APPLICABLE and i'm applying");

			player.addCard(card);
			floor.setCard(null); // set to null when the player buys the card
			// if the player has a discount given by a leader card
			player.removeFamilyMember(familyMember.getColor());
			
			
			System.out.println("TakeCard calculating realcost");
			
			ResourceChest realCost;
			realCost = card.getCost().cloneChest();
			if (player.getBonuses().getCardCostCoinDiscount() != 0) {
					realCost.subResource(new Coin(player.getBonuses().getCardCostCoinDiscount()));
			}
			
			if (isSomeoneInTheTower())
				realCost.addResource(new Coin(3));
			
			realCost.addResource(paidServants);
			
			System.out.println("TakeCard sub resources");
			
			player.subResources(realCost);
			
			System.out.println("TakeCard beforeapplyingimmediate");
			
			card.getImmediateEffect().applyEffect(familyMember.getPlayer());
			
			System.out.println("takecard before isdoubleresourcefromcards");
			
			if (player.getBonuses().isDoubleResourcesFromCards())
				card.getImmediateEffect().applyEffect(familyMember.getPlayer());
			
			if(card instanceof CharacterCard){
				Effect permanent=card.getPermanentEffect();
				if(permanent!=null)
					permanent.applyEffect(familyMember.getPlayer());
			}
				
			
			System.out.println("TakeCard after applyingimmediate");

			this.floor.getActionSpace().setFamilyMember(familyMember);

			this.floor.getActionSpace().getEffect().applyEffect(player);
			
			
		} else
			throw new NotApplicableException(notApplicableCode);
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.action.Action#isApplicable()
	 */
	@Override
	public boolean isApplicable() {

		if (!validParams())
			return false;
		if (this.card.getCardType() == CardType.TERRITORY) {
			if (!player.getBonuses().isNoMilitaryPointsRequiredForTerritories()) {
				System.out.println("takecardaction sono nella isnomilitarypoints");
				
				
				try{
//				List l=Board.getMilitaryRequirementsForTerritories();
//				l=new ArrayList<>();
//				
//				int a=(int) (Board.getMilitaryRequirementsForTerritories().get(0));
//				int b=(int) (Board.getMilitaryRequirementsForTerritories().get(1));
//				int c=(int) (Board.getMilitaryRequirementsForTerritories().get(2));
//				int d=(int) (Board.getMilitaryRequirementsForTerritories().get(3));
//				System.out.println(a);
//				System.out.println(b);
//				System.out.println(c);
//				System.out.println(d);
//				
//				l.forEach(point -> System.out.println(point));
				
//				System.out.println(l.get(0));
//				System.out.println(l.get(1));
//				System.out.println(l.get(2));
				
				int len=player.getDeckOfType(card.getCardType()).size();
				
				System.out.println("player deck length :"+player.getDeckOfType(card.getCardType()).size());
				
				System.out.println("takecard i punti richiesti sono:"+((int) (Board.getMilitaryRequirementsForTerritories().get(len))));
				}catch(Exception e){
					e.printStackTrace();
				}
				
				
				
				
				System.out.println("takecard punti del player");
				if ((int) (Board.getMilitaryRequirementsForTerritories().get
						(player.getDeckOfType(card.getCardType()).size())) > player.getResourceChest()
								.getResourceInChest(ResourceType.MILITARYPOINT).getAmount()){
					this.notApplicableCode = "you don't have the required military points to take this card";
				return false;
				}
			}
		}
			
			System.out.println("takecardaction params valid");
		
		// leader card discount (coin)
		ResourceChest realCost;
		
		realCost = (ResourceChest) card.getCost().cloneChest();
		if (player.getBonuses().getCardCostCoinDiscount() != 0) 
			realCost.subResource(new Coin(player.getBonuses().getCardCostCoinDiscount()));
			
		
		
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
		System.out.println("let's control if can be placed");
		return this.canBePlaced();

	}

	/**
	 * Checks if is someone in the tower.
	 *
	 * @return true, if is someone in the tower
	 */
	private boolean isSomeoneInTheTower() {
		List<Floor> floors;
		floors = this.floor.getTower().getFloors();
		for (Floor fl : floors) {
			if (fl.getActionSpace().isOccupied())
				return true;
		}
		return false;

	}

	/**
	 * Valid params.
	 *
	 * @return true, if successful
	 */
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
	 * Can be placed.
	 *
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
	 * No same player members.
	 *
	 * @param player the player
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
	 * it in this action space.
	 *
	 * @return true, if is action value enough
	 */
	private boolean isActionValueEnough() {
		// personal bonuses to add
		return (familyMember.getActionValue() + this.paidServants.getAmount() + this.actionValueVariation >= this.floor
				.getActionSpace().getActionValueRequired());

	}

}
