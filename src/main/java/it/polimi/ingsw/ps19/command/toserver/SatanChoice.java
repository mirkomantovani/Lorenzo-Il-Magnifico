package it.polimi.ingsw.ps19.command.toserver;

import it.polimi.ingsw.ps19.Color;
import it.polimi.ingsw.ps19.server.ServerCommandHandler;

public class SatanChoice extends ClientToServerCommand{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2239627322643830186L;
	
	private Color color;

	@Override
	public void processCommand(ServerCommandHandler serverHandlerCommand) {
		serverHandlerCommand.applyCommand(this);
		
	}
	
	public SatanChoice(Color color){
		this.color = color;
	}
	
	

}
