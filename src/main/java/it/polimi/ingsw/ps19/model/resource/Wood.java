package it.polimi.ingsw.ps19.model.resource;

/**
 * The Class Wood.
 */
public class Wood extends Resource {
	
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5688728673691418715L;

	/**
	 * Instantiates a new wood.
	 *
	 * @param amount the amount
	 */
	public Wood(int amount) {
		super(ResourceType.WOOD, amount);
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.resource.Resource#toString()
	 */
	@Override
	public String toString() {
		if (this.getAmount() == 1)
			return super.toString() + " wood";
		else
			return super.toString() + " woods";
	}



}
