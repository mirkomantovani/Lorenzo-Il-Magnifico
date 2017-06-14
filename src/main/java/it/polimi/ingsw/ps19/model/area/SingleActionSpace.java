package it.polimi.ingsw.ps19.model.area;

import java.util.ArrayList;
import java.util.HashSet;

import it.polimi.ingsw.ps19.FamilyMember;
import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.model.effect.Effect;

public class SingleActionSpace extends ActionSpace{
	
	private FamilyMember familyMember;
	
	public SingleActionSpace(int actionValueRequired, Effect effect) {
		super(actionValueRequired, effect);
	}

	@Override
	public boolean isOccupied() {
		return familyMember!=null;
	}
	
	
	//This method returns an arraylist with a single family member, in order to access the
	//attribute use the getFamilyMember method
	@Override
	public ArrayList<FamilyMember> occupiedByMember() {
		ArrayList<FamilyMember> forInterface = new ArrayList<FamilyMember>();
		forInterface.add(familyMember);
		return forInterface;
	}


	@Override
	public HashSet<Player> occupiedByPlayer() {
		HashSet<Player> forInterface = new HashSet<Player>();
		forInterface.add(familyMember.getPlayer());
		return forInterface;
	}


	public FamilyMember getFamilyMember() {
		return familyMember;
	}

	public void setFamilyMember(FamilyMember familyMember) {
		if(!isOccupied())
			this.familyMember = familyMember;
	}


	@Override
	public boolean isOccupable(FamilyMember familyMember) {
		return !isOccupied()&&familyMember.getActionValue()>actionValueRequired;
	}
	
}