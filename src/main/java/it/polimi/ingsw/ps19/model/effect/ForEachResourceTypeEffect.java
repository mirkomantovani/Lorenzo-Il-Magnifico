package it.polimi.ingsw.ps19.model.effect;

import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.model.resource.Coin;
import it.polimi.ingsw.ps19.model.resource.FaithPoint;
import it.polimi.ingsw.ps19.model.resource.MilitaryPoint;
import it.polimi.ingsw.ps19.model.resource.Resource;
import it.polimi.ingsw.ps19.model.resource.Servant;
import it.polimi.ingsw.ps19.model.resource.Stone;
import it.polimi.ingsw.ps19.model.resource.VictoryPoint;
import it.polimi.ingsw.ps19.model.resource.Wood;

/**
 * This effect gives n resources for each m other resources
 * @author Jimmy
 *
 */
public class ForEachResourceTypeEffect extends Effect{
	
	private Resource givenResource;
	private Resource toStringResource;  //This is used by the toString method to store a copy of the original givenResource that will be modified
	private Resource foreachResource;
	
	public ForEachResourceTypeEffect(Resource givenResource, Resource foreachResource){
		this.givenResource = givenResource;
		this.toStringResource = givenResource;
		this.foreachResource = foreachResource;
	}
	
	
	private Resource calculateResource(Player player){
		int leftFactor = givenResource.getAmount();
		//based on the dynamic type of the foreacheResource it will set the right
		//factor to the "int casted" result of the division between the amount of
		//the resource held by the player and the amount of the passed resource
		int rightFactor = (int) player.getResourceChest().getResourceInChest(foreachResource).getAmount() / foreachResource.getAmount();
		//Non dovrebbe mai succedere che il foreach resource non sia uno di quei tipo

		
		this.givenResource.setAmount(leftFactor * rightFactor);
		return givenResource;
	}
	public void applyEffect(Player player) {
		calculateResource(player);
		player.getResourceChest().addResource(givenResource);
	}
	
	@Override
	public String toString() {
		return toStringResource.toString() + " for each" + foreachResource.toString();
	}
	
	
}
