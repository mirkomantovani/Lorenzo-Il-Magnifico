package it.polimi.ingsw.ps19.command.toserver;

import it.polimi.ingsw.ps19.server.ServerCommandHandler;

/**
 * @author matteo
 * the command to ask to the server to close the connection 
 *
 */
public class RequestClosureCommand extends ClientToServerCommand {

	
	private String playerColor;
	/**
	 * 
	 */
	private static final long serialVersionUID = 4077481752452935713L;

	
	
	public RequestClosureCommand(String playerColor) {
		this.playerColor = playerColor;
	}



	@Override
	public void processCommand(ServerCommandHandler serverHandlerCommand) {
		serverHandlerCommand.applyCommand(this);
		
	}



	public String getPlayerColor() {
		return playerColor;
	}
	


}
