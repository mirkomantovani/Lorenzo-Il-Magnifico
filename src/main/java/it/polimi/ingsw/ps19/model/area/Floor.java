package it.polimi.ingsw.ps19.model.area;


import it.polimi.ingsw.ps19.model.card.DevelopmentCard;
import it.polimi.ingsw.ps19.model.effect.InstantResourcesEffect;

/**
 * @author matteo
 *
 */
public class Floor {
	
	DevelopmentCard card;	

	SingleActionSpace actionSpace;	
	Tower tower;
	
	public Floor(DevelopmentCard card, Tower tower, int actionSpaceCost, InstantResourcesEffect instantResourcesEffect){
		this.card = card;
		this.tower = tower;
		this.actionSpace = new SingleActionSpace(actionSpaceCost,instantResourcesEffect);
	}

	public DevelopmentCard getCard() {
		return card;
	}
	
	public void setCard(DevelopmentCard card) {
		this.card = card;
	}

	public SingleActionSpace getActionSpace() {
		return actionSpace;
	}

	public Tower getTower() {
		return tower;
	}
	
}
