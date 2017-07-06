package it.polimi.ingsw.ps19.command.toserver;

import it.polimi.ingsw.ps19.server.ServerCommandHandler;

/**
 * The Class PlaceIntoCouncilPalaceCommand.
 *
 * @author matteo
 * 
 * the client use this command to place a pawn in the council palace
 */
public class PlaceIntoCouncilPalaceCommand extends ClientToServerCommand {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -364724017878574034L;
	
	/** The family member. */
	private String familyMember; //the color
	
	/** The paid servants. */
	private int paidServants;
	
	
	/**
	 * Instantiates a new place into council palace command.
	 *
	 * @param familyMember the family member
	 * @param paidServants the paid servants
	 */
	public PlaceIntoCouncilPalaceCommand(String familyMember, int paidServants){
		this.familyMember = familyMember; 
		this.paidServants = paidServants;
	}
	
	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.command.toserver.ClientToServerCommand#processCommand(it.polimi.ingsw.ps19.server.ServerCommandHandler)
	 */
	@Override
	public void processCommand(ServerCommandHandler serverCommandHandler) {
		serverCommandHandler.applyCommand(this);
		
	}

	/**
	 * Gets the family member.
	 *
	 * @return the family member
	 */
	public String getFamilyMember() {
		return familyMember;
	}

	/**
	 * Gets the paid servants.
	 *
	 * @return the paid servants
	 */
	public int getPaidServants() {
		return paidServants;
	}


}
