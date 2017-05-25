package it.polimi.ingsw.ps19.model.resource;

public class Wood extends Resource {
	
	public Wood(ResourceType resourceType, int amount) {
		super(resourceType, amount);
	}

	@Override
	public String toString() {
		if (this.getAmount() == 1)
			return super.toString() + " wood";
		else
			return super.toString() + " woods";
	}



}
