package it.polimi.ingsw.ps19.command.toclient;

import it.polimi.ingsw.ps19.client.ClientCommandHandler;

/**
 * The Class NotifyExcommunicationCommand.
 *
 * @author matteo
 * 
 * the command used from the server to notify the excommunication status to the client
 */
public class NotifyExcommunicationCommand extends ServerToClientCommand{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2543318254054111680L;

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.command.toclient.ServerToClientCommand#processCommand(it.polimi.ingsw.ps19.client.ClientCommandHandler)
	 */
	@Override
	public void processCommand(ClientCommandHandler clientCommandHandler) {
		clientCommandHandler.applyCommand(this);
		
	}
	

}
