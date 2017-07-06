package it.polimi.ingsw.ps19.model.action;

import java.util.ArrayList;

import it.polimi.ingsw.ps19.FamilyMember;
import it.polimi.ingsw.ps19.exception.NotApplicableException;
import it.polimi.ingsw.ps19.model.area.CouncilPalace;
import it.polimi.ingsw.ps19.model.resource.Servant;

public class CouncilPalaceAction extends Action {
	
	private CouncilPalace councilPalace;
	int paidServants;
	
	public CouncilPalaceAction(FamilyMember familyMember, CouncilPalace councilPalace, int paidServants){
		
		super(familyMember);
		this.councilPalace = councilPalace;
        this.paidServants = paidServants;
	}

	@Override
	public void apply() throws NotApplicableException {
		if(isApplicable()){
		councilPalace.getMembers().add(familyMember);
		councilPalace.getEffect().applyEffect(familyMember.getPlayer());
		familyMember.getPlayer().removeFamilyMember(familyMember.getColor());
		familyMember.getPlayer().getResourceChest().subResource(new Servant(paidServants));
		} else throw new NotApplicableException("");
		
	}

	@Override
	public boolean isApplicable() {
		if((familyMember.getActionValue() + paidServants)< this.councilPalace.getActionValueRequired()){
			return false;
		}
		return true;
	}
	
	

}
