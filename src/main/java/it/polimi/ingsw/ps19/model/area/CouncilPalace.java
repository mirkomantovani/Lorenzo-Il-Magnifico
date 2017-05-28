package it.polimi.ingsw.ps19.model.area;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.ps19.FamilyMember;
import it.polimi.ingsw.ps19.model.effect.CouncilPrivilegeEffect;
import it.polimi.ingsw.ps19.model.effect.InstantResourcesEffect;
import it.polimi.ingsw.ps19.model.effect.MultipleEffect;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;

/**
 * @author matteo
 *
 */
public class CouncilPalace extends SingleActionSpace {
	
	private List<FamilyMember> members; // this attribute saves the order of the Members placed 
								//in the CouncilPalace to set the next turn playing order
	
	/**
	 * this constructor creates an ActionSpaces that gives a Coin and a privilege if you place a member in it
	 * 
	 */
	public CouncilPalace(){
		
		super(1,new MultipleEffect(new InstantResourcesEffect(new ResourceChest(1,0,0,0,0,0,0)), 
				new CouncilPrivilegeEffect(1)));
		this.members = new ArrayList<FamilyMember>();
	
	}

	public List<FamilyMember> getMembers() {
		return members;
	}
	
	private void resetPalace(){	
		this.members.removeAll(members);
		
	}
	
	
}
