package it.polimi.ingsw.ps19.model.card;

import it.polimi.ingsw.ps19.model.effect.CardEffect;

import it.polimi.ingsw.ps19.LeaderCardRequirement;

public class LeaderCard extends Card{
	
	
	private LeaderCardRequirement requirement;
	
	private CardEffect specialEffect; 
	
	public LeaderCard(String name, LeaderCardRequirement requirement, CardEffect specialEffect){
		
	
		super(name);
		this.requirement = requirement;
		this.specialEffect = specialEffect;
		super.setPlayer(null);
	}

	public LeaderCardRequirement getRequirement() {
		return requirement;
	}

	public void setRequirement(LeaderCardRequirement requirement) {
		this.requirement = requirement;
	}

	public CardEffect getSpecialEffect() {
		return specialEffect;
	}

	public void setSpecialEffect(CardEffect specialEffect) {
		this.specialEffect = specialEffect;
	}

	@Override
	public String toString() {
		return "[name=" + super.getName()
		+ ", \nRequirements=" + requirement.toString() + ", \nSpecialEffect=" + specialEffect.toString() +
		", player=" + super.getPlayer() + "]";
	}
	
	

}
