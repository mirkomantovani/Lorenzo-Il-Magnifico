package it.polimi.ingsw.ps19.command.toserver;

import it.polimi.ingsw.ps19.server.ServerCommandHandler;

/**
 * The Class ChatMessageClientCommand.
 * This class catch the chat message written by a client
 */
/**
 * @author matteo
 *
 */
public class ChatMessageClientCommand extends ClientToServerCommand {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2602059606073679275L;
	
	/** The message. */
	private String message;

	
	/**
	 * Instantiates a new chat message client command.
	 *
	 * @param message the message
	 */
	public ChatMessageClientCommand(String message) {
		this.message = message;
	}


	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}


	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.command.toserver.ClientToServerCommand#processCommand(it.polimi.ingsw.ps19.server.ServerCommandHandler)
	 */
	@Override
	public void processCommand(ServerCommandHandler serverHandlerCommand) {
		serverHandlerCommand.applyCommand(this);
	}

}
