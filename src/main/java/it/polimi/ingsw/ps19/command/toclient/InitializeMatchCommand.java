package it.polimi.ingsw.ps19.command.toclient;

import it.polimi.ingsw.ps19.client.ClientCommandHandler;

/**
 * The Class InitializeMatchCommand.
 *
 * The class to notify the client that a match has been intialized
 * @author matteo
 * The command that the match has started
 */
public class InitializeMatchCommand extends ServerToClientCommand{

	
	/** The num players. */
	int numPlayers;
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5791396313072706548L;
	


	/**
	 * Instantiates a new initialize match command.
	 *
	 * @param numPlayers the num players
	 */
	public InitializeMatchCommand(int numPlayers) {
		this.numPlayers=numPlayers;
	}



	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.command.toclient.ServerToClientCommand#processCommand(it.polimi.ingsw.ps19.client.ClientCommandHandler)
	 */
	@Override
	public void processCommand(ClientCommandHandler clientCommandHandler) {
		clientCommandHandler.applyCommand(this);
		
	}



	/**
	 * Gets the num players.
	 *
	 * @return the num players
	 */
	public int getNumPlayers() {
		return numPlayers;
	}
	
	
	
	

}
