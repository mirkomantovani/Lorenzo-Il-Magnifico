package it.polimi.ingsw.ps19.command;

import it.polimi.ingsw.ps19.FamilyMember;
import it.polimi.ingsw.ps19.model.area.SingleActionSpace;
import it.polimi.ingsw.ps19.server.ServerCommandHandler;

public class PlaceIntoMarketCommand extends ClientToServerCommand{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5666906861277528755L;
	
	FamilyMember familyMember;
	SingleActionSpace actionSpace;

	public PlaceIntoMarketCommand(FamilyMember familyMember, SingleActionSpace actionSpace){
		this.familyMember = familyMember;
		this.actionSpace = actionSpace;
	}
	
	@Override
	public void processCommand(ServerCommandHandler serverCommandHandler) {
		serverCommandHandler.applyCommand(this);
		
	}
	
	
	

}
