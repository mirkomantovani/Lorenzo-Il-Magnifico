package it.polimi.ingsw.ps19;

/**
 * @author matteo
 *
 */
public abstract class Resource {
	
	private int amount;
	
	public void setAmount(int amount){
	
		this.amount = amount;
		
	}


	public int getAmount(){
		
		return amount;
		
	}
	
	public boolean isZero(){
		
		return(amount==0);
		
	}
	
	public Resource(int amount){
		
		this.amount = amount;
		
	}
	
	public void add(int amount){
		
		this.amount = this.amount + amount;
		
	}
	
	public void sub(int amount){
		
		this.amount = this.amount - amount;
		
	}
	
	
	
	
	
	
	
	
}
