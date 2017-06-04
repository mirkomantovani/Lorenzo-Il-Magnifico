package it.polimi.ingsw.ps19.model.area;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.ps19.FamilyMember;
import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.model.card.DevelopmentCard;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;

public abstract class IndustrialArea{
	
	protected final int SLOT_COST = 1;
	protected final int MALUS = 3;
	protected List<FamilyMember> members;
	protected ResourceChest givenResources;

	
	protected SingleActionSpace singleSlot;
	protected SingleActionSpace multipleSlot;
	
	protected IndustrialArea(){
		
		singleSlot = new SingleActionSpace(SLOT_COST, null);
	}
	
	public abstract List<DevelopmentCard> getPlayerCards(Player player);
	
}
