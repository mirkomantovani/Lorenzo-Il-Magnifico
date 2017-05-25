package it.polimi.ingsw.ps19.model.resource;

/**
 * @author matteo
 *
 */
public class FaithPoint extends Resource{
	
	public FaithPoint(ResourceType resourceType,int amount){
		super(resourceType,amount);
		
	}

	@Override
	public String toString() {
		if (this.getAmount() == 1)
			return super.toString() + " faith point";
		else
			return super.toString() + " faith points";
	}

}
