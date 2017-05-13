package it.polimi.ingsw.ps19;

/**
 * @author Mirko
 *
 */
public class ResourceIdException extends RuntimeException {

	
	private final String error = "Resource id is not valid";
	
	public String getErrorCode() {
		return error;
	}
}
