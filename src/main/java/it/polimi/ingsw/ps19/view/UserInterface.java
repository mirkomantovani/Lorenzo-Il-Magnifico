package it.polimi.ingsw.ps19.view;

import java.util.List;

import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.model.area.Board;
import it.polimi.ingsw.ps19.model.card.LeaderCard;

/**
 * @author Mirko
 *
 */
public interface UserInterface {

	void askName();
	
	void startDraft(List<LeaderCard> leaderCards);
	
	void askPassword();
	
	void initializeMatch();
	
	void initializeTurn();
	
	void startTurn();
	
	void commandNotValid();
	
	void playerStatusChange();
	
	/**
	 * Notification of a generic player move
	 */
	void playerMove();
	
	/**
	 * It's <player> turn!
	 */
	void playerTurn();
	
	void win();
	
	void lose();

	void AskPrivilegeChoice(int numberOfPrivilege);
}
