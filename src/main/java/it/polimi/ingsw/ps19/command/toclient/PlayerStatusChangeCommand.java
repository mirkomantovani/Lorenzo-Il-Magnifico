package it.polimi.ingsw.ps19.command.toclient;

import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.client.ClientCommandHandler;

/**
 * This command has to be sent only to the client representing the same player as the one in the field.
 *
 * @author Mirko
 */
public class PlayerStatusChangeCommand extends ServerToClientCommand {

	

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8622584838091071912L;
	
	/** The player. */
	private Player player;



	/**
	 * Instantiates a new player status change command.
	 *
	 * @param player the player
	 */
	public PlayerStatusChangeCommand(Player player) {
		this.player=player;
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.command.toclient.ServerToClientCommand#processCommand(it.polimi.ingsw.ps19.client.ClientCommandHandler)
	 */
	@Override
	public void processCommand(ClientCommandHandler clientCommandHandler) {
		clientCommandHandler.applyCommand(this);
	}
	
	/**
	 * Gets the player.
	 *
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

}
