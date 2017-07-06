package it.polimi.ingsw.ps19.command.toserver;

import it.polimi.ingsw.ps19.server.ServerCommandHandler;

/**
 * This command was needed to treat in a different way the production and its relative
 * resource exchange choices.
 *
 * @author Mirko
 */
public class ProductionCommand extends ClientToServerCommand {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1936330748404906687L;
	
	/** The family member. */
	private String familyMember;
	
	/** The paid servants. */
	private int paidServants;
	
	/** The action space. */
	private int actionSpace;
	


	
	/**
	 * Instantiates a new production command.
	 *
	 * @param familyMember the family member
	 * @param paidServants the paid servants
	 * @param actionSpace the action space
	 */
	public ProductionCommand(String familyMember, int paidServants, int actionSpace){
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
	public String getFamilyMember(){
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
