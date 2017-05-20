package it.polimi.ingsw.ps19.model.card;

import it.polimi.ingsw.ps19.Period;
import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.model.effect.Effect;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;

/**
 * This class represents the abstract class of a generic Development Card
 * 
 * @author Mirko 
 *
 */
public abstract class DevelopmentCard extends Card {
	

	//attributes should be final, but I can't make them final since I'd have to specify the value now, but I'll have it just at runtime
	protected int id;   
	protected Period period;
	protected ResourceChest cost;  //Territory cards are the only one without cost, they're going to have the attribute set to null
	
	protected Effect immediateEffect,permanentEffect;
	protected CardType cardType;
	
	

	/**
	 * @param id
	 * @param name
	 * @param period
	 * @param cost
	 * @param immediateEffect
	 * @param permanentEffect
	 */

	public DevelopmentCard(int id,String name,Period period,ResourceChest cost,Effect immediateEffect,Effect permanentEffect){
		super(name);
		this.id=id;
		this.period=period;
		this.cost=cost;
		this.immediateEffect=immediateEffect;
		this.permanentEffect=permanentEffect;
		
		
		this.immediateEffect.setCard(this);  //this creates the association between an effect and its card
		this.permanentEffect.setCard(this);
	}

	/**
	 * @return
	 */
	public ResourceChest getCost() {
		return cost;
	}

	
	/**
	 * @param cost
	 */
	public void setCost(ResourceChest cost) {
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
	public Effect getImmediateEffect() {
		return immediateEffect;
	}

	/**
	 * @return
	 */
	public Effect getPermanentEffect() {
		return permanentEffect;
	}
	
	public CardType getCardType() {
		return cardType;
	}

	
	 @Override
		public String toString() {
			return " [id=" + id + ", period=" + period + ", cost=" + cost.toString() + ", name=" + name
					+ ", \nimmediateEffect=" + immediateEffect.toString() + 
					", \npermanentEffect=" + permanentEffect.toString() + 
					", player=" + player
					+ "]";
		}
	
	

}
