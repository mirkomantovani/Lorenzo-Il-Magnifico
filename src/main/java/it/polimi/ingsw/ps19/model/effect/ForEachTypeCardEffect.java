package it.polimi.ingsw.ps19.model.effect;

import java.util.List;

import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.model.card.CardType;
import it.polimi.ingsw.ps19.model.card.DevelopmentCard;
import it.polimi.ingsw.ps19.model.resource.Resource;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;
/**
 * This class implements the "Something"ForEachCard effect, this effect gives a specific
 * resource to the associated player.
 * 
 * @author Jimmy
 *
 */
public class ForEachTypeCardEffect extends Effect {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7216711590979481951L;
	Resource resource;
	CardType typeCard;
	
	public ForEachTypeCardEffect(Resource resource, CardType typeCard){
		this.resource = resource;
		this.typeCard = typeCard;
	}
	
	private int calculateAmount(List<? extends DevelopmentCard> playersArrayList){
		return resource.getAmount()*playersArrayList.size();
	}
	

	public void applyEffect(Player p) {
		this.resource.setAmount(calculateAmount(p.getDeckOfType(typeCard)));   //it sets the resource amount to: previous amount * cards in the player's deck
		ResourceChest rs=new ResourceChest();
		rs.addResource(resource);
		p.addResources(rs);
	}

	@Override
	public String toString() {
		return resource.toString() + " for each " + typeCard.toString().toLowerCase() + " card";
	}
	
}
