package it.polimi.ingsw.ps19.command.toclient;

import it.polimi.ingsw.ps19.client.ClientCommandHandler;

public class AssignColorCommand extends ServerToClientCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7572217227580448284L;
	
	private String color;
	
	

	public AssignColorCommand(String color) {
		this.color = color;
	}
	
	public String getColor() {
		return color;
	}

	@Override
	public void processCommand(ClientCommandHandler clientCommandHandler) {
		clientCommandHandler.applyCommand(this);
	}

}
