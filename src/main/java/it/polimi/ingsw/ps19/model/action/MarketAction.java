package it.polimi.ingsw.ps19.model.action;


import it.polimi.ingsw.ps19.FamilyMember;
import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.model.area.ActionSpace;

/**
 * @author matteo
 *
 */
public class MarketAction extends Action{
	
	private ActionSpace marketSpot;
	
	public MarketAction(Player player, FamilyMember familyMember, ActionSpace marketSpot){
		super(player,familyMember);
		this.marketSpot = marketSpot;
		
	}

	@Override
	public void apply() throws NotApplicableException {
		if(isApplicable()){
			this.marketSpot.getEffect().applyEffect();
			this.marketSpot.isOccupied();
		}
		
	}

	@Override
	public boolean isApplicable() {
		if(!this.canBePlaced()) {
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
