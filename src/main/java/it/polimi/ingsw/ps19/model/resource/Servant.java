package it.polimi.ingsw.ps19.model.resource;

/**
 * @author matteo
 *
 */
public class Servant extends Resource {

	public Servant(ResourceType resourceType, int amount) {
		super(resourceType, amount);
	}

	@Override
	public String toString() {
		if (this.getAmount() == 1)
			return super.toString() + " servant";
		else
			return super.toString() + " servants";
	}

	

}
