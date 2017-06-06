package it.polimi.ingsw.ps19.model.area;

import java.util.List;

import it.polimi.ingsw.ps19.FamilyMember;
import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.model.card.DevelopmentCard;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;

/**
 * this class represents both the harvest and the production area
 * 
 * @author Jimmy
 *
 */
public abstract class IndustrialArea{
	
	protected final int SLOT_COST = 1;
	protected final int MALUS = 3;
	protected List<FamilyMember> members;
	protected ResourceChest givenResources;

	
	protected SingleActionSpace singleSlot;
	protected MultipleActionSpace multipleSlot;
	
	protected IndustrialArea(){
		
		singleSlot = new SingleActionSpace(SLOT_COST, null);
	}
	
	public abstract List<DevelopmentCard> getPlayerCards(Player player);
	
}
