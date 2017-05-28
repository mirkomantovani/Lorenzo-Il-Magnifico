package it.polimi.ingsw.ps19.model.area;

import java.util.List;

import it.polimi.ingsw.ps19.FamilyMember;

public abstract class IndustrialArea{
	
	protected final int SLOT_COST = 1;
	protected final int MALUS = 3;
	protected List<FamilyMember> members;
	
	protected SingleActionSpace singleSlot;
	protected SingleActionSpace multipleSlot;
	
	public IndustrialArea(){
		
		singleSlot = new SingleActionSpace(SLOT_COST, null);
	}
	
}
