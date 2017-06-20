package it.polimi.ingsw.ps19.model.resource;

/**
 * @author matteo
 *
 */
public class FaithPoint extends Resource{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5060499443757488209L;

	public FaithPoint(int amount){
		super(ResourceType.FAITHPOINT,amount);
		
	}

	@Override
	public String toString() {
		if (this.getAmount() == 1)
			return super.toString() + " faith point";
		else
			return super.toString() + " faith points";
	}

}
