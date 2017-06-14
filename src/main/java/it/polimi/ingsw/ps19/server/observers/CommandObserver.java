package it.polimi.ingsw.ps19.server.observers;

import it.polimi.ingsw.ps19.command.ClientToServerCommand;

/**
 * This interface is implemented by ServerCommandHandler, an instance of the
 * concrete class is in ClientHandlerSocket
 * @author Mirko
 *
 */
public interface CommandObserver {
	
//	public void applyCommand(ClientToServerCommand command);

	/**
	 * This allows the switch based on the type of command
	 * @param command
	 */
	public void notifyNewCommand(ClientToServerCommand command);

		
	
}
