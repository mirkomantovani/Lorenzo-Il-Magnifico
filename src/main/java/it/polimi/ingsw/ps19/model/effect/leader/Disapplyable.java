package it.polimi.ingsw.ps19.model.effect.leader;

import it.polimi.ingsw.ps19.Player;

public interface Disapplyable {

	public abstract void disapplyEffect(Player p);

	public abstract void applyEffect(Player player);
}
