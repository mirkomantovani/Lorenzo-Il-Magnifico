package it.polimi.ingsw.ps19.model.area;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.polimi.ingsw.ps19.FamilyMember;
import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.model.card.DevelopmentCard;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;

/**
 * this class represents both the harvest and the production area, its multipleActionSpace will be initialized 
 * by the heirs classes.
 *
 * @author Jimmy
 */
public abstract class IndustrialArea implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5514546243119979995L;
	
	/** The slot cost. */
	protected final int SLOT_COST = 1;
	
	/** The malus. */
	protected final int MALUS = -3;
	
	/** The given resources. */
	protected ResourceChest givenResources;

	
	/** The single slot. */
	protected SingleActionSpace singleSlot;
	
	/** The multiple slot. */
	protected MultipleActionSpace multipleSlot;
	
	/**
	 * Instantiates a new industrial area.
	 */
	protected IndustrialArea(){
		
		singleSlot = new SingleActionSpace(SLOT_COST, null);
	}
	
	/**
	 * Gets the player cards.
	 *
	 * @param player the player
	 * @return the player cards
	 */
	public abstract List<DevelopmentCard> getPlayerCards(Player player);

	/**
	 * Gets the single action space.
	 *
	 * @return the single action space
	 */
	public SingleActionSpace getSingleActionSpace(){
		return singleSlot;
	}
	
	/**
	 * Gets the multiple action space.
	 *
	 * @return the multiple action space
	 */
	public MultipleActionSpace getMultipleActionSpace(){
		return multipleSlot;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		StringBuilder string = new StringBuilder();
		Set<String> playerColors = new HashSet<String>();
		
		
		string.append("\nSingle action space value required: " + this.getSingleActionSpace().getActionValueRequired());
		
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
		
		string.append("\n\n");
		
		return 	string.toString();
		
	}

}
