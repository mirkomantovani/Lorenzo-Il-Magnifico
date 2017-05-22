package it.polimi.ingsw.ps19.model.effect;

import java.util.List;

import it.polimi.ingsw.ps19.model.card.CardType;
import it.polimi.ingsw.ps19.model.card.DevelopmentCard;
import it.polimi.ingsw.ps19.model.resource.Resource;
/**
 * This class implements the "Something"ForEachCard effect, this effect gives a specific
 * resource to the associated player.
 * 
 * @author Jimmy
 *
 */
public class ForEachTypeCardEffect extends Effect {
	
	Resource resource;
	CardType typeCard;
	
	public ForEachTypeCardEffect(Resource resource, CardType typeCard){
		this.resource = resource;
		this.typeCard = typeCard;
	}
	
	private int calculateAmount(List<? extends DevelopmentCard> playersArrayList){
		return resource.getAmount()*playersArrayList.size();
	}
	

	public void applyEffect() {
		this.resource.setAmount(calculateAmount(this.getAssociatedPlayer().getRightArrayList(typeCard)));   //it sets the resource amount to: previous amount * cards in the player's deck
		this.getAssociatedPlayer().getResourceChest().addResource(this.resource);
	}

	@Override
	public String toString() {
		return resource.toString() + " for each " + typeCard.toString().toLowerCase() + " card";
	}
	
}
