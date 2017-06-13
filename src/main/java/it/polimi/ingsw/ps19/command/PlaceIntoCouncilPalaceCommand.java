package it.polimi.ingsw.ps19.command;

import it.polimi.ingsw.ps19.server.ServerCommandHandler;

public class PlaceIntoCouncilPalaceCommand extends ClientToServerCommand {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -364724017878574034L;
	
	private String familyMember;
	private String councilPalace;
	
	public PlaceIntoCouncilPalaceCommand(String familyMember,String councilPalace){
		this.familyMember = familyMember;
		this.councilPalace = councilPalace;
	}
	
	@Override
	public void processCommand(ServerCommandHandler serverCommandHandler) {
		serverCommandHandler.applyCommand(this);
		
	}

	public String getFamilyMember() {
		return familyMember;
	}

	public String getCouncilPalace() {
		return councilPalace;
	}

}
