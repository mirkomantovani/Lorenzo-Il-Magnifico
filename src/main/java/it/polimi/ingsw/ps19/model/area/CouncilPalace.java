package it.polimi.ingsw.ps19.model.area;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import it.polimi.ingsw.ps19.FamilyMember;
import it.polimi.ingsw.ps19.model.effect.CouncilPrivilegeEffect;
import it.polimi.ingsw.ps19.model.effect.InstantResourcesEffect;
import it.polimi.ingsw.ps19.model.effect.MultipleEffect;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;

/**
 * @author matteo
 *
 */
public class CouncilPalace extends MultipleActionSpace {
							
	
	/**
	 * this constructor creates an ActionSpaces that gives a Coin and a privilege if you place a member in it
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * 
	 */
	public CouncilPalace() throws FileNotFoundException, IOException{
		
		super(1,new MultipleEffect(getCoinEffect(), 
				getPrivilegeEffect()));
		members = new ArrayList<FamilyMember>();
	}

	
	private void resetPalace(){	
		this.members.removeAll(members);
		
	}
	
	private static InstantResourcesEffect getCoinEffect() throws FileNotFoundException, IOException{
		
		ArrayList<Integer> bonuses = BoardInitializer.actionSpacesBonuses("src/main/resources/files/fileboardactionspacebonuses.txt");
		
		ResourceChest r = new ResourceChest(bonuses.get(16),0,0,0,0,0,0); // 16 is the row
		// as you can see in the file template, of the coin amount given from the council Palace
	
		return new InstantResourcesEffect(r);
	}
	
	private static CouncilPrivilegeEffect getPrivilegeEffect() throws FileNotFoundException, IOException{
		ArrayList<Integer> bonuses = BoardInitializer.actionSpacesBonuses("src/main/resources/files/fileboardactionspacebonuses.txt");
		
		CouncilPrivilegeEffect effect = new CouncilPrivilegeEffect(bonuses.get(16)); // 16 is the row
		// as you can see in the file template, of the coin amount given from the council Palace
	
		return effect;
	}
	
	
	
}
