package it.polimi.ingsw.ps19.model.resource;

/**
 * The Class Servant.
 *
 * @author matteo
 */
public class Servant extends Resource {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2493490750236080560L;

	/**
	 * Instantiates a new servant.
	 *
	 * @param amount the amount
	 */
	public Servant(int amount) {
		super(ResourceType.SERVANT, amount);
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.resource.Resource#toString()
	 */
	@Override
	public String toString() {
		if (this.getAmount() == 1)
			return super.toString() + " servant";
		else
			return super.toString() + " servants";
	}

	

}
