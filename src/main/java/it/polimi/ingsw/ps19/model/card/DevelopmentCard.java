package it.polimi.ingsw.ps19.model.card;

import it.polimi.ingsw.ps19.Period;
import it.polimi.ingsw.ps19.model.effect.Effect;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;

/**
 * This class represents the abstract class of a generic Development Card.
 *
 * @author Mirko
 */
public abstract class DevelopmentCard extends Card {
	

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2067083051151226893L;
	
	/** The id. */
	//attributes should be final, but I can't make them final since I'd have to specify the value now, but I'll have it just at runtime
	protected int id;   
	
	/** The period. */
	protected Period period;
	
	/** The cost. */
	protected ResourceChest cost;  //Territory cards are the only one without cost, they're going to have the attribute set to null
	
	/** The immediate effect. */
	protected Effect immediateEffect;
	
	/** The permanent effect. */
	protected Effect permanentEffect;
	
	/** The card type. */
	protected CardType cardType;
	
	

	/**
	 * Instantiates a new development card.
	 *
	 * @param id the id
	 * @param name the name
	 * @param period the period
	 * @param cost the cost
	 * @param immediateEffect the immediate effect
	 * @param permanentEffect the permanent effect
	 */

	public DevelopmentCard(int id,String name,Period period,ResourceChest cost,Effect immediateEffect,Effect permanentEffect){
		super(name);
		this.id=id;
		this.period=period;
		this.cost=cost;
		this.immediateEffect=immediateEffect;
		this.permanentEffect=permanentEffect;

	}

	/**
	 * Gets the cost.
	 *
	 * @return the cost
	 */
	public ResourceChest getCost() {
		return cost;
	}

	
	/**
	 * Sets the cost.
	 *
	 * @param cost the new cost
	 */
	public void setCost(ResourceChest cost) {
		this.cost = cost;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Gets the period.
	 *
	 * @return the period
	 */
	public Period getPeriod() {
		return period;
	}


	/**
	 * Gets the immediate effect.
	 *
	 * @return the immediate effect
	 */
	public Effect getImmediateEffect() {
		return immediateEffect;
	}

	/**
	 * Gets the permanent effect.
	 *
	 * @return the permanent effect
	 */
	public Effect getPermanentEffect() {
		return permanentEffect;
	}
	
	/**
	 * Gets the card type.
	 *
	 * @return the card type
	 */
	public CardType getCardType() {
		return cardType;
	}

	
	 /* (non-Javadoc)
 	 * @see java.lang.Object#toString()
 	 */
 	@Override
		public String toString() {
		 	StringBuilder string = new StringBuilder();
		 	
		 	string.append("Name: " + name + "\nPeriod: " + period + "\nCost: ");
		 	
		 	if(this.cost!=null)
		 		string.append(cost.toString());
		 	string.append("\nImmediate effect: ");
		 	if(this.immediateEffect!=null)
		 		string.append(immediateEffect.toString());
		 		
		 	return string.toString();
		}
	 
	 /**
 	 * Gets the activation cost.
 	 *
 	 * @return the activation cost
 	 */
 	public abstract int getActivationCost();
	

	 
}
