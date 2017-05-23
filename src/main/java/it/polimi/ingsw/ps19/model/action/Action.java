package it.polimi.ingsw.ps19.model.action;

import it.polimi.ingsw.ps19.FamilyMember;
import it.polimi.ingsw.ps19.Player;

public abstract  class Action {
	
	protected Player player;
	
	protected FamilyMember familyMember;
	
	public Action(FamilyMember familyMember) {
		this.player = familyMember.getPlayer();
		this.familyMember=familyMember;
	}

	public abstract void apply() throws NotApplicableException;
	
	public abstract boolean isApplicable();
	

}