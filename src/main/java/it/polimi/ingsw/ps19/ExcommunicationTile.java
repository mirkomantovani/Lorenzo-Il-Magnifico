package it.polimi.ingsw.ps19;

import java.util.List;

/**
 * @author Mirko
 *
 */
public class ExcommunicationTile {
	
	Period period;
	
	private ExcommunicationEffect effect;
	
	private List<Player> excommunicatedPlayers;  //should we do it like this? (the association)
	
	

	public ExcommunicationTile(Period period, ExcommunicationEffect effect) {
		this.period = period;
		this.effect = effect;
	}



	public ExcommunicationEffect getEffect() {
		return effect;
	}


	public Period getPeriod() {
		return period;
	}



	public List<Player> getExcommunicatedPlayers() {
		return excommunicatedPlayers;
	}



	public void addExcommunicatedPlayer(Player player) {
		excommunicatedPlayers.add(player);
	}

}
