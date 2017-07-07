package it.polimi.ingsw.ps19.command.toclient;

import it.polimi.ingsw.ps19.client.ClientCommandHandler;

/**
 * The Class InvalidActionCommand.
 *
 * @author matteo
 * the command to notify the client about an invalid action
 */
public class InvalidActionCommand extends ServerToClientCommand {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6304179008470498378L;
	
	/** The invalid code. */
	private String invalidCode;
	
	/**
	 * Instantiates a new invalid action command.
	 */
	public InvalidActionCommand(){
		this.invalidCode="";
	
	}

	/**
	 * Instantiates a new invalid action command.
	 *
	 * @param notApplicableCode the not applicable code
	 */
	public InvalidActionCommand(String notApplicableCode) {
		this.invalidCode=notApplicableCode;
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.command.toclient.ServerToClientCommand#processCommand(it.polimi.ingsw.ps19.client.ClientCommandHandler)
	 */
	@Override
	public void processCommand(ClientCommandHandler clientCommandHandler) {
		clientCommandHandler.applyCommand(this);
		
	}
	
	/**
	 * Gets the invalid code.
	 *
	 * @return the invalid code
	 */
	public String getInvalidCode(){
		return invalidCode;
	}
	
}
