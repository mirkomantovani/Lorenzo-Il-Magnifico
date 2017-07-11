package it.polimi.ingsw.ps19.model.excommunicationtile;

import it.polimi.ingsw.ps19.model.Player;
import it.polimi.ingsw.ps19.model.effect.Effect;
import it.polimi.ingsw.ps19.model.resource.VictoryPoint;

/**
 *  * You lose "victoryPoints" points for every "resource" of type: wood,stone,servants,coin
 *  you have at the end of the game.
 *
 * @author Mirko
 */
public class LosePointsForEveryResourceEffect extends Effect {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3172886284056207322L;
	
	/** The victory point. */
	private VictoryPoint victoryPoint;
	
	/**
	 * Instantiates a new lose points for every resource effect.
	 *
	 * @param victoryPoint the victory point
	 */
	public LosePointsForEveryResourceEffect(VictoryPoint victoryPoint) {
		this.victoryPoint = victoryPoint;
	}
	
	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.effect.Effect#applyEffect(it.polimi.ingsw.ps19.Player)
	 */
	@Override
	public void applyEffect(Player player) {
		int sumResources;
		sumResources=player.getResourceChest().sumResources();
		VictoryPoint vpToSub;
		vpToSub=new VictoryPoint(sumResources*victoryPoint.getAmount());
		player.getResourceChest().subResource(vpToSub);

	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "You lose "+victoryPoint+" for every resource of"
				+ " type: wood, stone, servants or coin you have at the end of the game";
	}

}
