package it.polimi.ingsw.ps19.model.resource;

/**
 * The Class FaithPoint.
 *
 * @author matteo
 */
public class FaithPoint extends Resource{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5060499443757488209L;

	/**
	 * Instantiates a new faith point.
	 *
	 * @param amount the amount
	 */
	public FaithPoint(int amount){
		super(ResourceType.FAITHPOINT,amount);
		
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.resource.Resource#toString()
	 */
	@Override
	public String toString() {
		if (this.getAmount() == 1)
			return super.toString() + " faith point";
		else
			return super.toString() + " faith points";
	}

}
