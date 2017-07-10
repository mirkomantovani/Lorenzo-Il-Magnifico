package it.polimi.ingsw.ps19.command.toserver;

import it.polimi.ingsw.ps19.server.ServerCommandHandler;

public class SatanChoiceCommand extends ClientToServerCommand{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2239627322643830186L;
	
	private String color;

	@Override
	public void processCommand(ServerCommandHandler serverHandlerCommand) {
		serverHandlerCommand.applyCommand(this);
		
	}
	
	public SatanChoiceCommand(String color){
		this.color = color;
	}
	
	public String getColor(){
		return color;
	}
	

}
