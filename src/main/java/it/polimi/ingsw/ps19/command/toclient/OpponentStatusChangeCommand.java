package it.polimi.ingsw.ps19.command.toclient;

import it.polimi.ingsw.ps19.client.ClientCommandHandler;
import it.polimi.ingsw.ps19.model.Player;

/**
 * The command to notify that another player has changed its status, this message
 * brings only information that every player can see from the game board
 *
 * @author Mirko
 */
public class OpponentStatusChangeCommand extends ServerToClientCommand {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3016399299916946675L;
	
	/** Note: since it's an opponent we have to send a masked clone of the player. */
	Player maskedPlayer;

	

	/**
	 * Instantiates a new opponent status change command.
	 *
	 * @param maskedClone the masked clone
	 */
	public OpponentStatusChangeCommand(Player maskedClone) {
		this.maskedPlayer=maskedClone;
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.command.toclient.ServerToClientCommand#processCommand(it.polimi.ingsw.ps19.client.ClientCommandHandler)
	 */
	@Override
	public void processCommand(ClientCommandHandler clientCommandHandler) {
		clientCommandHandler.applyCommand(this);
	}
	
	/**
	 * Gets the masked player.
	 *
	 * @return the masked player
	 */
	public Player getMaskedPlayer() {
		return maskedPlayer;
	}

}
