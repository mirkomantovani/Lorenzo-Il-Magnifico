package it.polimi.ingsw.ps19.model.area;

import java.util.ArrayList;
import java.util.HashSet;

import it.polimi.ingsw.ps19.FamilyMember;
import it.polimi.ingsw.ps19.Player;

/**
 * @author Mirko
 *
 */
public interface Occupable {
	
	public boolean isOccupied();
	
	/**
	 * @param actionValue is the action value of the family member that is being 
	 * @return true if it's occupable by the familyMember passed. N.B (we're not considering here 
	 * the fact that more family members of the same players can't stay in the same tower, it can't be done
	 * at this level)
	 */
	public boolean isOccupable(FamilyMember familyMember);
	
	public ArrayList<FamilyMember> occupiedByMember();
	
	public HashSet<Player> occupiedByPlayer();
	
}