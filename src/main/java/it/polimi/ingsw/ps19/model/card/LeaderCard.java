package it.polimi.ingsw.ps19.model.card;

import java.io.Serializable;

import it.polimi.ingsw.ps19.LeaderCardRequirement;
import it.polimi.ingsw.ps19.model.effect.Effect;
import it.polimi.ingsw.ps19.model.effect.leader.Disapplyable;

public class LeaderCard extends Card implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -678840188923192984L;

	private LeaderCardRequirement requirement;
	
	private Disapplyable specialEffect; 
	
	
	public LeaderCard(String name, LeaderCardRequirement requirement, Disapplyable specialEffect){
		
	
		super(name);
		this.requirement = requirement;
		this.specialEffect = specialEffect;
//		super.setPlayer(null);
	}

	public LeaderCardRequirement getRequirement() {
		return requirement;
	}

	public void setRequirement(LeaderCardRequirement requirement) {
		this.requirement = requirement;
	}

	public Disapplyable getSpecialEffect() {
		return specialEffect;
	}

	public void setSpecialEffect(Disapplyable specialEffect) {
		this.specialEffect = specialEffect;
	}

	@Override
	public String toString() {
		return "[name=" + super.getName()
		+ " \nRequirements=" + requirement.toString() + " \nSpecialEffect=" + specialEffect.toString() +
		", player=" + super.getPlayer() + "]";
//		return "ciao";
	}
	
	

}
