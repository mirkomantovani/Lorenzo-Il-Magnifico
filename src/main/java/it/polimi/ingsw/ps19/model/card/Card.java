package it.polimi.ingsw.ps19.model.card;

import java.io.Serializable;

import it.polimi.ingsw.ps19.model.Player;

/**
 * The Class Card.
 *
 * @author Mirko
 */
public abstract class Card implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3472148174663920971L;
	
	/** The player. */
	protected Player player;
	
	/** The name. */
	protected String name;
	
	/**
	 * Instantiates a new card.
	 *
	 * @param name the name
	 */
	public Card(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the player.
	 *
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}
	
	/**
	 * Sets the player.
	 *
	 * @param player the new player
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

}
