package it.polimi.ingsw.ps19.view.cli;

/**
 * This interface will define the behaviour of a class that listens for an input
 * 
 * @author Jimmy
 *
 */
public interface InputListener {
	
	/**
	 * Notifies that an input has been received
	 * 
	 * @param input
	 */
	void notify(String input);
}
