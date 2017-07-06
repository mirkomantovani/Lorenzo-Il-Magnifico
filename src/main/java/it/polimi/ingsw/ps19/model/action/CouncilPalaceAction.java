package it.polimi.ingsw.ps19.model.action;

import java.util.ArrayList;

import it.polimi.ingsw.ps19.FamilyMember;
import it.polimi.ingsw.ps19.exception.NotApplicableException;
import it.polimi.ingsw.ps19.model.area.CouncilPalace;
import it.polimi.ingsw.ps19.model.resource.Servant;

/**
 * The Class CouncilPalaceAction.
 * This is the action related to familiar placement in the council palace
 */
/**
 * @author matteo
 *
 */
public class CouncilPalaceAction extends Action {
	
	/** The council palace. */
	private CouncilPalace councilPalace;
	
	/** The paid servants. */
	int paidServants;
	
	/**
	 * Instantiates a new council palace action.
	 *
	 * @param familyMember the family member
	 * @param councilPalace the council palace
	 * @param paidServants the paid servants
	 */
	public CouncilPalaceAction(FamilyMember familyMember, CouncilPalace councilPalace, int paidServants){
		
		super(familyMember);
		this.councilPalace = councilPalace;
        this.paidServants = paidServants;
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.action.Action#apply()
	 */
	@Override
	public void apply() throws NotApplicableException {
		if(isApplicable()){
		councilPalace.getMembers().add(familyMember);
		councilPalace.getEffect().applyEffect(familyMember.getPlayer());
		familyMember.getPlayer().removeFamilyMember(familyMember.getColor());
		familyMember.getPlayer().getResourceChest().subResource(new Servant(paidServants));
		} else throw new NotApplicableException("");
		
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.action.Action#isApplicable()
	 */
	@Override
	public boolean isApplicable() {
		if((familyMember.getActionValue() + paidServants)< this.councilPalace.getActionValueRequired()){
			return false;
		}
		return true;
	}
	
	

}
