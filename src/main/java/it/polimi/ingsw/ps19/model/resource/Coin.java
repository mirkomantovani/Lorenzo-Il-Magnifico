package it.polimi.ingsw.ps19.model.resource;

/**
 * The Class Coin.
 *
 * @author matteo
 */
public class Coin extends Resource {
	

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4990887197340403545L;

	/**
	 * Instantiates a new coin.
	 *
	 * @param amount the amount
	 */
	public Coin(int amount) {
		super(ResourceType.COIN, amount);
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
