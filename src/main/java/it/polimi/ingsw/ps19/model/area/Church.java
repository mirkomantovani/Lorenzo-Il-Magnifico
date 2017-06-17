package it.polimi.ingsw.ps19.model.area;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;

import it.polimi.ingsw.ps19.Period;
import it.polimi.ingsw.ps19.constant.BoardConstants;
import it.polimi.ingsw.ps19.constant.CardConstants;
import it.polimi.ingsw.ps19.model.excommunicationtile.ExcommunicationTile;
import it.polimi.ingsw.ps19.model.excommunicationtile.ExcommunicationTilesCreator;
import it.polimi.ingsw.ps19.model.resource.MilitaryPoint;

/**
 * @author Mirko
 *
 */
public class Church implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2245918610305460800L;
	private ExcommunicationTile excommunicationFirst;	
	private ExcommunicationTile excommunicationSecond;	
	private ExcommunicationTile excommunicationThird;
	
	private MilitaryPoint[] militaryPoints; //Those are the military points amount related to a specific 
												 // amount of faithpoints (this values are taken from file);

	
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
				tiles[rnd.nextInt(CardConstants.EXCOMMUNICATION_TILES/Period.values().length)+
						CardConstants.EXCOMMUNICATION_TILES/Period.values().length];
		this.excommunicationThird =
				tiles[rnd.nextInt(CardConstants.EXCOMMUNICATION_TILES/Period.values().length)+
						2*CardConstants.EXCOMMUNICATION_TILES/Period.values().length];
		//We're not gonna need the tiles anymore after choosing the 3 tiles of the match
		tiles=null;
		
		militaryPoints = new MilitaryPoint[BoardConstants.CHURCHSLOTS];
		try {
			this.militaryPoints = BoardInitializer.churchBonuses();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	public MilitaryPoint[] getMilitaryPoints() {
		return militaryPoints;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("---- The Church ---- \n The church contains these Excommunication tiles:\n ");
		builder.append(excommunicationFirst.toString());
		builder.append(", \n instead in the second period you ");
		builder.append(excommunicationSecond.toString());
		builder.append(", \n finally in the third period you ");
		builder.append(excommunicationThird.toString());
		return builder.toString();
	}
	
	
	
	
	


}