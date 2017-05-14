package it.polimi.ingsw.ps19.model.deck;


import it.polimi.ingsw.ps19.ExcommunicationTile;
import it.polimi.ingsw.ps19.Period;

import java.util.Random;

/**
 * @author matteo
 *
 */
public class ExcommunicationDeck {
	
	private ExcommunicationTile[] tiles; 
	
	public ExcommunicationTile getExcommunicationTile(Period period){
		
	Random random = new Random();
	
	while(true)
	{
		int i = random.nextInt(tiles.length);
		if(tiles[i].getPeriod() == period){
			return(tiles[i]);
		}
	}

	}
	


}
