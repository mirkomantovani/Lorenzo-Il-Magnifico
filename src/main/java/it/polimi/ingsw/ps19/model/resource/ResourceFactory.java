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
			return new Coin(resourceType,amount);
		case WOOD:
			return new Wood(resourceType,amount);
		case STONE:
			return new Stone(resourceType,amount);
		case SERVANT:
			return new Servant(resourceType,amount);
		case FAITHPOINT:
			return new FaithPoint(resourceType,amount);
		case VICTORYPOINT:
			return new VictoryPoint(resourceType,amount);
		case MILITARYPOINT:
			return new MilitaryPoint(resourceType,amount);
		
		default:
			throw new ResourceTypeException(); //we'll see if we have to introduce an exception

		}
	}

}
