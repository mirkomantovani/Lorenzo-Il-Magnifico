package it.polimi.ingsw.ps19.model.resource;

/**
 * @author matteo
 *
 */
public class MilitaryPoint extends Resource{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3459362696450813177L;

	public MilitaryPoint(int amount) {
		super(ResourceType.MILITARYPOINT, amount);
	}

	@Override
	public String toString() {
		if (this.getAmount() == 1)
			return super.toString() + " military point";
		else
			return super.toString() + " military points";
	}

	
}
