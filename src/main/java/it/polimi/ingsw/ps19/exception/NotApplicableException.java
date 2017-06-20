package it.polimi.ingsw.ps19.exception;

/**
 * This is a checked Exception, the caller of an action must deal with the fact that the action
 * should be not applicable 
 * @author Mirko
 *
 */
public class NotApplicableException extends Exception {
	
	private String notApplicableCode;

	public NotApplicableException(String notApplicableCode) {
		this.notApplicableCode=notApplicableCode;
	}

	public String getNotApplicableCode() {
		return notApplicableCode;
	}

}
