package it.polimi.ingsw.ps19.model.resource;

/**
 * @author matteo
 *
 */
public class Stone extends Resource{
	
	@Override
	public String toString() {
		return super.toString() + " Stones";
	}

	public Stone(int amount){
		super(amount);
	}

}
