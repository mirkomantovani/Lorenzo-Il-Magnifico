package it.polimi.ingsw.ps19.model.resource;

import java.io.Serializable;

/**
 * The Class Resource.
 *
 * @author matteo
 */
public abstract class Resource implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2248805350205634404L;
	
	/** The amount. */
	private int amount;
	
	/** The resource type. */
	private ResourceType resourceType;
	
	/**
	 * Sets the amount.
	 *
	 * @param amount the new amount
	 */
	public void setAmount(int amount){
		this.amount = amount;
	}
	
	/**
	 * Gets the amount.
	 *
	 * @return the amount
	 */
	public int getAmount(){	
		return amount;	
	}
	
	/**
	 * Checks if is zero.
	 *
	 * @return true, if is zero
	 */
	public boolean isZero(){	
		return(amount==0);	
	}
	
	/**
	 * Instantiates a new resource.
	 *
	 * @param resourceType the resource type
	 * @param amount the amount
	 */
	public Resource(ResourceType resourceType,int amount){	
		this.amount = amount;
		this.resourceType=resourceType;
	}
	
	/**
	 * Adds the.
	 *
	 * @param amount the amount
	 */
	public void add(int amount){	
		this.amount = this.amount + amount;	
	}
	
	/**
	 * Sub.
	 *
	 * @param amount the amount
	 */
	public void sub(int amount){	
		this.amount = this.amount - amount;
		if(this.amount<0)
			this.amount=0;
			
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return " "+this.amount;
	}
	
	/**
	 * Overloaded the method to simplify its use.
	 *
	 * @author Mirko
	 * @param resource the resource
	 */
	public void add(Resource resource){
		this.add(resource.amount);
	}
	
	/**
	 * Overloaded the method to simplify its use.
	 *
	 * @author Mirko
	 * @param resource the resource
	 */
	public void sub(Resource resource){
		this.sub(resource.amount);
	}

	/**
	 * Gets the resource type.
	 *
	 * @return the resource type
	 */
	public ResourceType getResourceType() {
		return this.resourceType;
	}

	
	
	

}
