package it.polimi.ingsw.ps19.command.toclient;

import it.polimi.ingsw.ps19.client.ClientCommandHandler;

/**
 * @author matteo
 * 
 * the command used from the server to notify the excommunication status to the client
 *
 */
public class NotifyExcommunicationCommand extends ServerToClientCommand{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2543318254054111680L;

	@Override
	public void processCommand(ClientCommandHandler clientCommandHandler) {
		clientCommandHandler.applyCommand(this);
		
	}
	

}
