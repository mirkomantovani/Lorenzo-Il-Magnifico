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
 * @author Mirko
 *
 */
public interface UserInterface {

	
	public void startDraft(ArrayList<LeaderCard> leaderCards);
	
	public void initializeMatch();
	
	public void initializeTurn(Period period, int turn);
	
	public void startTurn();
	
	public void commandNotValid();
	
	public void playerStatusChange(Player p);
	
	/**
	 * Notification of a generic player move
	 */
	public void playerMove();
	
	/**
	 * It's <player> turn!
	 */
	public void playerTurn();
	
	public void win();
	
	public void lose();

	public void AskPrivilegeChoice(int numberOfPrivilege, List<ResourceChest> privilegeResources);

	public void askMove();
	
	public void invalidInput();
	
	public void askPersonalBonusTile(List<PersonalBonusTile> personalBonusTiles);
	
	public void displayOpponentsStatus(Player player);
	
	public void assignColor(String color);

	public void refreshBoard(Board board);

	public void notApplicableAction();

	public void notifyExcommunication();

	public void newChatMessage(String message);

	public void askNameAndPassword();

	public void askForProductionExchangeEffect(List<String[]> choices);

	public void notifyRoundTimerExpired();

	public void askForExcommunicationPayment(String excommunicationEffect);

	public void opponentStatusChanged(Player maskedPlayer);
	
	public void askFinishRoundOrDiscard();

}