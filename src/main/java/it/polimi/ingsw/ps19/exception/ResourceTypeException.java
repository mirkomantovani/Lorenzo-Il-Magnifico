package it.polimi.ingsw.ps19.exception;

/**
 * The Class ResourceTypeException.
 *
 * @author Mirko
 */
public class ResourceTypeException extends RuntimeException {

	
	/** The error. */
	private final String error = "Resource id is not valid";
	
	/**
	 * Gets the error code.
	 *
	 * @return the error code
	 */
	public String getErrorCode() {
		return error;
	}
}
