package it.polimi.ingsw.ps19.model.area;

import it.polimi.ingsw.ps19.FamilyMember;
import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.model.effect.InstantResourcesEffect;

/**
 * This class represents an action space Occupable by a FamilyMember
 * 
 * @author Mirko
 *
 */
public class ActionSpace implements Occupable {

	private int actionValueRequired;


	private InstantResourcesEffect instantResourceEffect;
	private FamilyMember familyMember;
	
	public ActionSpace(int actionValueRequired, InstantResourcesEffect instantResourceEffect) {
		this.actionValueRequired = actionValueRequired;
		this.instantResourceEffect = instantResourceEffect;
		this.familyMember=null;
	}
	
	
	@Override
	public boolean isOccupied() {
		return familyMember!=null;
	}
	
	@Override
	public FamilyMember occupiedByMember() {
		return this.familyMember;
	}


	@Override
	public Player occupiedByPlayer() {
		return this.familyMember.getPlayer();
	}


	@Override
	public boolean isOccupable(FamilyMember familyMember) {
		return !isOccupied()&&familyMember.getActionValue()>actionValueRequired;
	}
	
	public int getActionValueRequired() {
		return actionValueRequired;
	}

	

}
