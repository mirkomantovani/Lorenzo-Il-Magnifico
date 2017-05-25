package it.polimi.ingsw.ps19.model.action;

import it.polimi.ingsw.ps19.FamilyMember;
import it.polimi.ingsw.ps19.model.area.CouncilPalace;

public class CouncilPalaceAction extends Action {
	
	CouncilPalace councilPalace;
	
	public CouncilPalaceAction(FamilyMember familyMember, CouncilPalace councilPalace){
		
		super(familyMember);
		this.councilPalace = councilPalace;

	}

	@Override
	public void apply() throws NotApplicableException {
		if(isApplicable()){
		councilPalace.getMembers().add(familyMember);
		//councilPalace.getEffect().applyEffect(familyMember);
		}
		
	}

	@Override
	public boolean isApplicable() {
		if(!councilPalace.isOccupable(familyMember)){
			return false;
		}
		return true;
	}
	
	

}
