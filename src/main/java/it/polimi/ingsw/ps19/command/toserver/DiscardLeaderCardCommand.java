package it.polimi.ingsw.ps19.command.toserver;

import it.polimi.ingsw.ps19.server.ServerCommandHandler;

/**
 * The Class DiscardLeaderCardCommand.
 *
 * @author matteo
 * this class manage the possibilty to discard a leader card during the game. 
 */
public class DiscardLeaderCardCommand extends ClientToServerCommand{ 
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 59970063367278274L;
	
	/** The leader name. */
	private String leaderName;

	/**
	 * Instantiates a new discard leader card command.
	 *
	 * @param leaderName the leader name
	 */
	public DiscardLeaderCardCommand(String leaderName){
		this.leaderName = leaderName;
	}
	
	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.command.toserver.ClientToServerCommand#processCommand(it.polimi.ingsw.ps19.server.ServerCommandHandler)
	 */
	@Override
	public void processCommand(ServerCommandHandler serverHandlerCommand) {
		serverHandlerCommand.applyCommand(this);
		
	}

	/**
	 * Gets the leader name.
	 *
	 * @return the leader name
	 */
	public String getLeaderName() {
		return leaderName;
	}
	
	

}
