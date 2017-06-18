package it.polimi.ingsw.ps19.view;

import java.util.List;

public interface InputObserver {
	
	public void notifyName(String name);
	
	public void notifyPassword(String password);
	
	public void notifyChosenLeaderCard(String LeaderCardName);
	
	public void notifyMove(String move);

	public void notifyChosenPrivileges(String choices);
	
	public void notifyInvalidInput();
	
	public void notifyCouncilPalace(List<String> actioConstructor);
	
	public void notifyTakeCardAction(List<String> actionConstructor);
	
	public void notifyDiscardedLeaderCard(String dicardedLeaderCard);
	
	public void notifyMarket(List<String> actionConstructor);
	
	public void notifyHarvest(List<String> actionConstructor);
	
	public void notifyProduction(List<String> actionConstructor);
}
