package it.polimi.ingsw.ps19.exception;

/**
 * The Class CardTypeException.
 *
 * @author Jimmy
 */
public class CardTypeException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The error message. */
	private final String errorMessage = "Card id is not valid";
	
	/**
	 * Gets the error code.
	 *
	 * @return the error code
	 */
	public String getErrorCode(){
		return errorMessage;
	}

}
