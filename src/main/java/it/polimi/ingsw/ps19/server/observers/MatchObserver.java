package it.polimi.ingsw.ps19.server.observers;

/**
 * This interface is implemented by the so called "VirtualView" which in our case is the 
 * MatchHandler class. The only one that knows the state of the game and is able to determine
 * the player to send model updates to.
 * An instance of this class will be in those classes of the model
 * (Match) that need to notify the status change 
 * @author Mirko
 *
 */
public interface MatchObserver {

	public void notifyPlayerStatusChange();
	
//	public void notifyBoardChange();
	
	public void notifyFamilyPlaced();
	
//	public void notifyCardTaken();
	
}
