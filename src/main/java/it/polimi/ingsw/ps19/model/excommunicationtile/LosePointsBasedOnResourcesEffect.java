package it.polimi.ingsw.ps19.model.excommunicationtile;

import it.polimi.ingsw.ps19.model.Player;
import it.polimi.ingsw.ps19.model.effect.Effect;
import it.polimi.ingsw.ps19.model.resource.Resource;
import it.polimi.ingsw.ps19.model.resource.VictoryPoint;

/**
 * You lose "victoryPoints" points for every "resource"
 *  you have at the end of the game.
 *
 * @author Mirko
 */
public class LosePointsBasedOnResourcesEffect extends Effect {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7541480330001366752L;
	
	/** The resource. */
	private Resource resource;
	
	/** The victory points. */
	private VictoryPoint victoryPoints;
	
	/**
	 * Instantiates a new lose points based on resources effect.
	 *
	 * @param resource the resource
	 * @param victoryPoints the victory points
	 */
	public LosePointsBasedOnResourcesEffect(Resource resource, VictoryPoint victoryPoints) {
		this.resource = resource;
		this.victoryPoints = victoryPoints;
	}
	
	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.effect.Effect#applyEffect(it.polimi.ingsw.ps19.Player)
	 */
	@Override
	public void applyEffect(Player player) {
		int multiplier;
		int amount;
		amount=player.getResourceChest().getResourceInChest(resource).getAmount();
		multiplier=amount/resource.getAmount();
		victoryPoints.setAmount(victoryPoints.getAmount()*multiplier);
		player.getResourceChest().subResource(victoryPoints);

	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "You lose "+ victoryPoints.toString()+ " for every "
				+ resource.toString()+" you have at the end of the game";
	}

	

}
