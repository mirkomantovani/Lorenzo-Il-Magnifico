package it.polimi.ingsw.ps19.model.area;

import java.util.ArrayList;
import java.util.HashSet;

import it.polimi.ingsw.ps19.model.FamilyMember;
import it.polimi.ingsw.ps19.model.Player;

/**
 * The Interface Occupable exposes some methods to every object of the game occupable
 * by a Family Member
 *
 * @author Mirko
 */
public interface Occupable {
	
	/**
	 * Checks if is occupied.
	 *
	 * @return true, if is occupied
	 */
	public boolean isOccupied();
	
	/**
	 * Checks if is occupable.
	 *
	 * @param familyMember the family member
	 * @return true if it's occupable by the familyMember passed. N.B (we're not considering here
	 * the fact that more family members of the same players can't stay in the same tower, it can't be done
	 * at this level)
	 */
	public boolean isOccupable(FamilyMember familyMember);
	
	/**
	 * Occupied by member.
	 *
	 * @return the array list
	 */
	public ArrayList<FamilyMember> occupiedByMember();
	//public FamilyMember occupiedByMember();
	
	/**
	 * Occupied by player.
	 *
	 * @return the hash set
	 */
	public HashSet<Player> occupiedByPlayer();
	//public Player occupiedByPlayer();
	
}