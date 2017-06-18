package it.polimi.ingsw.ps19.command.toserver;

import java.util.ArrayList;

import it.polimi.ingsw.ps19.server.ServerCommandHandler;

/**
 * @author matteo
 * 
 * this class represent the client choice about the possible resources given by a council privilege
 *
 */
public class ChosenPrivilegeCommand extends ClientToServerCommand{

	/**
	 * 
	 */
	private static final long serialVersionUID = -294023756405550321L;
	
	private ArrayList<Integer> choices; //possibile values : 1 for rock and stone/5 - faithpoint/4 - militarypoint
							// 2 - servant/ 3 -coins
	
	public ChosenPrivilegeCommand(ArrayList<Integer> choices){
		this.choices = choices;
	}

	@Override
	public void processCommand(ServerCommandHandler serverHandlerCommand) {
		serverHandlerCommand.applyCommand(this);		
	}

	public ArrayList<Integer> getChoice() {
		return choices;
	}
	
	

}
