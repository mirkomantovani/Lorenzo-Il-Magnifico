package it.polimi.ingsw.ps19.model.action;

import it.polimi.ingsw.ps19.FamilyMember;
import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.exception.NotApplicableException;

public abstract class Action {
	
	protected Player player;
	
	protected FamilyMember familyMember;
	
	public Action(FamilyMember familyMember) {
		if(familyMember!=null)
		this.player = familyMember.getPlayer();
		this.familyMember=familyMember;
	}

	public abstract void apply() throws NotApplicableException;
	
	public abstract boolean isApplicable();
	
	public Player getPlayer(){
		return this.player;
	}
	
}
