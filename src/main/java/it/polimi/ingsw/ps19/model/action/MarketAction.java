package it.polimi.ingsw.ps19.model.action;


import it.polimi.ingsw.ps19.FamilyMember;
import it.polimi.ingsw.ps19.exception.NotApplicableException;
import it.polimi.ingsw.ps19.model.area.SingleActionSpace;

/**
 * @author matteo
 *
 */
public class MarketAction extends Action{
	
	private SingleActionSpace marketSpot;
	private int paidServants;
	
	
	public MarketAction(FamilyMember familyMember, SingleActionSpace marketSpot, int paidServants){
		super(familyMember);
		this.marketSpot = marketSpot;
		this.paidServants = paidServants;
		
	}

	@Override
	public void apply() throws NotApplicableException {
		if(isApplicable()){
			this.marketSpot.getEffect().applyEffect(familyMember.getPlayer());
			this.marketSpot.isOccupied();
			familyMember.getPlayer().removeFamilyMember(familyMember.getColor());
		} else throw new NotApplicableException();
		
	}

	@Override
	public boolean isApplicable() {
		if(!this.canBePlaced() || player.getBonuses().isNoMarketActionActive()) {
			return false;
		}
		return true;
	}
	
	
	private boolean canBePlaced(){
		if(!marketSpot.isOccupable(familyMember) || (familyMember.getActionValue() + paidServants)< this.marketSpot.getActionValueRequired()){
			return false;
		}
		return true;
		
		
	}
	
	
	

}
