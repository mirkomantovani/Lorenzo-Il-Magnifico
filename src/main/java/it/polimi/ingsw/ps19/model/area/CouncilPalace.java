package it.polimi.ingsw.ps19.model.area;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import it.polimi.ingsw.ps19.FamilyMember;

/**
 * @author matteo
 *
 */
public class CouncilPalace extends MultipleActionSpace implements Serializable {
							
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9220272507699532377L;

	/**
	 * this constructor creates an ActionSpaces that gives a Coin and a privilege if you place a member in it
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * 
	 */
	public CouncilPalace() throws FileNotFoundException, IOException{
		
	
		super(1,BoardInitializer.councilPalaceBonuses());
		members = new ArrayList<FamilyMember>();
	}

	
	private void resetPalace(){	
		this.members.removeAll(members);
		
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("---- The CouncilPalace ---- \n To place a pawn into this area your familiar should have a"
				+ " value of, ");
		builder.append(actionValueRequired);
		builder.append(" and your position into this area establish the player order of the next turn. "
				+ "\n Actually if you visit the CouncilPalace you ");
		builder.append(effect.toString());
		
		if(!members.isEmpty()){
			builder.append(super.toString());
		}
		return builder.toString();
	}
	
	

	
	
	
}
