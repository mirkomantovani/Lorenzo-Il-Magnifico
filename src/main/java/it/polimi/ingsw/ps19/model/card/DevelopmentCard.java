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
public abstract class DevelopmentCard {
	

	//attributes should be final, but I can't make them final since I'd have to specify the value now, but I'll have it just at runtime
	private int id;   
	private Period period;
	private ResourceChest cost;  //Territory cards are the only one without cost, they're going to have the attribute set to null
	private String name;
	private Effect immediateEffect,permanentEffect;
	private Player player;
	
	/**
	 * @param id
	 * @param name
	 * @param period
	 * @param cost
	 * @param immediateEffect
	 * @param permanentEffect
	 */

	public DevelopmentCard(int id,String name,Period period,ResourceChest cost,Effect immediateEffect,Effect permanentEffect){
		this.id=id;
		this.name=name;
		this.period=period;
		this.cost=cost;
		this.immediateEffect=immediateEffect;
		this.permanentEffect=permanentEffect;
		
		if(immediateEffect!=null)
			this.immediateEffect.setCard(this);  //this creates the association between an effect and its card
		if(this.permanentEffect!=null)
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

	public Player getPlayer() {
		return player;
	}

	/**
	 * @param player
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	 @Override
		public String toString() {			
		 if(immediateEffect == null && permanentEffect != null)
			 return " [id=" + id + ", period=" + period + ", cost=" + cost.toString() + ", name=" + name
						+ ", \nimmediateEffect=     /     " + ", \npermanentEffect=" + permanentEffect.toString() + "\nplayer=" + player
						+ "]";
		 else if(immediateEffect !=null && permanentEffect == null)
			 return " [id=" + id + ", period=" + period + ", cost=" + cost.toString() + ", name=" + name
						+ ", \nimmediateEffect=" + immediateEffect.toString() + ", \npermanentEffect=     /     "  + "\nplayer=" + player
						+ "]";
		 else if(immediateEffect == null && permanentEffect == null)
			 return " [id=" + id + ", period=" + period + ", cost=" + cost.toString() + ", name=" + name
						+ ", \nimmediateEffect=     /     " + " \npermanentEffect=      /     " + "\nplayer=" + player
						+ "]";
		 return " [id=" + id + ", period=" + period + ", cost=" + cost.toString() + ", name=" + name
					+ ", \nimmediateEffect=" + immediateEffect.toString() + ", \npermanentEffect=" + permanentEffect.toString() + "\nplayer=" + player
					+ "]";
		}
	
	

}
