package it.polimi.ingsw.ps19.model.resource;

/**
 * @author matteo
 *
 */
public class VictoryPoint extends Resource {
	
	@Override
	public String toString() {
		return super.toString() + " Victory points";
	}

	public VictoryPoint(int amount){
		
		super(amount);
	}

}
