package it.polimi.ingsw.ps19.model.area;

import it.polimi.ingsw.ps19.FamilyMember;
import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.model.effect.CouncilPrivilegeEffect;
import it.polimi.ingsw.ps19.model.effect.CardEffect;
import it.polimi.ingsw.ps19.model.effect.InstantResourcesEffect;

/**
 * This class represents an action space Occupable by a FamilyMember
 * 
 * @author Mirko,Matteo
 *
 */
public class ActionSpace implements Occupable {

	private int actionValueRequired;


	private CardEffect effect;
	private FamilyMember familyMember;

	
	public ActionSpace(int actionValueRequired, CardEffect effect) {
		this.actionValueRequired = actionValueRequired;
		this.effect = effect;
		this.familyMember=null;
	}
	

	public CardEffect getEffect() {
		return effect;
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


	public FamilyMember getFamilyMember() {
		return familyMember;
	}


	public void setFamilyMember(FamilyMember familyMember) {
		this.familyMember = familyMember;
	}


	@Override
	public boolean isOccupable(FamilyMember familyMember) {
		return !isOccupied()&&familyMember.getActionValue()>actionValueRequired;
	}
	
	public int getActionValueRequired() {
		return actionValueRequired;
	}


	
	

	

}
