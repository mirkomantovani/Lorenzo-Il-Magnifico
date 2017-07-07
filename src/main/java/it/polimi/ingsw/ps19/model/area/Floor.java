package it.polimi.ingsw.ps19.model.area;


import java.io.Serializable;
import java.util.ArrayList;

import it.polimi.ingsw.ps19.model.card.DevelopmentCard;
import it.polimi.ingsw.ps19.model.effect.InstantResourcesEffect;

/**
 * The Class Floor.
 * This class represents the floors in the towers, the related action spaces have specific bonuses
 * taken from file and here you can place the familiar
 *
 * @author matteo
 */
public class Floor implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5822825459286872128L;
	
	/** The card. */
	DevelopmentCard card;
	
	/** The action space. */
	SingleActionSpace actionSpace;	
	
	/** The tower. */
	Tower tower;

	
	/**
	 * Instantiates a new floor.
	 *
	 * @param card the card
	 * @param tower the tower
	 * @param actionSpaceCost the action space cost
	 * @param instantResourcesEffect the instant resources effect
	 */
	public Floor(DevelopmentCard card, Tower tower, int actionSpaceCost, InstantResourcesEffect instantResourcesEffect){
		this.card = card;
		this.tower = tower;
		this.actionSpace = new SingleActionSpace(actionSpaceCost,instantResourcesEffect);
	}

	/**
	 * Gets the card.
	 *
	 * @return the card
	 */
	public DevelopmentCard getCard() {
		return card;
	}
	
	/**
	 * Sets the card.
	 *
	 * @param card the new card
	 */
	public void setCard(DevelopmentCard card) {
		this.card = card;
	}

	/**
	 * Gets the action space.
	 *
	 * @return the action space
	 */
	public SingleActionSpace getActionSpace() {
		return actionSpace;
	}

	/**
	 * Gets the tower.
	 *
	 * @return the tower
	 */
	public Tower getTower() {
		return tower;
	}
	
	
	
}
