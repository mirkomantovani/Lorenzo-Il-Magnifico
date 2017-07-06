package it.polimi.ingsw.ps19.command.toserver;

import it.polimi.ingsw.ps19.model.card.CardType;
import it.polimi.ingsw.ps19.server.ServerCommandHandler;

/**
 * The Class TakeCardCommand.
 *
 * @author matteo
 * the client uses this command to take a card from the game board and to place a pawn in the related floor
 */
public class TakeCardCommand extends ClientToServerCommand {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2549395717779803669L;

	/** The family member. */
	private String familyMember;
	
	/** The floor. */
	private int floor;
	
	/** The card type. */
	private CardType cardType;
	
	/** The paid servants. */
	private int paidServants;

	/**
	 * Instantiates a new take card command.
	 *
	 * @param familyMember the family member
	 * @param floor the floor
	 * @param paidServants the paid servants
	 * @param cardType the card type
	 */
	public TakeCardCommand(String familyMember, int floor, int paidServants, CardType cardType) {
		this.familyMember = familyMember;
		this.floor = floor;
		this.paidServants = paidServants;
		this.cardType = cardType;
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
	 * Gets the floor.
	 *
	 * @return the floor
	 */
	public int getFloor() {
		return floor;
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
	 * Gets the card type.
	 *
	 * @return the card type
	 */
	public CardType getCardType() {
		return cardType;
	}
	
}
