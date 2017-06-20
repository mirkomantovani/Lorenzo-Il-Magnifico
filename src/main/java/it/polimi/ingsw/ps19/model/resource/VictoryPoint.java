package it.polimi.ingsw.ps19.model.resource;

/**
 * @author matteo
 *
 */
public class VictoryPoint extends Resource {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3592945222426163326L;

	public VictoryPoint(int amount) {
		super(ResourceType.VICTORYPOINT, amount);
	}

	@Override
	public String toString() {
		if (this.getAmount() == 1)
			return super.toString() + " victory point";
		else
			return super.toString() + " victory points";
	}

	

}
