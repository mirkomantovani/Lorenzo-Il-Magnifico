package it.polimi.ingsw.ps19.model.effect;

import it.polimi.ingsw.ps19.ResourceChest;

public class InstantResourcesEffect extends Effect {
	
	private ResourceChest givenResourceChest;
	private ResourceChest playersResourceChest;
	
	public InstantResourcesEffect(ResourceChest playersResourceChest, ResourceChest givenResourceChest){
		this.givenResourceChest = givenResourceChest;
		this.playersResourceChest = playersResourceChest;
	}
	
	
	public void applyEffect() {
		givenResourceChest.pourInto(playersResourceChest);
	}
}
