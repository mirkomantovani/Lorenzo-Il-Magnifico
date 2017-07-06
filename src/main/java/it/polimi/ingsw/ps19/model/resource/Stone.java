package it.polimi.ingsw.ps19.model.resource;

/**
 * The Class Stone.
 *
 * @author matteo
 */
public class Stone extends Resource{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4520009044956001937L;

	/**
	 * Instantiates a new stone.
	 *
	 * @param amount the amount
	 */
	public Stone(int amount) {
		super(ResourceType.STONE, amount);
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.resource.Resource#toString()
	 */
	@Override
	public String toString() {
		if (this.getAmount() == 1)
			return super.toString() + " stone";
		else
			return super.toString() + " stones";
	}

	

}
