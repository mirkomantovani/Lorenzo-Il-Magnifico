package it.polimi.ingsw.ps19.view;

/**
 * @author Mirko
 *
 */
public interface UserInterface {

	
	void initializeMatch();
	
	void initializeTurn();
	
	void startTurn();
	
	void commandNotValid();
	
	void playerStatusChange();
	
	/**
	 * Notification of a generic player move
	 */
	void playerMove();
	
	/**
	 * It's <player> turn!
	 */
	void playerTurn();
	
	void win();
	
	void lose();
}
