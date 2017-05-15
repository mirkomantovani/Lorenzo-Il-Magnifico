package it.polimi.ingsw.ps19.model.resource;

public class Wood extends Resource {
	
	@Override
	public String toString() {
		return super.toString() + " Woods";
	}

	public Wood(int amount){
		super(amount);
	}

}
