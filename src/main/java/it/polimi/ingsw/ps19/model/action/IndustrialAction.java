package it.polimi.ingsw.ps19.model.action;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.ps19.FamilyMember;
import it.polimi.ingsw.ps19.exception.NotApplicableException;
import it.polimi.ingsw.ps19.model.area.ActionSpace;
import it.polimi.ingsw.ps19.model.area.IndustrialArea;
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
		System.out.println("Industrial action: " + this.player.getName());
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
		System.out.println("Industrial Action: SONO NELLA APPLICABLE E STO CONTROLLANDO LA CARTA: !" + card.toString());
		System.out.println("action value fm: " + this.familyMember.getActionValue());
		System.out.println(
				"players bonuses: " + this.getPlayer().getBonuses().getActivationVariation(card.getCardType()));
		System.out.println("card activation cost" + card.getActivationCost());
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
		System.out.println("\nINDUSTRIAL ACTION: Sono nella canBePlaced\n");
		return actionSpace.isOccupable(this.familyMember);
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.action.Action#apply()
	 */
	@Override
	public void apply() throws NotApplicableException {
		if (canBePlaced()) {
			actionSpace.setFamilyMember(familyMember);
			this.player.removeFamilyMember(familyMember.getColor());
			this.player.getResourceChest().subResource(new Servant(paidServants));
			System.out.println("\nIndustrial action: sto applicando la industrialAction\n");
			if (actionSpace.getEffect() != null) {
				System.out.println("\nNon è null quindi è una action space multipla\n");
				actionSpace.getEffect().applyEffect(player);
			}
			for (DevelopmentCard card : industrialArea.getPlayerCards(this.player)) {
				if (isApplicable(card)) {
					System.out.println("\nIndustrial action: LA CARTA DI PUO' ATTIVARE!\n");
					card.getPermanentEffect().applyEffect(this.player);
					System.out.println("\nINDUSTRIAL ACTION: STO ATTIVANDO LA CARTA: " + card.toString() + "\n");
				} else
					System.out.println("CannotActivate card effect");
			}
		} else {
			System.out.println("You cannot put your member here");
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
			System.out.println("\nIndustrial action CON SCELTA: sto applicando la industrialAction con scelta\n");
			if (actionSpace.getEffect() != null) {
				System.out.println("\nNon è null quindi è una action space multipla\n");
				actionSpace.getEffect().applyEffect(player);
			}
			for (DevelopmentCard card : industrialArea.getPlayerCards(this.player)) {
				if (isApplicable(card)) {
					if ((card instanceof BuildingCard) && ((BuildingCard) card).hasProductionChoice()) { // Se

						System.out.println("IndustrialAction: la carta è una building card e ha una scelta");
						System.out.println("choices: " + choices.get(index));
						if (card.getPermanentEffect() instanceof ResourcesExchangeEffect)
							System.out.println("permanenteffect è un resourceexchangeeffect");

						try {
							ProductionEffect p = (ProductionEffect) (card.getPermanentEffect());
							p.getResourcesExchangeEffect().applyEffect(choices.get(index), player);

						} catch (Exception e) {
							e.printStackTrace();
						}

					} else {
						System.out.println("\nIndustrial action: LA CARTA DI PUO' ATTIVARE!\n");
						card.getPermanentEffect().applyEffect(this.player); // attivo
																			// l'effetto
						System.out.println("\nINDUSTRIAL ACTION: STO ATTIVANDO LA CARTA: " + card.toString() + "\n");
					}
				} else
					System.out.println("CannotActivate card effect");
			}
		} else {
			System.out.println("You cannot put your member here");
			throw new NotApplicableException("You cannot put your member here!");
		}
	}
}
