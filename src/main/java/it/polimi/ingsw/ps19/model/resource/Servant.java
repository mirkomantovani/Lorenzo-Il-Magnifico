package it.polimi.ingsw.ps19.model.resource;

/**
 * @author matteo
 *
 */
public class Servant extends Resource {

	public Servant(int amount) {
		super(ResourceType.SERVANT, amount);
	}

	@Override
	public String toString() {
		if (this.getAmount() == 1)
			return super.toString() + " servant";
		else
			return super.toString() + " servants";
	}

	

}
