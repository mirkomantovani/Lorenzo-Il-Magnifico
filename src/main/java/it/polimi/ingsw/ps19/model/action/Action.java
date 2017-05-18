package it.polimi.ingsw.ps19.model.action;

import it.polimi.ingsw.ps19.Player;

public interface  Action {
	
	private Player player;
	
	public Action(Player player) {
		this.player = player;
	}

	public abstract void apply();
	
	public abstract boolean isApplicable();
	

}
