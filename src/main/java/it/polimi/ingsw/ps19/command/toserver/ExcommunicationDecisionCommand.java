package it.polimi.ingsw.ps19.command.toserver;

import it.polimi.ingsw.ps19.server.ServerCommandHandler;

/**
 * @author matteo
 * 
 * this class represent the client answer about the possible decision to pay to prevent excommunication
 *
 */
/**
 * @author matteo
 *
 */
public class ExcommunicationDecisionCommand extends ClientToServerCommand{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2379068517465682578L;
	
	private String decision;
	
	public ExcommunicationDecisionCommand(String decision){
		this.decision = decision;
	}

	@Override
	public void processCommand(ServerCommandHandler serverHandlerCommand) {
		serverHandlerCommand.applyCommand(this);
	}

	public String getDecision() {
		return decision;
	}
	
	
	

}
