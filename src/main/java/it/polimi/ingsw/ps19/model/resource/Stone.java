package it.polimi.ingsw.ps19.model.resource;

/**
 * @author matteo
 *
 */
public class Stone extends Resource{
	
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
