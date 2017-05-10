package it.polimi.ingsw.ps19;

public class Resource {
	
	private int amount;
	
	private void setAmount(int amount){
	
		this.amount = amount;
		
	}

	private int getAmount(){
		
		return amount;
		
	}
	
	private boolean isZero(){
		
		return(amount==0);
		
	}
	
	public Resource(int amount){
		
		this.amount = amount;
		
	}
	
	
	
	
	
	
}
