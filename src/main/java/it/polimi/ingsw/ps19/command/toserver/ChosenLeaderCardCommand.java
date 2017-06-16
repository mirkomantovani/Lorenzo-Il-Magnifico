package it.polimi.ingsw.ps19.command.toserver;

import it.polimi.ingsw.ps19.server.ClientHandler;
import it.polimi.ingsw.ps19.server.ServerCommandHandler;

public class ChosenLeaderCardCommand extends ClientToServerCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7435305980891201004L;
	
	private String name;
	
	public ChosenLeaderCardCommand(String name){
		this.name = name;
	}

	@Override
	public void processCommand(ServerCommandHandler serverHandlerCommand) {
		System.out.println("SendCredentialCommand: you should not be here");
		
	}
	
	public void processCommand(ServerCommandHandler serverHandlerCommand,ClientHandler clientHandler) {
		serverHandlerCommand.applyCommand(this,clientHandler);

	}

	public String getName() {
		return name;
	}

	
}
