package it.polimi.ingsw.ps19.command.toserver;

import it.polimi.ingsw.ps19.server.ServerCommandHandler;

/**
 * The Class SendCredentialsCommand.
 * This class represent the message with a client credentials received by server
 */
public class SendCredentialsCommand extends ClientToServerCommand {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -643151000952056736L;
	
	/** The username. */
	private String username;
	
	/** The password. */
	private String password;
	
	/** The player color. */
	private String playerColor;

	
	
	/**
	 * Instantiates a new send credentials command.
	 *
	 * @param username the username
	 * @param password the password
	 * @param playerColor the player color
	 */
	public SendCredentialsCommand(String username, String password, String playerColor) {
		this.username = username;
		this.password = password;
		this.playerColor = playerColor;
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	
	
	/**
	 * Gets the player color.
	 *
	 * @return the player color
	 */
	public String getPlayerColor() {
		return playerColor;
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.command.toserver.ClientToServerCommand#processCommand(it.polimi.ingsw.ps19.server.ServerCommandHandler)
	 */
	@Override
	public void processCommand(ServerCommandHandler serverHandlerCommand) {
		serverHandlerCommand.applyCommand(this);
	}

}
