package it.polimi.ingsw.ps19.command.toserver;


import it.polimi.ingsw.ps19.exception.NotApplicableException;
import it.polimi.ingsw.ps19.server.ServerCommandHandler;

/**
 * The Class PlaceIntoMarketCommand.
 *
 * @author matteo
 * the command to place a pawn into one of the market areas
 */
public class PlaceIntoMarketCommand extends ClientToServerCommand{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5666906861277528755L;
	
	/** The family member. */
	private String familyMember; // orange,black,white,neutral
	
	/** The action space. */
	private String actionSpace; // FIRST,SECOND,THIRD,FOURTH as the place of the marker spot from the left to the
								// right on the board **Mirko**: NO!! whoever wrote this and
	//then used 1,2,3,4 in thre actionSpace string made me lose 1 hour understanding why it 
	//didnt't work on the GUI, it's 1,2,3,4 at this point
	
	
	/** The paid servants. */
								private int paidServants;

	/**
	 * Instantiates a new place into market command.
	 *
	 * @param familyMember the family member
	 * @param actionSpace the action space
	 * @param paidServants the paid servants
	 */
	public PlaceIntoMarketCommand(String familyMember, String actionSpace, int paidServants){
		this.familyMember = familyMember;
		this.actionSpace = actionSpace;
		this.paidServants = paidServants;
	}
	
	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.command.toserver.ClientToServerCommand#processCommand(it.polimi.ingsw.ps19.server.ServerCommandHandler)
	 */
	@Override
	public void processCommand(ServerCommandHandler serverCommandHandler) {
		try {
			serverCommandHandler.applyCommand(this);
		} catch (NotApplicableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
	 * Gets the action space.
	 *
	 * @return the action space
	 */
	public String getActionSpace() {
		return actionSpace;
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
