package it.polimi.ingsw.ps19.command.toserver;

import it.polimi.ingsw.ps19.server.ServerCommandHandler;

/**
 * The Class RequestClosureCommand.
 *
 * @author matteo
 * the command to ask to the server to close the connection 
 */
public class RequestClosureCommand extends ClientToServerCommand {

	
	/** The player color. */
	private String playerColor;
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4077481752452935713L;

	
	
	/**
	 * Instantiates a new request closure command.
	 *
	 * @param playerColor the player color
	 */
	public RequestClosureCommand(String playerColor) {
		this.playerColor = playerColor;
	}



	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.command.toserver.ClientToServerCommand#processCommand(it.polimi.ingsw.ps19.server.ServerCommandHandler)
	 */
	@Override
	public void processCommand(ServerCommandHandler serverHandlerCommand) {
		serverHandlerCommand.applyCommand(this);
		
	}



	/**
	 * Gets the player color.
	 *
	 * @return the player color
	 */
	public String getPlayerColor() {
		return playerColor;
	}
	


}
