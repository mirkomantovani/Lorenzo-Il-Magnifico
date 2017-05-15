package it.polimi.ingsw.ps19.model.resource;

/**
 * A factory for creating Resource objects based on a parameter.
 * This class was created using the creational Factory Method design pattern
 * 
 * 
 * @author Mirko
 *
 */
public class ResourceFactory {
	
	private static final int COIN=1;
	private static final int WOOD=2;
	private static final int STONE=3;
	private static final int SERVANT=4;
	private static final int FAITH_POINT=5;
	private static final int VICTORY_POINT=6;
	private static final int MILITARY_POINT=7;
	private static final int COUNCIL_PRIVILEGE=8;
	
	
	/**
	 * @param id
	 * @param amount
	 * @return a Resource subtype or null if the id was 0
	 */
	public static Resource getResource(int id,int amount){
		switch (id) {
		case COIN:
			return new Coin(amount);
		case WOOD:
			return new Wood(amount);
		case STONE:
			return new Stone(amount);
		case SERVANT:
			return new Servant(amount);
		case FAITH_POINT:
			return new FaithPoint(amount);
		case VICTORY_POINT:
			return new VictoryPoint(amount);
		case MILITARY_POINT:
			return new MilitaryPoint(amount);
		/*case COUNCIL_PRIVILEGE:
			return new CouncilPrivilege(amount);*/
		case 0:
			return null;

		
		default:
			throw new ResourceIdException(); //we'll see if we have to introduce an exception

		}
	}

}
