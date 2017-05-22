package it.polimi.ingsw.ps19.model.resource;

/**
 * @author matteo
 *
 */
public class Servant extends Resource {

	@Override
	public String toString() {
		if (this.getAmount() == 1)
			return super.toString() + " servant";
		else
			return super.toString() + " servants";
	}

	public Servant(int amount) {
		super(amount);
	}

}
