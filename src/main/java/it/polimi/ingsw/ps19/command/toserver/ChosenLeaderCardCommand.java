package it.polimi.ingsw.ps19.command.toserver;

import it.polimi.ingsw.ps19.server.ServerCommandHandler;

public class ChosenLeaderCardCommand extends ClientToServerCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7435305980891201004L;

	private String name;

	private String playerColor;

	public ChosenLeaderCardCommand(String name, String playerColor) {
		this.name = name;
		this.playerColor = playerColor;
	}

	@Override
	public void processCommand(ServerCommandHandler serverHandlerCommand) {
		serverHandlerCommand.applyCommand(this);
	}


	
	public String getPlayerColor() {
		return playerColor;
	}

	public String getName() {
		return name;
	}

}
