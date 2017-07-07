package it.polimi.ingsw.ps19.model.resource;

/**
 * The Class VictoryPoint.
 *
 * @author matteo
 */
public class VictoryPoint extends Resource {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3592945222426163326L;

	/**
	 * Instantiates a new victory point.
	 *
	 * @param amount the amount
	 */
	public VictoryPoint(int amount) {
		super(ResourceType.VICTORYPOINT, amount);
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.resource.Resource#toString()
	 */
	@Override
	public String toString() {
		if (this.getAmount() == 1)
			return super.toString() + " victory point";
		else
			return super.toString() + " victory points";
	}

	

}
