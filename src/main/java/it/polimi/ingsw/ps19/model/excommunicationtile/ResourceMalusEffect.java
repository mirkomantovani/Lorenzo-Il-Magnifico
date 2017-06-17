package it.polimi.ingsw.ps19.model.excommunicationtile;

import java.util.ArrayList;

import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.model.effect.Effect;
import it.polimi.ingsw.ps19.model.resource.Resource;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;

/**
 * @author matteo
 *
 */
public class ResourceMalusEffect extends Effect{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2781912671942166597L;
	ArrayList<Resource> resources;
	
	public ResourceMalusEffect(ArrayList<Resource> resources){
		
		resources=new ArrayList<>();
		
		for(Resource r: resources){
			this.resources.add(r);
		}
	}

	@Override
	public void applyEffect(Player player) {
		
		ResourceChest chest = new ResourceChest(0,0,0,0,0,0,0);
		
		for (Resource r : this.resources){
			chest.addResource(r);
		}
		
		player.getBonuses().setResourceMalus(resources);
		
	}
	
	

}
