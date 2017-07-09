package it.polimi.ingsw.ps19.command.toclient;

import it.polimi.ingsw.ps19.client.ClientCommandHandler;

public class PlayerDisconnectedCommand extends ServerToClientCommand {
	
	String color;

	public PlayerDisconnectedCommand(String color) {
		this.color=color;
	}

	@Override
	public void processCommand(ClientCommandHandler clientCommandHandler) {
		clientCommandHandler.applyCommand(this);
	}

	public String getColor() {
		return color;
	}
	
	

}
