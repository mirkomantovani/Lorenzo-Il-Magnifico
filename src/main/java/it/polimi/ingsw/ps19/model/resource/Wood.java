package it.polimi.ingsw.ps19.model.resource;

public class Wood extends Resource {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5688728673691418715L;

	public Wood(int amount) {
		super(ResourceType.WOOD, amount);
	}

	@Override
	public String toString() {
		if (this.getAmount() == 1)
			return super.toString() + " wood";
		else
			return super.toString() + " woods";
	}



}
