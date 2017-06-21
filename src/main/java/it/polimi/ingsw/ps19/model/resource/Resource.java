package it.polimi.ingsw.ps19.model.resource;

import java.io.Serializable;

/**
 * @author matteo
 *
 */
public abstract class Resource implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2248805350205634404L;
	
	private int amount;
	private ResourceType resourceType;
	
	public void setAmount(int amount){
		this.amount = amount;
	}
	
	public int getAmount(){	
		return amount;	
	}
	
	public boolean isZero(){	
		return(amount==0);	
	}
	
	public Resource(ResourceType resourceType,int amount){	
		this.amount = amount;
		this.resourceType=resourceType;
	}
	
	public void add(int amount){	
		this.amount = this.amount + amount;	
	}
	
	public void sub(int amount){	
		this.amount = this.amount - amount;
		if(this.amount<0)
			this.amount=0;
			
	}

	@Override
	public String toString() {
		return " "+this.amount;
	}
	
	/**
	 * Overloaded the method to simplify its use
	 * @author Mirko
	 * @param resource
	 */
	public void add(Resource resource){
		this.add(resource.amount);
	}
	
	/**
	 * Overloaded the method to simplify its use
	 * @author Mirko
	 * @param resource
	 */
	public void sub(Resource resource){
		this.sub(resource.amount);
	}

	public ResourceType getResourceType() {
		return this.resourceType;
	}

	
	
	

}
