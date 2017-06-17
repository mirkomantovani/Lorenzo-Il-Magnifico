package it.polimi.ingsw.ps19.model.area;

import java.io.Serializable;

import it.polimi.ingsw.ps19.FamilyMember;
import it.polimi.ingsw.ps19.model.effect.Effect;

/**
 * This class represents the space that can receive one or more family member
 * 
 * @author Jimmy
 *
 */
public abstract class ActionSpace implements Occupable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3896782904317576765L;


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
