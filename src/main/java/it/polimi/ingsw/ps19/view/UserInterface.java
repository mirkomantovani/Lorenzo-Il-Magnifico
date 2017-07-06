package it.polimi.ingsw.ps19.view;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.ps19.Period;
import it.polimi.ingsw.ps19.PersonalBonusTile;
import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.model.area.Board;
import it.polimi.ingsw.ps19.model.card.LeaderCard;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;

/**
 * The Interface UserInterface to be implemented by the different type of views
 *
 * @author Mirko
 */
public interface UserInterface {

	
	/**
	 * Start draft.
	 *
	 * @param leaderCards the leader cards
	 */
	public void startDraft(ArrayList<LeaderCard> leaderCards);
	
	/**
	 * Initialize match.
	 *
	 * @param numPlayers the num players
	 */
	public void initializeMatch(int numPlayers);
	
	/**
	 * Initialize turn.
	 *
	 * @param period the period
	 * @param turn the turn
	 */
	public void initializeTurn(Period period, int turn);
	
	/**
	 * Start turn.
	 */
	public void startTurn();
	
	/**
	 * Command not valid.
	 */
	public void commandNotValid();
	
	/**
	 * Player status change.
	 *
	 * @param p the p
	 */
	public void playerStatusChange(Player p);
	
	/**
	 * Notification of a generic player move.
	 */
	public void playerMove();
	
	/**
	 * It's turn!.
	 */
	public void playerTurn();
	
	/**
	 * Win.
	 */
	public void win();
	
	/**
	 * Lose.
	 */
	public void lose();

	/**
	 * Ask privilege choice.
	 *
	 * @param numberOfPrivilege the number of privilege
	 * @param privilegeResources the privilege resources
	 */
	public void AskPrivilegeChoice(int numberOfPrivilege, List<ResourceChest> privilegeResources);

	/**
	 * Ask move.
	 */
	public void askMove();
	
	/**
	 * Invalid input.
	 */
	public void invalidInput();
	
	/**
	 * Ask personal bonus tile.
	 *
	 * @param personalBonusTiles the personal bonus tiles
	 */
	public void askPersonalBonusTile(List<PersonalBonusTile> personalBonusTiles);
	
	
	/**
	 * Assign color.
	 *
	 * @param color the color
	 */
	public void assignColor(String color);

	/**
	 * Refresh board.
	 *
	 * @param board the board
	 */
	public void refreshBoard(Board board);

	/**
	 * Notify excommunication.
	 */
	public void notifyExcommunication();

	/**
	 * New chat message.
	 *
	 * @param message the message
	 */
	public void newChatMessage(String message);

	/**
	 * Ask name and password.
	 */
	public void askNameAndPassword();

	/**
	 * Ask for production exchange effect.
	 *
	 * @param choices the choices
	 */
	public void askForProductionExchangeEffect(List<String[]> choices);

	/**
	 * Notify round timer expired.
	 */
	public void notifyRoundTimerExpired();

	/**
	 * Ask for excommunication payment.
	 *
	 * @param excommunicationEffect the excommunication effect
	 */
	public void askForExcommunicationPayment(String excommunicationEffect);

	/**
	 * Opponent status changed.
	 *
	 * @param maskedPlayer the masked player
	 */
	public void opponentStatusChanged(Player maskedPlayer);
	
	/**
	 * Ask finish round or discard.
	 */
	public void askFinishRoundOrDiscard();
	
	/**
	 * Action command not valid.
	 *
	 * @param reason the reason
	 */
	public void actionCommandNotValid(String reason);

	/**
	 * Notify server closed.
	 */
	public void notifyServerClosed();


}