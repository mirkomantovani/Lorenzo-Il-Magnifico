package it.polimi.ingsw.ps19.command;

public class PlaceIntoIndustrialAreaCommand extends ClientToServerCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8748964103373380495L;
	
	String familyMember;
	String industrialArea;
	
	public PlaceIntoIndustrialAreaCommand(String familyMember, String industrialArea){
		this.familyMember = familyMember;
		this.industrialArea = industrialArea;
	}
	

	@Override
	public void processCommand(ServerCommandHandler serverHandlerCommand) {
		serverHandlerCommand.applyCommand(this);
		
	}
	
	
	
	

}
