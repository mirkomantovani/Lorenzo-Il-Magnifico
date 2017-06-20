package it.polimi.ingsw.ps19.view;

import java.util.ArrayList;
import java.util.List;

public interface InputObserver {
	
	public void notifyChosenLeaderCard(String LeaderCardName);
	
	public void notifyMove(String move);

	public void notifyChosenPrivileges(String choices);
	
	public void notifyInvalidInput();
	
	public void notifyCouncilPalace(ArrayList<String> actioConstructor);
	
	public void notifyTakeCardAction(ArrayList<String> actionConstructor);
	
	public void notifyDiscardedLeaderCard(String dicardedLeaderCard);
	
	public void notifyMarket(ArrayList<String> actionConstructor);
	
	public void notifyHarvest(ArrayList<String> actionConstructor);
	
	public void notifyProduction(ArrayList<String> actionConstructor);
	
	public void notifyCredentials(ArrayList<String> actionConstructor);
}