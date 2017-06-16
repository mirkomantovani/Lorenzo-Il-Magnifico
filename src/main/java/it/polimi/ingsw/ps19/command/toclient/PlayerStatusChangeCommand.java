package it.polimi.ingsw.ps19.command.toclient;

import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.client.ClientCommandHandler;

/**
 * This command has to be sent only to the client representing the same player as the one in the field
 * @author Mirko
 *
 */
public class PlayerStatusChangeCommand extends ServerToClientCommand {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 8622584838091071912L;
	
	private Player player;



	public PlayerStatusChangeCommand(Player player) {
		this.player=player;
	}

	@Override
	public void processCommand(ClientCommandHandler clientCommandHandler) {
		// TODO Auto-generated method stub

	}
	
	public Player getPlayer() {
		return player;
	}

}
