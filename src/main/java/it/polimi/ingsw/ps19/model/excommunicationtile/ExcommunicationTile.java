package it.polimi.ingsw.ps19.model.excommunicationtile;

import java.awt.LayoutManager;
import java.io.Serializable;

import it.polimi.ingsw.ps19.Period;
import it.polimi.ingsw.ps19.model.effect.Effect;

/**
 * The Class ExcommunicationTile.
 *
 * @author Mirko
 */
public class ExcommunicationTile implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4535401537979829418L;

	/** The period. */
	private Period period;
	
	/** The effect. */
	private Effect effect;
	
	/** The id. */
	private int id;
	
	/**
	 * Instantiates a new excommunication tile.
	 *
	 * @param period the period
	 * @param effect the effect
	 * @param id the id
	 */
	public ExcommunicationTile(Period period, Effect effect,int id) {
		this.period = period;
		this.effect = effect;
		this.id = id;
	}



	/**
	 * Gets the effect.
	 *
	 * @return the effect
	 */
	public Effect getEffect() {
		return effect;
	}


	/**
	 * Gets the period.
	 *
	 * @return the period
	 */
	public Period getPeriod() {
		return period;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("Period: "+period.toString().toLowerCase());
		s.append("\nEffect: "+effect.toString());
			
		return s.toString();
	}



	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return this.id;
	}


}
