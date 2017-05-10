package it.polimi.ingsw.ps19;

public class FamilyMember {
	
	private Color color; 
	
	private int value; 
	
	private void setValue(int value){
		
		this.value = value; 
		
	}
	
	private int getValue(){
		
		return(value);
	}
	
	public FamilyMember(int value, Color color){
		
		this.value = value;
		
		this.color = color; 
	}
	

}
