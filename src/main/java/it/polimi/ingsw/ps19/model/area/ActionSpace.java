package it.polimi.ingsw.ps19.model.area;

import java.io.Serializable;

import it.polimi.ingsw.ps19.FamilyMember;
import it.polimi.ingsw.ps19.model.effect.Effect;

/**
 * This class represents the space that can receive one or more family member.
 *
 * @author Jimmy
 */
public abstract class ActionSpace implements Occupable, Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3896782904317576765L;


	/** The action value required. */
	protected int actionValueRequired;
	
	
	/** The effect. */
	protected Effect effect;
	
	
	/**
	 * Sets the family member.
	 *
	 * @param familyMember the new family member
	 */
	public abstract void setFamilyMember(FamilyMember familyMember);
	
	
	/**
	 * Instantiates a new action space.
	 *
	 * @param actionValueRequired the action value required
	 * @param effect the effect
	 */
	protected ActionSpace(int actionValueRequired, Effect effect) {
		this.actionValueRequired = actionValueRequired;
		this.effect = effect;
	}
	
	/**
	 * Gets the effect.
	 *
	 * @return the effect
	 */
	public Effect getEffect() {
		return effect;
	}
	
	/**
	 * Gets the action value required.
	 *
	 * @return the action value required
	 */
	public int getActionValueRequired() {
		return actionValueRequired;
	}
	
	
	
}
