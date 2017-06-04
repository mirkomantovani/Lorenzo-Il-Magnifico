package it.polimi.ingsw.ps19.model.action;

import java.util.ArrayList;

import it.polimi.ingsw.ps19.FamilyMember;
import it.polimi.ingsw.ps19.model.area.CouncilPalace;

public class CouncilPalaceAction extends Action {
	
	CouncilPalace councilPalace;
	ArrayList<FamilyMember> memberOrder;
	
	public CouncilPalaceAction(FamilyMember familyMember, CouncilPalace councilPalace){
		
		super(familyMember);
		this.councilPalace = councilPalace;
        memberOrder.add(familyMember);
	}

	@Override
	public void apply() throws NotApplicableException {
		if(isApplicable()){
		councilPalace.getMembers().add(familyMember);
		councilPalace.getEffect().applyEffect(familyMember.getPlayer());
		} else throw new NotApplicableException();
		
	}

	@Override
	public boolean isApplicable() {
		if(!councilPalace.isOccupable(familyMember)){
			return false;
		}
		return true;
	}
	
	

}
