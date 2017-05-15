package it.polimi.ingsw.ps19.model.resource;

/**
 * @author matteo
 *
 */
public class FaithPoint extends Resource{
	
	public FaithPoint(int amount){
		super(amount);
		
	}

	@Override
	public String toString() {
		return super.toString() + " Faith points";
	}

}
