package it.polimi.ingsw.ps19.model.area;

import it.polimi.ingsw.ps19.FamilyMember;
import it.polimi.ingsw.ps19.model.effect.Effect;

/**
 * This class represents the space that can receive one or more family member
 * 
 * @author Jimmy
 *
 */
public abstract class ActionSpace implements Occupable {

	protected int actionValueRequired;


	protected Effect effect;
	
	
	public abstract void setFamilyMember(FamilyMember familyMember);
	
	
	protected ActionSpace(int actionValueRequired, Effect effect) {
		this.actionValueRequired = actionValueRequired;
		this.effect = effect;
	}
	
	public Effect getEffect() {
		return effect;
	}
	
	public int getActionValueRequired() {
		return actionValueRequired;
	}
	
}
