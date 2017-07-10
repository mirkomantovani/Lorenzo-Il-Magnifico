package it.polimi.ingsw.ps19.model.excommunicationtile;

import java.util.List;

import it.polimi.ingsw.ps19.model.Player;
import it.polimi.ingsw.ps19.model.card.CardType;
import it.polimi.ingsw.ps19.model.card.DevelopmentCard;
import it.polimi.ingsw.ps19.model.effect.Effect;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;
import it.polimi.ingsw.ps19.model.resource.VictoryPoint;

/**
 * Lose victoryPoints points for every wood or stone pictured in the costs of the cards you have
 * of the specified CardType type.
 *
 * @author Mirko
 */
public class LosePointsEveryWoodStoneEffect extends Effect {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5082995786213243247L;
	
	/** The victory point. */
	private VictoryPoint victoryPoint;
	
	/** The card type. */
	private CardType cardType;
	
	/**
	 * Instantiates a new lose points every wood stone effect.
	 *
	 * @param victoryPoint the victory point
	 * @param cardType the card type
	 */
	public LosePointsEveryWoodStoneEffect(VictoryPoint victoryPoint,CardType cardType) {
		this.victoryPoint = victoryPoint;
		this.cardType=cardType;
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.effect.Effect#applyEffect(it.polimi.ingsw.ps19.Player)
	 */
	@Override
	public void applyEffect(Player player) {

		int sum=0;
		List<DevelopmentCard> deck;
		deck=player.getDeckOfType(cardType);
		for(DevelopmentCard card:deck){
			sum+=card.getCost().getStoneAmount();
			sum+=card.getCost().getWoodAmount();
		}
		this.victoryPoint.setAmount(victoryPoint.getAmount()*sum);
		ResourceChest rc=new ResourceChest();
		rc.addResource(victoryPoint);
		player.subResources(rc);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "You Lose "+ victoryPoint.toString() + 
				" for every wood or stone pictured in the costs of the "+
				cardType.toString().toLowerCase()+" you own";
				

	}
	
	

}
