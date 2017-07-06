package it.polimi.ingsw.ps19.model.action;


import it.polimi.ingsw.ps19.FamilyMember;
import it.polimi.ingsw.ps19.exception.NotApplicableException;
import it.polimi.ingsw.ps19.model.area.SingleActionSpace;
import it.polimi.ingsw.ps19.model.resource.Servant;

/**
 * The Class MarketAction.
 * This class represent the placement in a market slot action
 *
 * @author matteo
 */
public class MarketAction extends Action{
	
	/** The market spot. */
	private SingleActionSpace marketSpot;
	
	/** The paid servants. */
	private int paidServants;
	
	
	/**
	 * Instantiates a new market action.
	 *
	 * @param familyMember the family member
	 * @param marketSpot the market spot
	 * @param paidServants the paid servants
	 */
	public MarketAction(FamilyMember familyMember, SingleActionSpace marketSpot, int paidServants){
		super(familyMember);
		this.marketSpot = marketSpot;
		this.paidServants = paidServants;
		
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.action.Action#apply()
	 */
	@Override
	public void apply() throws NotApplicableException {
		System.out.println("sono nell'azione market");
	
		if(isApplicable()){
			System.out.println("sono entrato in isApplicable");
			this.marketSpot.getEffect().applyEffect(familyMember.getPlayer());
			System.out.println("ho applicato l'effetto");
			this.marketSpot.setFamilyMember(familyMember);
			System.out.println("setto lo slot a occupato");
			familyMember.getPlayer().removeFamilyMember(familyMember.getColor());
			System.out.println("tolgo il family usato");
			familyMember.getPlayer().getResourceChest().subResource(new Servant(paidServants));
		} else {
			System.out.println("sono nella isApplicable false");
			throw new NotApplicableException("market action non si può fare");
		}
		
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.action.Action#isApplicable()
	 */
	@Override
	public boolean isApplicable() {
		System.out.println("sono dentro a isApplicable");
		if(!this.canBePlaced() || player.getBonuses().isNoMarketActionActive()) {
			System.out.println("azione non applicabile");
			return false;
		}
		System.out.println("isApplicable market action");
		return true;
	}
	
	
	/**
	 * Can be placed.
	 *
	 * @return true, if successful
	 */
	private boolean canBePlaced(){
		System.out.println("sono dentro a can be placed");
		if(!marketSpot.isOccupable(familyMember) || (familyMember.getActionValue() + paidServants)< this.marketSpot.getActionValueRequired()){
			System.out.println("family memeber can't be placed");
			return false;
		}
		System.out.println("familymember can be placed");
		return true;
		
		
	}
	
	
	

}
