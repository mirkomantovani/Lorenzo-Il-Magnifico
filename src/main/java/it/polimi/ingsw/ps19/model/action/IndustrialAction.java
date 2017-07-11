package it.polimi.ingsw.ps19.model.action;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.ps19.exception.NotApplicableException;
import it.polimi.ingsw.ps19.model.Color;
import it.polimi.ingsw.ps19.model.FamilyMember;
import it.polimi.ingsw.ps19.model.area.ActionSpace;
import it.polimi.ingsw.ps19.model.area.HarvestArea;
import it.polimi.ingsw.ps19.model.area.IndustrialArea;
import it.polimi.ingsw.ps19.model.area.MultipleActionSpace;
import it.polimi.ingsw.ps19.model.area.ProductionArea;
import it.polimi.ingsw.ps19.model.area.SingleActionSpace;
import it.polimi.ingsw.ps19.model.card.BuildingCard;
import it.polimi.ingsw.ps19.model.card.DevelopmentCard;
import it.polimi.ingsw.ps19.model.effect.ProductionEffect;
import it.polimi.ingsw.ps19.model.effect.ResourcesExchangeEffect;
import it.polimi.ingsw.ps19.model.resource.Servant;

/**
 * The Class IndustrialAction.
 *
 * @author Jimmy
 */
public class IndustrialAction extends Action {

	/** The industrial area. */
	IndustrialArea industrialArea;
	
	/** The player cards. */
	ArrayList<? extends DevelopmentCard> playerCards;
	
	/** The action space. */
	ActionSpace actionSpace;
	
	/** The paid servants. */
	int paidServants;

	/**
	 * Instantiates a new industrial action.
	 *
	 * @param familyMember            A familyMember is passed so that the action can identify the
	 *            player that is calling the action // totaly not needed
	 * @param industrialArea            The industrialArea will tell whether the industrial area
	 *            refers to a production area or a harvest area
	 * @param actionSpace            This needs to say whether it is passed a single or a multiple
	 *            slot through its effect
	 * @param paidServants the paid servants
	 */
	public IndustrialAction(FamilyMember familyMember, IndustrialArea industrialArea, ActionSpace actionSpace,
			int paidServants) {
	
		super(familyMember);
		this.industrialArea = industrialArea;
		this.actionSpace = actionSpace;
	
		this.paidServants = paidServants;
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.action.Action#isApplicable()
	 */
	@Override
	public boolean isApplicable() {
		return false;
	}

	/**
	 * Checks if is applicable.
	 *
	 * @param card the card
	 * @return true, if is applicable
	 */
	public boolean isApplicable(DevelopmentCard card) {
		return (this.familyMember.getActionValue()
				+ this.getPlayer().getBonuses().getActivationVariation(card.getCardType())
				+ paidServants >= card.getActivationCost());
	}

	/**
	 * Can be placed.
	 *
	 * @return true, if successful
	 */
	private boolean canBePlaced() {
		if(actionSpace instanceof MultipleActionSpace)
			return ((MultipleActionSpace) actionSpace).isOccupable(familyMember, paidServants, player.getBonuses().getActivationVariation(industrialArea.getAssociatedCardType()));
		}
		else if(actionSpace instanceof SingleActionSpace){
			System.out.println("industrial action: instance of Single action space");
			return ((SingleActionSpace) actionSpace).isOccupable(familyMember, paidServants, player.getBonuses().getActivationVariation(industrialArea.getAssociatedCardType()));
		}
		return actionSpace.isOccupable(this.familyMember);
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.action.Action#apply()
	 */
	@Override
	public void apply() throws NotApplicableException {
		if (actionSpace.getEffect() != null) {
			System.out.println("Action Space Effect: " + actionSpace.getEffect().toString());
			System.out.println("\nNon è null quindi è una action space multipla\n");
			actionSpace.getEffect().applyEffect(player);
		}
		
		if (canBePlaced()) {
			actionSpace.setFamilyMember(familyMember);
			this.player.removeFamilyMember(familyMember.getColor());
			this.player.getResourceChest().subResource(new Servant(paidServants));
			if (actionSpace.getEffect() != null) {
				actionSpace.getEffect().applyEffect(player);
			}
			
			for (DevelopmentCard card : industrialArea.getPlayerCards(this.player)) {
				if (isApplicable(card)) {
					card.getPermanentEffect().applyEffect(this.player);
				} 
			}
		} else {
			throw new NotApplicableException("You cannot put your member here!");
		}
	}

	/**
	 * Apply.
	 *
	 * @param choices the choices
	 * @throws NotApplicableException the not applicable exception
	 */
	public void apply(List<Integer> choices) throws NotApplicableException {
		if (canBePlaced()) {
			int index = 0;
			actionSpace.setFamilyMember(familyMember);
			this.player.removeFamilyMember(familyMember.getColor());
			this.player.getResourceChest().subResource(new Servant(paidServants));
			if (actionSpace.getEffect() != null) {
				actionSpace.getEffect().applyEffect(player);
			}
			for (DevelopmentCard card : industrialArea.getPlayerCards(this.player)) {
				if (isApplicable(card)) {
					if ((card instanceof BuildingCard) && ((BuildingCard) card).hasProductionChoice()) { // Se

						if (card.getPermanentEffect() instanceof ResourcesExchangeEffect)

						try {
							ProductionEffect p = (ProductionEffect) (card.getPermanentEffect());
							p.getResourcesExchangeEffect().applyEffect(choices.get(index), player);

						} catch (Exception e) {
							e.printStackTrace();
						}

					} else {
						card.getPermanentEffect().applyEffect(this.player); // attivo
																			// l'effetto
					}
				} 			
				}
		} else {
			throw new NotApplicableException("You cannot put your member here!");
		}
	}
}
