package it.polimi.ingsw.ps19.model.excommunicationtile;

import java.io.Serializable;

/**
 * The Class ExcommunicationEffect.
 *
 * @author Mirko
 */
public class ExcommunicationEffect implements Serializable {

	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4319086030631087795L;
	
	/** The tile. */
	private ExcommunicationTile tile;
	

	/**
	 * Gets the tile.
	 *
	 * @return the tile
	 */
	public ExcommunicationTile getTile() {
		return tile;
	}

	/**
	 * Sets the tile.
	 *
	 * @param tile the new tile
	 */
	public void setTile(ExcommunicationTile tile) {
		this.tile = tile;
	}


//	public isExcommunicated(){
//		
//	}
	
}
