package it.polimi.ingsw.ps19.exception;

/**
 * @author Mirko
 *
 */
public class ResourceTypeException extends RuntimeException {

	
	private final String error = "Resource id is not valid";
	
	public String getErrorCode() {
		return error;
	}
}