package it.polimi.ingsw.ps19.model.action;


import it.polimi.ingsw.ps19.FamilyMember;
import it.polimi.ingsw.ps19.exception.NotApplicableException;
import it.polimi.ingsw.ps19.model.area.SingleActionSpace;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;

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
		System.out.println("sono nell'azione");
	
		if(isApplicable()){
			
			this.marketSpot.getEffect().applyEffect(familyMember.getPlayer());
			
			this.marketSpot.setFamilyMember(familyMember);
			
			familyMember.getPlayer().removeFamilyMember(familyMember.getColor());
			
			if(paidServants > 0){
				familyMember.getPlayer().subResources(new ResourceChest(0,0,0,paidServants,0,0,0));
			}
			
		} else {
			System.out.println("sono nella isApplicable false");
			throw new NotApplicableException("market action non si può fare");
		}
		
	}

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
