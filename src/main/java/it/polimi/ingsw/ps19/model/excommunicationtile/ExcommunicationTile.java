package it.polimi.ingsw.ps19.model.excommunicationtile;

import java.util.List;

import it.polimi.ingsw.ps19.Period;
import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.model.effect.Effect;

/**
 * @author Mirko
 *
 */
public class ExcommunicationTile {
	
	private Period period;
	
	private Effect effect;
	
	public ExcommunicationTile(Period period, Effect effect) {
		this.period = period;
		this.effect = effect;
	}



	public Effect getEffect() {
		return effect;
	}


	public Period getPeriod() {
		return period;
	}


}
