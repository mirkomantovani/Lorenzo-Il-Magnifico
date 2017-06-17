package it.polimi.ingsw.ps19.view.cli;

import java.util.List;

import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.client.ClientController;
import it.polimi.ingsw.ps19.constant.ClientConstants;
import it.polimi.ingsw.ps19.model.area.Board;
import it.polimi.ingsw.ps19.model.card.LeaderCard;
import it.polimi.ingsw.ps19.view.UserInterface;

/**
 * Comand Line Interface play mode.
 * 
 * @author Jimmy
 *
 */
public class CommandLineInterface implements UserInterface, InputListener {

	private final String welcomeMessage= "|------------------Welcome to Lorenzo il Magnifico, the board game!----------------|";
	private final String initialStatus= "The decks have been shuffled and the cards on "
			+ "the towers are as follows";	
	
	
	private InputReader reader;
	private ClientController gameController;
	private Thread inputReaderThread;
	private int readerState;
	
	
	
	public CommandLineInterface(ClientController clientController){
		this.gameController = clientController;
		reader = new InputReader();
		inputReaderThread = new Thread(reader);
		inputReaderThread.start();
		reader.addListener(this);
		print(welcomeMessage);
	}
	
	
	@Override
	public void initializeMatch() {
		print("A new game is about to start");
//		board.toString(); //o qualcosa di simile
//		player.toString();
	}

	@Override
	public void startTurn() {
			print("-------------------------------------------------------------");
			print("\nIt is now your turn, what do you want to do next?");
	}
	
	

	@Override
	public void commandNotValid() {
		printImp("Invalid command");
	}

	@Override
	public void playerStatusChange(Player p) {
		print("This is your status updated :\n");
		p.toString();
		
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


	@Override
	public void notify(String input) {
		switch (readerState) {
		case ClientConstants.SEND_NAME:
			gameController.notifyName(input);
			break;
		case ClientConstants.SEND_CHOSEN_LEADERCARD:		
			gameController.notifyChosenLeaderCard(input);
			break;
		case ClientConstants.SEND_PASSWORD:
			print("Command not recognized");
			break;
		default:
			print("Command not recognized");
			break;
		}
	}




	@Override
	public void askName() {
		print("Insert your credentials: ");
		print("Name: ");
		readerState = ClientConstants.SEND_NAME;
	}


	@Override
	public void askPassword() {
		print("Password: ");
		readerState = ClientConstants.SEND_PASSWORD;
	}


	@Override
	public void startDraft(List<LeaderCard> leaderCards) {
		print("Select a leader card from the following: ");
		if(leaderCards.size()==0)System.out.println("leader cards è 0");
		for(int i = 0; i<leaderCards.size(); i++){
			print("Number " + i + ":\n" + leaderCards.get(i).toString());
		}
		readerState = ClientConstants.SEND_CHOSEN_LEADERCARD;
	}

	

}
