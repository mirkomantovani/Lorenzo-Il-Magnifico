package it.polimi.ingsw.ps19.command.toserver;


import it.polimi.ingsw.ps19.server.ServerCommandHandler;

/**
 * @author matteo
 * the command to place a pawn into one of the market areas
 *
 */
public class PlaceIntoMarketCommand extends ClientToServerCommand{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5666906861277528755L;
	
	private String familyMember; // orange,black,white,neutral
	private String actionSpace; // FIRST,SECOND,THIRD,FOURTH as the place of the marker spot from the left to the
								// right on the board
	private int paidServants;

	public PlaceIntoMarketCommand(String familyMember, String actionSpace, int paidServants){
		this.familyMember = familyMember;
		this.actionSpace = actionSpace;
		this.paidServants = paidServants;
	}
	
	@Override
	public void processCommand(ServerCommandHandler serverCommandHandler) {
		serverCommandHandler.applyCommand(this);
		
	}

	public String getFamilyMember() {
		return familyMember;
	}

	public String getActionSpace() {
		return actionSpace;
	}

	public int getPaidServants() {
		return paidServants;
	}
	
	
	

}
