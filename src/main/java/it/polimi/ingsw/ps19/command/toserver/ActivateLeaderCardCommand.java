package it.polimi.ingsw.ps19.command.toserver;

import it.polimi.ingsw.ps19.server.ServerCommandHandler;

/**
 * @author matteo
 * this class notify the server about the client intention to activate a leader effect
 *
 */
public class ActivateLeaderCardCommand extends ClientToServerCommand{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5734000768457689241L;
	
	private String leaderName;
	
	public ActivateLeaderCardCommand(String leaderName){
		this.leaderName = leaderName;
	}

	@Override
	public void processCommand(ServerCommandHandler serverHandlerCommand) {
		serverHandlerCommand.applyCommand(this);
		
	}

	public String getLeaderName() {
		return leaderName;
	}
	
	

}
