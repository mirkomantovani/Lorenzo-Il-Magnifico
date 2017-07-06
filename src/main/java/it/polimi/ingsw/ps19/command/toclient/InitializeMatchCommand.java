package it.polimi.ingsw.ps19.command.toclient;

import it.polimi.ingsw.ps19.client.ClientCommandHandler;

/**
 * @author matteo
 * The command that the match has started
 *
 */
public class InitializeMatchCommand extends ServerToClientCommand{

	
	int numPlayers;
	/**
	 * 
	 */
	private static final long serialVersionUID = -5791396313072706548L;
	


	public InitializeMatchCommand(int numPlayers) {
		this.numPlayers=numPlayers;
	}



	@Override
	public void processCommand(ClientCommandHandler clientCommandHandler) {
		clientCommandHandler.applyCommand(this);
		
	}



	public int getNumPlayers() {
		return numPlayers;
	}
	
	
	
	

}
