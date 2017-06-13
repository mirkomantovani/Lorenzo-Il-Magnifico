package it.polimi.ingsw.ps19.exception;



/**
 * @author Mirko
 *
 */
public class WrongPlayerException extends Exception {
	
private final String error = "The clientHandler associated to the player the server is "
		+ "trying to send a command to is not in the list of clientHandlers";
	
	public String getError() {
		return error;
	}

}
