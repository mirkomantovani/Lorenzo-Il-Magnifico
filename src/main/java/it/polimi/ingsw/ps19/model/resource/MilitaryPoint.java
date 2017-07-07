package it.polimi.ingsw.ps19.model.resource;

/**
 * The Class MilitaryPoint.
 *
 * @author matteo
 */
public class MilitaryPoint extends Resource{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3459362696450813177L;

	/**
	 * Instantiates a new military point.
	 *
	 * @param amount the amount
	 */
	public MilitaryPoint(int amount) {
		super(ResourceType.MILITARYPOINT, amount);
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.resource.Resource#toString()
	 */
	@Override
	public String toString() {
		if (this.getAmount() == 1)
			return super.toString() + " military point";
		else
			return super.toString() + " military points";
	}

	
}
