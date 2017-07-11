package it.polimi.ingsw.ps19.exception;

/**
 * This is a checked Exception, the caller of an action must deal with the fact that the action
 * should be not applicable .
 *
 * @author Mirko
 */
public class NotApplicableException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** The not applicable code. */
	private String notApplicableCode;

	/**
	 * Instantiates a new not applicable exception.
	 *
	 * @param notApplicableCode the not applicable code
	 */
	public NotApplicableException(String notApplicableCode) {
		this.notApplicableCode=notApplicableCode;
	}

	/**
	 * Gets the not applicable code.
	 *
	 * @return the not applicable code
	 */
	public String getNotApplicableCode() {
		return notApplicableCode;
	}

}
