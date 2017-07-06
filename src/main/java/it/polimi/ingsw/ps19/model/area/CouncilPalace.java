package it.polimi.ingsw.ps19.model.area;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import it.polimi.ingsw.ps19.FamilyMember;

/**
 * The Class CouncilPalace.
 *
 * @author matteo
 */
public class CouncilPalace extends MultipleActionSpace implements Serializable {
							
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -9220272507699532377L;

	/**
	 * this constructor creates an ActionSpaces that gives a Coin and a privilege if you place a member in it.
	 *
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public CouncilPalace() throws FileNotFoundException, IOException{
		
	
		super(1,BoardInitializer.councilPalaceBonuses());
		members = new ArrayList<FamilyMember>();
	}

	
	/**
	 * Reset palace.
	 */
	private void resetPalace(){	
		this.members.removeAll(members);
		
	}


	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.area.MultipleActionSpace#toString()
	 */
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
