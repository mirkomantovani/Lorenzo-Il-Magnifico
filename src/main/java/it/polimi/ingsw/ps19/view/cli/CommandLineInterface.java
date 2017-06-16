package it.polimi.ingsw.ps19.view.cli;

import it.polimi.ingsw.ps19.view.UserInterface;

public class CommandLineInterface implements UserInterface {

	private final String welcomeMessage= "Welcome to the Lorenzo il Magnifico board game"
			+ " the game has just started";
	private final String initialStatus= "The decks have been shuffled and the cards on "
			+ "the towers are as follows";
	
	
	@Override
	public void initializeMatch() {
		print(welcomeMessage);
		print(initialStatus);
		
	}

	@Override
	public void startTurn() {
		
	}
	
	

	@Override
	public void commandNotValid() {
		
	}

	@Override
	public void playerStatusChange() {
		
	}

	@Override
	public void playerMove() {
		
	}

	@Override
	public void win() {
		
	}

	@Override
	public void lose() {
		
	}
	
	@Override
	public void playerTurn() {
		
	}
	
	private void print(String s){
		System.out.println(s);
		
	}
	
    private void printImp(String s){
		System.out.println("¤¤¤"+s.toUpperCase()+"¤¤¤");
	}

	@Override
	public void initializeTurn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void AskPrivilegeChoice(int numberOfPrivilege) {
		// TODO Auto-generated method stub
		
	}

	

}
