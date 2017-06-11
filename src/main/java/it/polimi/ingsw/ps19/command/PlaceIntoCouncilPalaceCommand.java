package it.polimi.ingsw.ps19.command;

public class PlaceIntoCouncilPalaceCommand extends ClientToServerCommand {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -364724017878574034L;
	String familyMember;
	String councilPalace;
	
	public PlaceIntoCouncilPalaceCommand(String familyMember,String councilPalace){
		this.familyMember = familyMember;
		this.councilPalace = councilPalace;
	}
	
	@Override
	public void processCommand(ServerCommandHandler serverCommandHandler) {
		serverCommandHandler.applyCommand(this);
		
	}

}
