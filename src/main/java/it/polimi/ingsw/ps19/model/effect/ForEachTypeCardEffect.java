package it.polimi.ingsw.ps19.model.effect;

import it.polimi.ingsw.ps19.Resource;
/**
 * This class implements the "Something"ForEachCard effect, this effect gives a specific
 * resource to the associated player.
 * 
 * @author Jimmy
 *
 */
public class ForEachTypeCardEffect extends Effect {
	
	Resource resource;
	int typeCard;
	
	public ForEachTypeCardEffect(Resource resource, int typeCard){
		this.resource = resource;
		this.typeCard = typeCard;
	}
	
	public void applyEffect() {
		
	}

}
