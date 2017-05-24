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
		if (this.getAmount() == 1)
			return super.toString() + " faith point";
		else
			return super.toString() + " faith points";
	}

}
