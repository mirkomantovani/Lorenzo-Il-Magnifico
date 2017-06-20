package it.polimi.ingsw.ps19.model.resource;

/**
 * @author matteo
 *
 */
public class Stone extends Resource{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4520009044956001937L;

	public Stone(int amount) {
		super(ResourceType.STONE, amount);
	}

	@Override
	public String toString() {
		if (this.getAmount() == 1)
			return super.toString() + " stone";
		else
			return super.toString() + " stones";
	}

	

}
