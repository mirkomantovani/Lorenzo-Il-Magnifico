package it.polimi.ingsw.ps19.command;

import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.client.ClientCommandHandler;

public class PlayerStatusChangeCommand extends ServerToClientCommand{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2275358822151695331L;
	
	private Player player;
	
	public PlayerStatusChangeCommand(Player player){
		this.player = player;
	}

	@Override
	public void processCommand(ClientCommandHandler clientCommandHandler) {
		// TODO Auto-generated method stub
		
	}
	
	

}
