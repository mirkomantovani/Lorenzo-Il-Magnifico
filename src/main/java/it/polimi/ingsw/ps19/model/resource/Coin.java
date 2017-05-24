package it.polimi.ingsw.ps19.model.resource;

/**
 * @author matteo
 *
 */
public class Coin extends Resource {
	
	public Coin(int amount){
		super(amount);
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.resource.Resource#toString()
	 */
	@Override
	public String toString() {
		if (this.getAmount() == 1)
			return super.toString() + " coin";
		else
			return super.toString() + " coins";
	}

	
}
