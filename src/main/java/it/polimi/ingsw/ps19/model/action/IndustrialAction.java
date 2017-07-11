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
		// Non mi sembra necessario controllare se il familyMember è diverso da
		// null o meno in quanto alla fine sarà il comando a garantire tale
		// condizione
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
		//Wow.. such a bad programming, but now it's too late to make things look and work better. I feel so sorry about that
		if(actionSpace instanceof MultipleActionSpace){
			return ((MultipleActionSpace) actionSpace).isOccupable(familyMember, paidServants, player.getBonuses().getActivationVariation(industrialArea.getAssociatedCardType()));
		}
		else if(actionSpace instanceof SingleActionSpace){
			return ((SingleActionSpace) actionSpace).isOccupable(familyMember, paidServants, player.getBonuses().getActivationVariation(industrialArea.getAssociatedCardType()));
		}
		return actionSpace.isOccupable(this.familyMember);
		//This method almost doesn't make any sense
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.action.Action#apply()
	 */
	@Override
	public void apply() throws NotApplicableException {
		if (actionSpace.getEffect() != null) {
			actionSpace.getEffect().applyEffect(player);
		}
		
		if (canBePlaced()) {
			//System.out.println("Player: " + player.getFamilyMember(Color.ORANGE).toString());
			actionSpace.setFamilyMember(familyMember);
			this.player.removeFamilyMember(familyMember.getColor());
			this.player.getResourceChest().subResource(new Servant(paidServants));
			for (DevelopmentCard card : industrialArea.getPlayerCards(this.player)) {
			}
			for (DevelopmentCard card : industrialArea.getPlayerCards(this.player)) {
				if (isApplicable(card)) {
					card.getPermanentEffect().applyEffect(this.player);
				} else
					System.out.println("CannotActivate card effect");
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
							System.out.println("permanenteffect è un resourceexchangeeffect");

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
				} else
					System.out.println("CannotActivate card effect");
			}
		} else {
			throw new NotApplicableException("You cannot put your member here!");
		}
	}
}
