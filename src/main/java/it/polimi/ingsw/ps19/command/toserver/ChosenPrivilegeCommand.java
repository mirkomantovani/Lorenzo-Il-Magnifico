package it.polimi.ingsw.ps19.command.toserver;

import java.util.ArrayList;

import it.polimi.ingsw.ps19.server.ServerCommandHandler;

/**
 * The Class ChosenPrivilegeCommand.
 *
 * @author matteo
 * 
 * this class represent the client choice about the possible resources given by a council privilege
 */
public class ChosenPrivilegeCommand extends ClientToServerCommand{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -294023756405550321L;
	
	/** The choices. */
	private ArrayList<Integer> choices; //possibile values : 1 for rock and stone/5 - faithpoint/4 - militarypoint
							// 2 - servant/ 3 -coins
	
	/**
							 * Instantiates a new chosen privilege command.
							 *
							 * @param choices the choices
							 */
							public ChosenPrivilegeCommand(ArrayList<Integer> choices){
		this.choices = choices;
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.command.toserver.ClientToServerCommand#processCommand(it.polimi.ingsw.ps19.server.ServerCommandHandler)
	 */
	@Override
	public void processCommand(ServerCommandHandler serverHandlerCommand) {
		serverHandlerCommand.applyCommand(this);		
	}

	/**
	 * Gets the choice.
	 *
	 * @return the choice
	 */
	public ArrayList<Integer> getChoice() {
		return choices;
	}
	
	

}
