package it.polimi.ingsw.ps19.model.resource;

/**
 * @author matteo
 *
 */
public class MilitaryPoint extends Resource{

	@Override
	public String toString() {
		return super.toString() + " Military points";
	}

	public MilitaryPoint(int amount){
		super(amount);
	}
}
