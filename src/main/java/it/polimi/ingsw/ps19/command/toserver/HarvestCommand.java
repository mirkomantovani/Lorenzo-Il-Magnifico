package it.polimi.ingsw.ps19.command.toserver;

import it.polimi.ingsw.ps19.server.ServerCommandHandler;

/**
 * The Class HarvestCommand.
 * This class manages the client decision to make an harvest action
 */
/**
 * @author matteo
 *
 */
public class HarvestCommand extends ClientToServerCommand {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7415867579709431260L;
	
	/** The family member. */
	private String familyMember;
	
	/** The paid servants. */
	private int paidServants;
	
	/** The action space. */
	private int actionSpace;
	
	/**
	 * Instantiates a new harvest command.
	 *
	 * @param familyMember the family member
	 * @param paidServants the paid servants
	 * @param actionSpace the action space
	 */
	public HarvestCommand(String familyMember, int paidServants, int actionSpace){
		this.familyMember = familyMember;
		this.paidServants = paidServants;
		this.actionSpace = actionSpace;
	}
	
	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.command.toserver.ClientToServerCommand#processCommand(it.polimi.ingsw.ps19.server.ServerCommandHandler)
	 */
	@Override
	public void processCommand(ServerCommandHandler serverHandlerCommand) {
		serverHandlerCommand.applyCommand(this);
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

	/**
	 * Gets the action space.
	 *
	 * @return the action space
	 */
	public int getActionSpace() {
		return actionSpace;
	}

	
	
}
