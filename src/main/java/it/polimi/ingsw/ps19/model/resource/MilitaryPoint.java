package it.polimi.ingsw.ps19.model.resource;

/**
 * @author matteo
 *
 */
public class MilitaryPoint extends Resource{

	public MilitaryPoint(ResourceType resourceType, int amount) {
		super(resourceType, amount);
	}

	@Override
	public String toString() {
		if (this.getAmount() == 1)
			return super.toString() + " military point";
		else
			return super.toString() + " military points";
	}

	
}
