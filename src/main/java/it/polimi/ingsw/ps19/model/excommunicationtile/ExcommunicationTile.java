package it.polimi.ingsw.ps19.model.excommunicationtile;

import java.io.Serializable;

import it.polimi.ingsw.ps19.Period;
import it.polimi.ingsw.ps19.model.effect.Effect;

/**
 * @author Mirko
 *
 */
public class ExcommunicationTile implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4535401537979829418L;

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



	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("Period: "+period.toString().toLowerCase());
		s.append("\nEffect: "+effect.toString());
			
		return s.toString();
	}


}
