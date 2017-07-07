package it.polimi.ingsw.ps19.server.observers;

import it.polimi.ingsw.ps19.Player;

/**
 * This interface is implemented by the 
 * MatchHandler class. The only one that knows the state of the game and is able to determine
 * the player to send model updates to based on the mapping between clientHandler and player.
 * An instance of this class will be in those classes of the model
 * (Match) that need to notify the status change 
 * @author Mirko
 *
 */
public interface MatchObserver {

	/**
	 * This method is called when information about an Match
	 * which was previously requested using an asynchronous
	 * interface becomes available.
	 *
	 * @param player the player
	 */
	public void notifyPlayerStatusChange(Player player);
	
//	public void notifyBoardChange();
	
	/**
 * This method is called when information about an Match
 * which was previously requested using an asynchronous
 * interface becomes available.
 */
public void notifyFamilyPlaced();
	
//	public void notifyCardTaken();
	
}
