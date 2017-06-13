package it.polimi.ingsw.ps19.command;

import it.polimi.ingsw.ps19.client.ClientCommandHandler;

public class AskPrivilegeChoiceCommand extends ServerToClientCommand{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7880502444669115290L;

	@Override
	public void processCommand(ClientCommandHandler clientCommandHandler) {
		clientCommandHandler.applyCommand(this);
		
	}

}
