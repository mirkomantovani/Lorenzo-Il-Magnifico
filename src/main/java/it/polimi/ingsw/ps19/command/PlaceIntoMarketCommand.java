package it.polimi.ingsw.ps19.command;

import it.polimi.ingsw.ps19.FamilyMember;
import it.polimi.ingsw.ps19.model.area.SingleActionSpace;

public class PlaceIntoMarketCommand extends ClientToServerCommand{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5666906861277528755L;
	
	String familyMember;
	String actionSpace;

	public PlaceIntoMarketCommand(String familyMember, String actionSpace){
		this.familyMember = familyMember;
		this.actionSpace = actionSpace;
	}
	
	@Override
	public void processCommand(ServerCommandHandler serverCommandHandler) {
		serverCommandHandler.applyCommand(this);
		
	}
	
	
	

}
