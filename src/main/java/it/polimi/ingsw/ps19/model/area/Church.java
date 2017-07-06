package it.polimi.ingsw.ps19.model.area;

import java.io.IOException;
import java.io.Serializable;
import java.util.Random;

import it.polimi.ingsw.ps19.Period;
import it.polimi.ingsw.ps19.constant.BoardConstants;
import it.polimi.ingsw.ps19.constant.CardConstants;
import it.polimi.ingsw.ps19.model.excommunicationtile.ExcommunicationTile;
import it.polimi.ingsw.ps19.model.excommunicationtile.ExcommunicationTilesCreator;
import it.polimi.ingsw.ps19.model.resource.MilitaryPoint;

/**
 * The Class Church.
 *
 * @author Mirko
 */
public class Church implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2245918610305460800L;
	
	/** The excommunication first. */
	private ExcommunicationTile excommunicationFirst;
	
	/** The excommunication second. */
	private ExcommunicationTile excommunicationSecond;
	
	/** The excommunication third. */
	private ExcommunicationTile excommunicationThird;

	/** The military points. */
	private MilitaryPoint[] militaryPoints; // Those are the military points
											// amount related to a specific
											// amount of faithpoints (this
											// values are taken from file);

	/**
											 * Instantiates a new church.
											 */
											public Church() {

		Random rnd = new Random();
		ExcommunicationTile[] tiles = new ExcommunicationTile[CardConstants.EXCOMMUNICATION_TILES];
		try {
			tiles = ExcommunicationTilesCreator.createExcommunicationTiles(CardConstants.EXCOMMUNICATION_TILES);
		} catch (IOException e) {
			System.out.println("Error in retrieving excommunication tiles from file");
			e.printStackTrace();
		}

		this.excommunicationFirst = tiles[rnd.nextInt(CardConstants.EXCOMMUNICATION_TILES / Period.values().length)];
		this.excommunicationSecond = tiles[rnd.nextInt(CardConstants.EXCOMMUNICATION_TILES / Period.values().length)
				+ CardConstants.EXCOMMUNICATION_TILES / Period.values().length];
		this.excommunicationThird = tiles[rnd.nextInt(CardConstants.EXCOMMUNICATION_TILES / Period.values().length)
				+ 2 * CardConstants.EXCOMMUNICATION_TILES / Period.values().length];
		// We're not gonna need the tiles anymore after choosing the 3 tiles of
		// the match
		tiles = null;

		militaryPoints = new MilitaryPoint[BoardConstants.CHURCHSLOTS];
		try {
			this.militaryPoints = BoardInitializer.churchBonuses();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Gets the excommunication first.
	 *
	 * @return the excommunication first
	 */
	public ExcommunicationTile getExcommunicationFirst() {
		return excommunicationFirst;
	}

	/**
	 * Gets the excommunication second.
	 *
	 * @return the excommunication second
	 */
	public ExcommunicationTile getExcommunicationSecond() {
		return excommunicationSecond;
	}

	/**
	 * Gets the excommunication third.
	 *
	 * @return the excommunication third
	 */
	public ExcommunicationTile getExcommunicationThird() {
		return excommunicationThird;
	}

	/**
	 * Gets the excommunication tile.
	 *
	 * @param p the p
	 * @return the excommunication tile
	 */
	public ExcommunicationTile getExcommunicationTile(Period p){
		if(p==Period.FIRST)
			return this.excommunicationFirst;
		if(p==Period.SECOND)
			return this.excommunicationSecond;
		if(p==Period.THIRD)
			return this.excommunicationThird;
		
		return null;
		
	}

	/**
	 * Gets the military points.
	 *
	 * @return the military points
	 */
	public MilitaryPoint[] getMilitaryPoints() {
		return militaryPoints;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
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