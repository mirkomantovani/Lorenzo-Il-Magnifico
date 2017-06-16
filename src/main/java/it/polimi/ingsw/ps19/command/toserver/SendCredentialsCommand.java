package it.polimi.ingsw.ps19.command.toserver;

import it.polimi.ingsw.ps19.server.ClientHandler;
import it.polimi.ingsw.ps19.server.ServerCommandHandler;

public class SendCredentialsCommand extends ClientToServerCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = -643151000952056736L;
	
	private String username;
	private String password;

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	
	public void processCommand(ServerCommandHandler serverHandlerCommand,ClientHandler clientHandler) {
		serverHandlerCommand.applyCommand(this,clientHandler);

	}

	@Override
	public void processCommand(ServerCommandHandler serverHandlerCommand) {
		System.out.println("SendCredentialCommand: you should not be here");
	}

}
