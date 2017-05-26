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
	

	public static Resource getResource(ResourceType resourceType,int amount){
		switch (resourceType) {
		case COIN:
			return new Coin(amount);
		case WOOD:
			return new Wood(amount);
		case STONE:
			return new Stone(amount);
		case SERVANT:
			return new Servant(amount);
		case FAITHPOINT:
			return new FaithPoint(amount);
		case VICTORYPOINT:
			return new VictoryPoint(amount);
		case MILITARYPOINT:
			return new MilitaryPoint(amount);
		
		default:
			throw new ResourceTypeException(); //we'll see if we have to introduce an exception

		}
	}

}
