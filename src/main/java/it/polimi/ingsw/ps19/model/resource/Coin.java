package it.polimi.ingsw.ps19.model.resource;

/**
 * @author matteo
 *
 */
public class Coin extends Resource {
	
	public Coin(int amount){
		super(amount);
	}

	@Override
	public String toString() {
		return super.toString() + " Coins";
	}

	
}
