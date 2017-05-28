package it.polimi.ingsw.ps19.model.action;


import it.polimi.ingsw.ps19.FamilyMember;
import it.polimi.ingsw.ps19.model.area.SingleActionSpace;

/**
 * @author matteo
 *
 */
public class MarketAction extends Action{
	
	private SingleActionSpace marketSpot;
	
	
	public MarketAction(FamilyMember familyMember, SingleActionSpace marketSpot){
		super(familyMember);
		this.marketSpot = marketSpot;
		
	}

	@Override
	public void apply() throws NotApplicableException {
		if(isApplicable()){
			//this.marketSpot.getEffect().applyEffect(familyMember);
			this.marketSpot.isOccupied();
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
		if(!marketSpot.isOccupable(familyMember)){
			return false;
		}
		return true;
		
		
	}
	
	
	

}
