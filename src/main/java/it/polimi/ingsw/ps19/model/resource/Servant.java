package it.polimi.ingsw.ps19.model.resource;

/**
 * @author matteo
 *
 */
public class Servant extends Resource {
	
	@Override
	public String toString() {
		return super.toString() + " Servants";
	}

	public Servant(int amount){
		super(amount);
	}

}
