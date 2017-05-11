package it.polimi.ingsw.ps19.model.card;

import it.polimi.ingsw.ps19.Effect;
import it.polimi.ingsw.ps19.Period;
import it.polimi.ingsw.ps19.ResourceCost;

public class DevelopmentCard {
	 //attributes should be final, but I can't make them final since I'd have to specify the value now, but I'll have it just at runtime
	private int id;   
	private Period period;
	private ResourceCost cost;  //Territory cards are the only one without cost, they're going to have the attribute set to null
	private String name;
	private Effect immediateEffect,permanentEffect;
	
	public DevelopmentCard(int id,String name,Period period,ResourceCost cost,Effect immediateEffect,Effect permanentEffect){
		this.id=id;
		this.name=name;
		this.period=period;
		this.cost=cost;
		this.immediateEffect=immediateEffect;
		this.permanentEffect=permanentEffect;
	}

	public ResourceCost getCost() {
		return cost;
	}

	public void setCost(ResourceCost cost) {
		this.cost = cost;
	}

	public int getId() {
		return id;
	}

	public Period getPeriod() {
		return period;
	}

	public String getName() {
		return name;
	}

	public Effect getImmediateEffect() {
		return immediateEffect;
	}

	public Effect getPermanentEffect() {
		return permanentEffect;
	}
	
	

}
