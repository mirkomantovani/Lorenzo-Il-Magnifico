package it.polimi.ingsw.ps19.model.effect;

import java.util.List;

import it.polimi.ingsw.ps19.model.Player;
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
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7216711590979481951L;
	
	/** The resource. */
	Resource resource;
	
	/** The type card. */
	CardType typeCard;
	
	/**
	 * Instantiates a new for each type card effect.
	 *
	 * @param resource the resource
	 * @param typeCard the type card
	 */
	public ForEachTypeCardEffect(Resource resource, CardType typeCard){
		this.resource = resource;
		this.typeCard = typeCard;
	}
	
	/**
	 * Calculate amount.
	 *
	 * @param playersArrayList the players array list
	 * @return the int
	 */
	private int calculateAmount(List<? extends DevelopmentCard> playersArrayList){
		return resource.getAmount()*playersArrayList.size();
	}
	

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.effect.Effect#applyEffect(it.polimi.ingsw.ps19.Player)
	 */
	public void applyEffect(Player p) {
		this.resource.setAmount(calculateAmount(p.getDeckOfType(typeCard)));   //it sets the resource amount to: previous amount * cards in the player's deck
		ResourceChest rs=new ResourceChest();
		rs.addResource(resource);
		p.addResources(rs);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return resource.toString() + " for each " + typeCard.toString().toLowerCase() + " card";
	}
	
}
