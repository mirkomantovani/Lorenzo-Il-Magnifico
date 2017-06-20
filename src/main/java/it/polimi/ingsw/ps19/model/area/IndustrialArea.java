package it.polimi.ingsw.ps19.model.area;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import it.polimi.ingsw.ps19.FamilyMember;
import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.model.card.DevelopmentCard;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;

/**
 * this class represents both the harvest and the production area, its multipleActionSpace will be initialized 
 * by the heirs classes
 * 
 * @author Jimmy
 *
 */
public abstract class IndustrialArea implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5514546243119979995L;
	protected final int SLOT_COST = 1;
	protected final int MALUS = -3;
	protected ResourceChest givenResources;

	
	protected SingleActionSpace singleSlot;
	protected MultipleActionSpace multipleSlot;
	
	protected IndustrialArea(){
		
		singleSlot = new SingleActionSpace(SLOT_COST, null);
	}
	
	public abstract ArrayList<DevelopmentCard> getPlayerCards(Player player);

	public SingleActionSpace getSingleActionSpace(){
		return singleSlot;
	}
	
	public MultipleActionSpace getMultipleActionSpace(){
		return multipleSlot;
	}
	
	public String toString(){
		StringBuilder string = new StringBuilder();
		Set<String> playerColors = new HashSet<String>();
		
		
		string.append("Single action space value required: " + this.getSingleActionSpace().getActionValueRequired());
		
		if(this.getSingleActionSpace().isOccupied() == false)
			string.append("\nThe single action space is empty");
		else
			string.append("\nThe single action space is occupied by player with color " + this.getSingleActionSpace().getFamilyMember().getPlayer().getColor());
		
		string.append("\nMultiple action space value required: " + this.getMultipleActionSpace().getActionValueRequired() +
						"\nMultiple action space gives a : " + MALUS + " malus to your family member action value");
		
		if(this.getMultipleActionSpace().isOccupied() == true)
			string.append("\nThe multiple action space is empty");
		else{
			string.append("\nThe multiple action space is occupied by players with color: ");
			for(FamilyMember member : this.getMultipleActionSpace().getMembers()){
				playerColors.add(member.getPlayer().getColor());
			}
		}
		
		for(String color : playerColors)
			string.append(color + " ");
		
		return 	string.toString();
		
	}

}
