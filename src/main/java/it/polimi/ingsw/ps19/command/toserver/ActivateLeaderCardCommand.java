package it.polimi.ingsw.ps19.command.toserver;

import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.exception.NotApplicableException;
import it.polimi.ingsw.ps19.server.ServerCommandHandler;

/**
 * The Class ActivateLeaderCardCommand.
 *
 * @author matteo
 * this class notify the server about the client intention to activate a leader effect
 */
public class ActivateLeaderCardCommand extends ClientToServerCommand{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5734000768457689241L;
	
	/** The leader name. */
	private String leaderName;
	
	/** The player. */
	private String player;
	
	/**
	 * Instantiates a new activate leader card command.
	 *
	 * @param leaderName the leader name
	 * @param player the player
	 */
	public ActivateLeaderCardCommand(String leaderName, String player){
		this.leaderName = leaderName;
		this.player = player;
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.command.toserver.ClientToServerCommand#processCommand(it.polimi.ingsw.ps19.server.ServerCommandHandler)
	 */
	@Override
	public void processCommand(ServerCommandHandler serverHandlerCommand) {
		try {
			serverHandlerCommand.applyCommand(this);
		} catch (NotApplicableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * Gets the leader name.
	 *
	 * @return the leader name
	 */
	public String getLeaderName() {
		return leaderName;
	}
	
	/**
	 * Gets the player.
	 *
	 * @return the player
	 */
	public String getPlayer(){
		return player;
	}
	

}
