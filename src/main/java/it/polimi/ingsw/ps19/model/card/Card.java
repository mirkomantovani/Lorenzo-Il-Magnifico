package it.polimi.ingsw.ps19.model.card;

import it.polimi.ingsw.ps19.Player;

/**
 * @author Mirko
 *
 */
public abstract class Card {
	
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
