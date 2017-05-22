package it.polimi.ingsw.ps19.model.resource;

/**
 * @author matteo
 *
 */
public class VictoryPoint extends Resource {
	
	@Override
	public String toString() {
		if (this.getAmount() == 1)
			return super.toString() + " victory point";
		else
			return super.toString() + " victory points";
	}

	public VictoryPoint(int amount){
		
		super(amount);
	}

}
