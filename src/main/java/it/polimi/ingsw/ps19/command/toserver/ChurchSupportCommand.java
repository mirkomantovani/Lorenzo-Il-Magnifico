package it.polimi.ingsw.ps19.command.toserver;

import it.polimi.ingsw.ps19.server.ServerCommandHandler;

/**
 * The Class ChurchSupportCommand.
 *
 * @author matteo
 * 
 * this class represent the client answer about the possible decision to pay to prevent excommunication
 * 
 * 
 * true if you want to support, false otherwise
 */
/**
 * @author matteo
 *
 */
public class ChurchSupportCommand extends ClientToServerCommand{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2379068517465682578L;
	
	/** The decision. */
	private boolean decision;
	
	/** The player color. */
	private String playerColor;
	
	

	/**
	 * Instantiates a new church support command.
	 *
	 * @param playerColor the player color
	 * @param decision the decision
	 */
	public ChurchSupportCommand(String playerColor, boolean decision){
		this.playerColor=playerColor;
		this.decision = decision;
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.command.toserver.ClientToServerCommand#processCommand(it.polimi.ingsw.ps19.server.ServerCommandHandler)
	 */
	@Override
	public void processCommand(ServerCommandHandler serverHandlerCommand) {
		serverHandlerCommand.applyCommand(this);
	}

	/**
	 * Gets the decision.
	 *
	 * @return the decision
	 */
	public boolean getDecision() {
		return decision;
	}
	
	/**
	 * Gets the player color.
	 *
	 * @return the player color
	 */
	public String getPlayerColor() {
		return playerColor;
	}
	
	
	

}
