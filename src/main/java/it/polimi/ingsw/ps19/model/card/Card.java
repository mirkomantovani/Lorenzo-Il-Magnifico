package it.polimi.ingsw.ps19.model.card;

import java.io.Serializable;

import it.polimi.ingsw.ps19.Player;

/**
 * @author Mirko
 *
 */
public abstract class Card implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3472148174663920971L;
	
	protected Player player;
	protected String name;
	
	public Card(String name) {
		this.name = name;
	}
	
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public String getName() {
		return name;
	}

}
