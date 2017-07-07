package it.polimi.ingsw.ps19.exception;



/**
 * A checked Exception to signal a wrong player to send a command to has been selected
 *
 * @author Mirko
 */
public class WrongPlayerException extends Exception {
	
/** The error. */
private final String error = "The clientHandler associated to the player the server is "
		+ "trying to send a command to is not in the list of clientHandlers";
	
	/**
	 * Gets the error.
	 *
	 * @return the error
	 */
	public String getError() {
		return error;
	}

}
