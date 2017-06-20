package it.polimi.ingsw.ps19.model.excommunicationtile;

import java.util.ArrayList;

import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.model.card.CardType;
import it.polimi.ingsw.ps19.model.card.DevelopmentCard;
import it.polimi.ingsw.ps19.model.effect.Effect;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;
import it.polimi.ingsw.ps19.model.resource.VictoryPoint;

/**
 * Lose victoryPoints points for every wood or stone pictured in the costs of the cards you have
 * of the specified CardType type
 * @author Mirko
 *
 */
public class LosePointsEveryWoodStoneEffect extends Effect {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5082995786213243247L;
	private VictoryPoint victoryPoint;
	private CardType cardType;
	
	public LosePointsEveryWoodStoneEffect(VictoryPoint victoryPoint,CardType cardType) {
		this.victoryPoint = victoryPoint;
		this.cardType=cardType;
	}

	@Override
	public void applyEffect(Player player) {

		int sum=0;
		ArrayList<DevelopmentCard> deck;
		deck=(ArrayList<DevelopmentCard>) player.getDeckOfType(cardType);
		for(DevelopmentCard card:deck){
			sum+=card.getCost().getStoneAmount();
			sum+=card.getCost().getWoodAmount();
		}
		this.victoryPoint.setAmount(victoryPoint.getAmount()*sum);
		ResourceChest rc=new ResourceChest();
		rc.addResource(victoryPoint);
		player.subResources(rc);
	}
	
	@Override
	public String toString() {
		return "You Lose "+ victoryPoint.toString() + 
				" for every wood or stone pictured in the costs of the "+
				cardType.toString().toLowerCase()+" you own";
				

	}
	
	

}
