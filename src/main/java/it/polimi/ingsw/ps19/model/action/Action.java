package it.polimi.ingsw.ps19.model.action;

import it.polimi.ingsw.ps19.exception.NotApplicableException;
import it.polimi.ingsw.ps19.model.FamilyMember;
import it.polimi.ingsw.ps19.model.Player;

/**
 * The Class Action.
 * This is the abstract class to generalize an action
 */
/**
 * @author matteo
 *
 */
public abstract class Action {
	
	/** The player. */
	protected Player player;
	
	/** The family member. */
	protected FamilyMember familyMember;
	
	/**
	 * Instantiates a new action.
	 *
	 * @param familyMember the family member
	 */
	public Action(FamilyMember familyMember) {
		if(familyMember!=null)
		this.player = familyMember.getPlayer();
		this.familyMember=familyMember;
	}

	/**
	 * Apply.
	 *
	 * @throws NotApplicableException the not applicable exception
	 */
	public abstract void apply() throws NotApplicableException;
	
	/**
	 * Checks if is applicable.
	 *
	 * @return true, if is applicable
	 */
	public abstract boolean isApplicable();
	
	/**
	 * Gets the player.
	 *
	 * @return the player
	 */
	public Player getPlayer(){
		return this.player;
	}
	
}
