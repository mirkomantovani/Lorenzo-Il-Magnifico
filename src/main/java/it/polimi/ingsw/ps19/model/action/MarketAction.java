package it.polimi.ingsw.ps19.model.action;


import it.polimi.ingsw.ps19.exception.NotApplicableException;
import it.polimi.ingsw.ps19.model.FamilyMember;
import it.polimi.ingsw.ps19.model.area.SingleActionSpace;
import it.polimi.ingsw.ps19.model.effect.InstantResourcesEffect;
import it.polimi.ingsw.ps19.model.resource.Coin;
import it.polimi.ingsw.ps19.model.resource.MilitaryPoint;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;
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
	
		if(isApplicable()){
			
			this.marketSpot.getEffect().applyEffect(familyMember.getPlayer());
			this.marketSpot.setFamilyMember(familyMember);
			familyMember.getPlayer().removeFamilyMember(familyMember.getColor());
			familyMember.getPlayer().getResourceChest().subResource(new Servant(paidServants));
			for(int i = 0; i<familyMember.getPlayer().getBonuses().getResourceMalus().size();i++){
				if(familyMember.getPlayer().getBonuses().getResourceMalus().get(i) instanceof Coin
						&& this.marketSpot.getEffect().equals(new InstantResourcesEffect(new
								ResourceChest(5,0,0,0,0,0,0)))){
					familyMember.getPlayer().getResourceChest().subResource(new Coin(1));
				}
				if(familyMember.getPlayer().getBonuses().getResourceMalus().get(i) instanceof Coin
						&& this.marketSpot.getEffect().equals(new InstantResourcesEffect(new
								ResourceChest(0,0,0,5,0,0,0)))){
					familyMember.getPlayer().getResourceChest().subResource(new Servant(1));
				}
				if(familyMember.getPlayer().getBonuses().getResourceMalus().get(i) instanceof Coin
						&& this.marketSpot.getEffect().equals(new InstantResourcesEffect(new
								ResourceChest(2,0,0,0,0,0,3)))){
					familyMember.getPlayer().getResourceChest().subResource(new Coin(1));
					familyMember.getPlayer().getResourceChest().subResource(new MilitaryPoint(1));
				}
			}
		} else {
			
			throw new NotApplicableException("market action non si può fare");
		}
		
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.action.Action#isApplicable()
	 */
	@Override
	public boolean isApplicable() {
		if(!this.canBePlaced() || player.getBonuses().isNoMarketActionActive()) {
			return false;
		}
		return true;
	}
	
	
	/**
	 * Can be placed.
	 *
	 * @return true, if successful
	 */
	private boolean canBePlaced(){
		if(!marketSpot.isOccupable(familyMember) || (familyMember.getActionValue() + paidServants)< this.marketSpot.getActionValueRequired()){
			return false;
		}
		return true;
		
		
	}
	
	
	

}
