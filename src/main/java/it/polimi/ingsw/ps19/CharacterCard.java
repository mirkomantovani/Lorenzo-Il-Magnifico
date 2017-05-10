package it.polimi.ingsw.ps19;

public class CharacterCard extends DevelopmentCard {
	
	//we have to pass some null values to immediate or permanent effect of some of these cards because some of them don't have both

	public CharacterCard(int id, String name, Period period, ResourceCost cost, Effect immediateEffect,
			Effect permanentEffect) {
		super(id, name, period, cost, immediateEffect, permanentEffect);
	}

	

}
