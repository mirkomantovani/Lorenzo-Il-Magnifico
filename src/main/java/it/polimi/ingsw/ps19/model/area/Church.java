package it.polimi.ingsw.ps19.model.area;

import it.polimi.ingsw.ps19.ExcommunicationTile;

/**
 * @author Mirko
 *
 */
public class Church {
	
	private ExcommunicationTile excommunicationFirst;
	
	private ExcommunicationTile excommunicationSecond;
	
	private ExcommunicationTile excommunicationThird;

	
	public Church(ExcommunicationTile excommunicationFirst, ExcommunicationTile excommunicationSecond,
			ExcommunicationTile excommunicationThird) {
		
		this.excommunicationFirst = excommunicationFirst;
		this.excommunicationSecond = excommunicationSecond;
		this.excommunicationThird = excommunicationThird;
	}

	
}
