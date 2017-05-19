package it.polimi.ingsw.ps19.model.card;

import it.polimi.ingsw.ps19.model.effect.Effect;
import it.polimi.ingsw.ps19.LeaderCardRequirment;

public class LeaderCard extends Card{
	
	private String name;
	
	private LeaderCardRequirment requirment;
	
	private Effect specialEffect; 
	
	public LeaderCard(String name){
		super(name);
	}

}
