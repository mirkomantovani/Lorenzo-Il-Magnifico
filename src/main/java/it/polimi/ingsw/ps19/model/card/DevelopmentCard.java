package it.polimi.ingsw.ps19.model.card;

import it.polimi.ingsw.ps19.Effect;
import it.polimi.ingsw.ps19.Period;
import it.polimi.ingsw.ps19.ResourceCost;

/**
 * @author Mirko
 *
 */
public class DevelopmentCard {
	 //attributes should be final, but I can't make them final since I'd have to specify the value now, but I'll have it just at runtime
	private int id;   
	private Period period;
	private ResourceCost cost;  //Territory cards are the only one without cost, they're going to have the attribute set to null
	private String name;
	private Effect immediateEffect,permanentEffect;
	
	/**
	 * @param id
	 * @param name
	 * @param period
	 * @param cost
	 * @param immediateEffect
	 * @param permanentEffect
	 */
	public DevelopmentCard(int id,String name,Period period,ResourceCost cost,Effect immediateEffect,Effect permanentEffect){
		this.id=id;
		this.name=name;
		this.period=period;
		this.cost=cost;
		this.immediateEffect=immediateEffect;
		this.permanentEffect=permanentEffect;
	}

	/**
	 * @return
	 */
	public ResourceCost getCost() {
		return cost;
	}

	/**
	 * @param cost
	 */
	/**
	 * @param cost
	 */
	public void setCost(ResourceCost cost) {
		this.cost = cost;
	}

	/**
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return
	 */
	public Period getPeriod() {
		return period;
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return
	 */
	public Effect getImmediateEffect() {
		return immediateEffect;
	}

	/**
	 * @return
	 */
	public Effect getPermanentEffect() {
		return permanentEffect;
	}
	
	

}
