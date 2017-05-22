package it.polimi.ingsw.ps19.model.area;

import it.polimi.ingsw.ps19.model.excommunicationtile.ExcommunicationTile;

/**
 * @author Mirko
 *
 */
public class Church {
	
	private ExcommunicationTile excommunicationFirst;	
	private ExcommunicationTile excommunicationSecond;	
	public ExcommunicationTile getExcommunicationFirst() {
		return excommunicationFirst;
	}
	
	public Church(ExcommunicationTile excommunicationFirst, ExcommunicationTile excommunicationSecond,
			ExcommunicationTile excommunicationThird) {
		
		this.excommunicationFirst = excommunicationFirst;
		this.excommunicationSecond = excommunicationSecond;
		this.excommunicationThird = excommunicationThird;
	}


	public void setExcommunicationFirst(ExcommunicationTile excommunicationFirst) {
		this.excommunicationFirst = excommunicationFirst;
	}


	public ExcommunicationTile getExcommunicationSecond() {
		return excommunicationSecond;
	}


	public void setExcommunicationSecond(ExcommunicationTile excommunicationSecond) {
		this.excommunicationSecond = excommunicationSecond;
	}


	public ExcommunicationTile getExcommunicationThird() {
		return excommunicationThird;
	}


	public void setExcommunicationThird(ExcommunicationTile excommunicationThird) {
		this.excommunicationThird = excommunicationThird;
	}


	private ExcommunicationTile excommunicationThird;

	
	
	
	

	
}
