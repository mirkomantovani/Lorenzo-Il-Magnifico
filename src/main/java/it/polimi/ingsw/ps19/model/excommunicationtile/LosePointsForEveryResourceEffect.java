package it.polimi.ingsw.ps19.model.excommunicationtile;

import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.model.effect.Effect;
import it.polimi.ingsw.ps19.model.resource.VictoryPoint;

/**
 *  * You lose "victoryPoints" points for every "resource" of type: wood,stone,servants,coin
 *  you have at the end of the game
 * @author Mirko
 *
 */
public class LosePointsForEveryResourceEffect extends Effect {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3172886284056207322L;
	private VictoryPoint victoryPoint;
	public LosePointsForEveryResourceEffect(VictoryPoint victoryPoint) {
		this.victoryPoint = victoryPoint;
	}
	
	@Override
	public void applyEffect(Player player) {
		int sumResources;
		sumResources=player.getResourceChest().sumResources();
		VictoryPoint vpToSub;
		vpToSub=new VictoryPoint(sumResources*victoryPoint.getAmount());
		player.getResourceChest().subResource(vpToSub);

	}


}
