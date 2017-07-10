package it.polimi.ingsw.ps19.model.card;

import java.io.Serializable;

import it.polimi.ingsw.ps19.model.LeaderCardRequirement;
import it.polimi.ingsw.ps19.model.effect.leader.Disapplyable;

/**
 * The Class LeaderCard.
 */
public class LeaderCard extends Card implements Serializable{
	
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -678840188923192984L;

	/** The requirement. */
	private LeaderCardRequirement requirement;
	
	/** The special effect. */
	private Disapplyable specialEffect; 
	
	private boolean activationState;
	

	/**
	 * Instantiates a new leader card.
	 *
	 * @param name the name
	 * @param requirement the requirement
	 * @param specialEffect the special effect
	 */
	public LeaderCard(String name, LeaderCardRequirement requirement, Disapplyable specialEffect){
		
	
		super(name);
		this.requirement = requirement;
		this.specialEffect = specialEffect;
//		super.setPlayer(null);
	}

	/**
	 * Gets the requirement.
	 *
	 * @return the requirement
	 */
	public LeaderCardRequirement getRequirement() {
		return requirement;
	}

	/**
	 * Sets the requirement.
	 *
	 * @param requirement the new requirement
	 */
	public void setRequirement(LeaderCardRequirement requirement) {
		this.requirement = requirement;
	}

	/**
	 * Gets the special effect.
	 *
	 * @return the special effect
	 */
	public Disapplyable getSpecialEffect() {
		return specialEffect;
	}

	/**
	 * Sets the special effect.
	 *
	 * @param specialEffect the new special effect
	 */
	public void setSpecialEffect(Disapplyable specialEffect) {
		this.specialEffect = specialEffect;
	}
	
	public boolean isActivationState() {
		return activationState;
	}

	public void setActivationState(boolean activationState) {
		this.activationState = activationState;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[name=" + super.getName()
		+ " \nRequirements=" + requirement.toString() + " \nSpecialEffect=" + specialEffect.toString() +
		", player=" + super.getPlayer() + "]";
//		return "ciao";
	}
	
	

}
