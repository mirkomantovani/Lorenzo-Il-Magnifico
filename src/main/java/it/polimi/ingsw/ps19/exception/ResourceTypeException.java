package it.polimi.ingsw.ps19.exception;

/**
 * The unchecked exception thrown when an invalid resource type is passed to the Resource Factory
 *
 * @author Mirko
 */
public class ResourceTypeException extends RuntimeException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
