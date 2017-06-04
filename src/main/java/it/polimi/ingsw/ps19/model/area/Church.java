package it.polimi.ingsw.ps19.model.area;

import java.io.IOException;
import java.util.Random;

import it.polimi.ingsw.ps19.Period;
import it.polimi.ingsw.ps19.constant.CardConstants;
import it.polimi.ingsw.ps19.model.excommunicationtile.ExcommunicationTile;
import it.polimi.ingsw.ps19.model.excommunicationtile.ExcommunicationTilesCreator;

/**
 * @author Mirko
 *
 */
public class Church {
	
	private ExcommunicationTile excommunicationFirst;	
	private ExcommunicationTile excommunicationSecond;	
	private ExcommunicationTile excommunicationThird;

	
	public Church() {
		
		Random rnd = new Random();
		ExcommunicationTile[] tiles=new ExcommunicationTile[CardConstants.EXCOMMUNICATION_TILES];
		try {
			tiles=ExcommunicationTilesCreator.createExcommunicationTiles(CardConstants.EXCOMMUNICATION_TILES);
		} catch (IOException e) {
			System.out.println("Error in retrieving excommunication tiles from file");
			e.printStackTrace();
		}
		
		this.excommunicationFirst = 
				tiles[rnd.nextInt(CardConstants.EXCOMMUNICATION_TILES/Period.values().length)];
		this.excommunicationSecond = 
				tiles[rnd.nextInt(CardConstants.EXCOMMUNICATION_TILES/Period.values().length+
						CardConstants.EXCOMMUNICATION_TILES/Period.values().length)];
		this.excommunicationThird =
				tiles[rnd.nextInt(CardConstants.EXCOMMUNICATION_TILES/Period.values().length+
						2*CardConstants.EXCOMMUNICATION_TILES/Period.values().length)];
		//We're not gonna need the tiles anymore after choosing the 3 tiles of the match
		tiles=null;
	}
	
	public ExcommunicationTile getExcommunicationFirst() {
		return excommunicationFirst;
	}


	public ExcommunicationTile getExcommunicationSecond() {
		return excommunicationSecond;
	}


	public ExcommunicationTile getExcommunicationThird() {
		return excommunicationThird;
	}



}
