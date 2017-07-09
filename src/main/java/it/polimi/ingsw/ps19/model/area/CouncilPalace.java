package it.polimi.ingsw.ps19.model.area;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import it.polimi.ingsw.ps19.FamilyMember;

/**
 * The Class CouncilPalace.
 * This class represent the council palace, a multiple action space that gave specific things
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
		builder.append("°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°The Council Palace°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°\n");
		builder.append("Remember that this area affects the play order\n\n");
		builder.append("Action value required: " + actionValueRequired);
		builder.append("\n\nEffect: " + effect.toString() + "\n\n");
		
		if(!members.isEmpty()){
			builder.append(super.toString());
		}
		
		builder.append("\n°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°\n\n");
		
		
		
		return builder.toString();
	}
	
	

	
	
	
}
