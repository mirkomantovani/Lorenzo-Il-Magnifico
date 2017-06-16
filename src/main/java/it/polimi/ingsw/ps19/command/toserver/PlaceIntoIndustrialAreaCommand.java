package it.polimi.ingsw.ps19.command.toserver;

import it.polimi.ingsw.ps19.server.ServerCommandHandler;

/**
 * @author matteo
 * 
 * the client use this command to place a pawn in an industrial area (harvest or production, single or multiple one)
 *
 */
public class PlaceIntoIndustrialAreaCommand extends ClientToServerCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8748964103373380495L;
	
	private String familyMember;
	private String industrialArea; // it should be multipleHarvestArea - multipleProductionArea - singleProductionArea - singleHarvestArea
	
	public String getFamilyMember() {
		return familyMember;
	}


	public String getIndustrialArea() {
		return industrialArea;
	}


	public PlaceIntoIndustrialAreaCommand(String familyMember, String industrialArea){
		this.familyMember = familyMember;
		this.industrialArea = industrialArea;
	}
	

	@Override
	public void processCommand(ServerCommandHandler serverHandlerCommand) {
		serverHandlerCommand.applyCommand(this);
		
	}
	
	
	
	

}
