package it.polimi.ingsw.ps19.model.effect;

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
	
	Resource givenResource;
	Resource toStringResource;  //This is used by the toString method to store a copy of the original givenResource that will be modified
	Resource foreachResource;
	
	public ForEachResourceTypeEffect(Resource givenResource, Resource foreachResource){
		this.givenResource = givenResource;
		this.toStringResource = givenResource;
		this.foreachResource = foreachResource;
	}
	
	
	private Resource calculateResource(){
		int leftFactor = givenResource.getAmount();
		int rightFactor = 0;
		
		//based on the dynamic type of the foreacheResource it will set the right
		//factor to the "int casted" result of the division between the amount of
		//the resource held by the player and the amount of the passed resource
		//I THINK WE SHOULD INTRODUCE AN ENUM RESOURCE TYPE AND ASSIGN THAT TO EACH RESOURCE TYPE
		if(foreachResource instanceof Coin)
			rightFactor = (int)this.getAssociatedPlayer().getResourceChest().getCoins().getAmount() / foreachResource.getAmount();
		if(foreachResource instanceof Wood)
			rightFactor = (int)this.getAssociatedPlayer().getResourceChest().getWoods().getAmount() / foreachResource.getAmount();
		if(foreachResource instanceof Stone)
			rightFactor = (int)this.getAssociatedPlayer().getResourceChest().getStones().getAmount() / foreachResource.getAmount();
		if(foreachResource instanceof Servant)
			rightFactor = (int)this.getAssociatedPlayer().getResourceChest().getServants().getAmount() / foreachResource.getAmount();
		if(foreachResource instanceof FaithPoint)
			rightFactor = (int)this.getAssociatedPlayer().getResourceChest().getFaithPoint().getAmount() / foreachResource.getAmount();
		if(foreachResource instanceof VictoryPoint)
			rightFactor = (int)this.getAssociatedPlayer().getResourceChest().getVictoryPoint().getAmount() / foreachResource.getAmount();
		if(foreachResource instanceof MilitaryPoint)
			rightFactor = (int)this.getAssociatedPlayer().getResourceChest().getMilitaryPoint().getAmount() / foreachResource.getAmount();
		//Non dovrebbe mai succedere che il foreach resource non sia uno di quei tipo

		
		this.givenResource.setAmount(leftFactor * rightFactor);
		return givenResource;
	}
	public void applyEffect() {
		this.getAssociatedPlayer().getResourceChest().addResource(givenResource);
	}
	
	@Override
	public String toString() {
		return toStringResource.toString() + " for each" + foreachResource.toString();
	}
	
	
}
