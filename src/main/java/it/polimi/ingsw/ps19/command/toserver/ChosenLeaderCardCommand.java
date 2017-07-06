package it.polimi.ingsw.ps19.command.toserver;

import it.polimi.ingsw.ps19.server.ServerCommandHandler;

/**
 * 
 * The Class ChosenLeaderCardCommand.
 * This class represent the leader card choice made by one client
 */
/**
 * @author matteo
 *
 */
public class ChosenLeaderCardCommand extends ClientToServerCommand {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7435305980891201004L;

	/** The name. */
	private String name;

	/** The player color. */
	private String playerColor;

	/**
	 * Instantiates a new chosen leader card command.
	 *
	 * @param name the name
	 * @param playerColor the player color
	 */
	public ChosenLeaderCardCommand(String name, String playerColor) {
		this.name = name;
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

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

}
