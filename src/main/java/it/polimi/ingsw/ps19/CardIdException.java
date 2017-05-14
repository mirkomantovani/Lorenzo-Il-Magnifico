package it.polimi.ingsw.ps19;

/**
 * @author Jimmy
 *
 */
public class CardIdException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private final String errorMessage = "Card id is not valid";
	
	public String getErrorCode(){
		return errorMessage;
	}

}
