package it.polimi.ingsw.ps19.model.excommunicationtile;

import java.util.ArrayList;
import java.util.Iterator;

import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.model.effect.Effect;
import it.polimi.ingsw.ps19.model.resource.Resource;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;

/**
 * The Class ResourceMalusEffect.
 *
 * @author matteo
 */
public class ResourceMalusEffect extends Effect{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2781912671942166597L;
	
	/** The resources. */
	ArrayList<Resource> resources;
	
	/**
	 * Instantiates a new resource malus effect.
	 *
	 * @param resources the resources
	 */
	public ResourceMalusEffect(ArrayList<Resource> resources){
		
		this.resources=resources;
		
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.effect.Effect#applyEffect(it.polimi.ingsw.ps19.Player)
	 */
	@Override
	public void applyEffect(Player player) {
		
		ResourceChest chest = new ResourceChest(0,0,0,0,0,0,0);
		
		for (Resource r : this.resources){
			chest.addResource(r);
		}
		
		player.getBonuses().setResourceMalus(resources);
		
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder s=new StringBuilder();
		Iterator<Resource> iterator= resources.iterator();
		s.append("Every time you get ");
		
		while(iterator.hasNext()){
			s.append(iterator.next().getResourceType().toString().toLowerCase());
		if(iterator.hasNext())s.append(" or");
		}
	
		s.append(" from an action space or a development card you get ");
		iterator=resources.iterator();
		while(iterator.hasNext()){
			s.append(iterator.next().toString());
		if(iterator.hasNext())s.append(" and ");
		}
		s.append(" less");
		
		return s.toString();
	}
	
	

}
