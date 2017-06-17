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

	void askName();
	
	void startDraft(List<LeaderCard> leaderCards);
	
	void askPassword();
	
	void initializeMatch();
	
	void initializeTurn(Board board, Period period, int turn);
	
	void startTurn();
	
	void commandNotValid();
	
	void playerStatusChange(Player p);
	
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

	void AskPrivilegeChoice(int numberOfPrivilege, List<ResourceChest> privilegeResources);

	void askMove();
	
	void invalidInput();
	
	void askPersonalBonusTile(ArrayList<PersonalBonusTile> personalBonusTiles);
}
