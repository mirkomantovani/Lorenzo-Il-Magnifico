package it.polimi.ingsw.ps19.view;

public interface InputObserver {
	
	public void notifyName(String name);
	
	public void notifyPassword(String password);
	
	public void notifyChosenLeaderCard(String LeaderCardName);
	
	public void notifyMove(String move);

	public void notifyChosenPrivileges(String choices);
}
