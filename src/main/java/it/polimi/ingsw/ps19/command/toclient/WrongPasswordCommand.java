package it.polimi.ingsw.ps19.command.toclient;

import it.polimi.ingsw.ps19.client.ClientCommandHandler;

/**
 * Signals the user that the password he inserted was not correct
 * @author Mirko
 *
 */
public class WrongPasswordCommand extends ServerToClientCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4210904855925108874L;
	
	private String username;

	public WrongPasswordCommand(String username) {
		this.username=username;
	}

	@Override
	public void processCommand(ClientCommandHandler clientCommandHandler) {
		clientCommandHandler.applyCommand(this);
	}

	public String getUsername() {
		return username;
	}
	
	

}
