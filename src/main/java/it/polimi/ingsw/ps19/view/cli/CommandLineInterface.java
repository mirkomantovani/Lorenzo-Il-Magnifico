package it.polimi.ingsw.ps19.view.cli;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.ps19.Period;
import it.polimi.ingsw.ps19.PersonalBonusTile;
import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.client.ClientController;
import it.polimi.ingsw.ps19.constant.ClientConstants;
import it.polimi.ingsw.ps19.model.area.Board;
import it.polimi.ingsw.ps19.model.card.LeaderCard;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;
import it.polimi.ingsw.ps19.model.resource.ResourceType;
import it.polimi.ingsw.ps19.view.UserInterface;

/**
 * Command Line Interface play mode.
 * 
 * @author Jimmy
 *
 */
public class CommandLineInterface implements UserInterface, InputListener {

	private final String welcomeMessage = "|------------------Welcome to Lorenzo il Magnifico, the board game!----------------|";

	private InputReader reader;
	private ClientController gameController;
	private Thread inputReaderThread;
	private int readerState;
	private int moveState;
	private int takeCardState;
	private ArrayList<String> actionConstructor;

	public CommandLineInterface(ClientController clientController) {
		this.gameController = clientController;
		actionConstructor=new ArrayList<String>();
		reader = new InputReader();
		inputReaderThread = new Thread(reader);
		inputReaderThread.start();
		reader.addListener(this);
		print(welcomeMessage);
	}

	@Override
	public void initializeMatch() {
		print("A new game is about to start");
		// board.toString(); //o qualcosa di simile
		// player.toString();
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

		System.out.println("\nCLI: lunghezza hashmap leadercards: "+p.getLeaderCards().size());
		
		
		
		System.out.println("stampo player to string:");
		print(p.toString());

	}

	@Override
	public void playerMove() {

	}

	@Override
	public void win() {
		print("CONGRATULATIONS! You won the game!\nPress any key to exit the game");
		readerState = ClientConstants.SEND_END_GAME;
	}

	/*
	 * This is just provisional, we will adopt another mechanism
	 */
	@Override
	public void lose() {
		print("OOPS, You lost. Try again, next time you will be luckier!\nPress any key to exit the game");
		readerState = ClientConstants.SEND_END_GAME;
	}

	@Override
	public void playerTurn() {

	}

	private void print(String s) {
		System.out.println(s);

	}

	private void printImp(String s) {
		System.out.println("¤¤¤  " + s.toUpperCase() + "  ¤¤¤");
	}

	@Override
	public void initializeTurn(Period period, int turn) {
		printImp("New turn");
		print("Period: " + period.toString() + "\tTurn: " + turn);
	}
	
	@Override
	public void refreshBoard(Board board){
		print("Board status:");
		print(board.toString());
	}

	@Override
	public void AskPrivilegeChoice(int numberOfPrivilege, ArrayList<ResourceChest> privilegeResources) {
		print("Choose " + numberOfPrivilege
				+ " different resources from the following (Please enter the numbers associated with your choices separated by commas, e.g. x,y,z, if you have more than one privilege to choose):");
		for (int i = 0; i < privilegeResources.size(); i++) {
			print("Number " + i + ":\n" + privilegeResources.get(i).toString() + "\n");
		}

		readerState = ClientConstants.SEND_PRIVILEGE_CHOICES;
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
		case ClientConstants.SEND_MOVE:
			if(input.equals("2")){
				print("Select a leader card to discard (insert its name): ");
				readerState = ClientConstants.SEND_DISCARDED_LEADER_CARD;
			}
			else
				moveHandler(input);
			break;
		case ClientConstants.SEND_PASSWORD:
			print("Command not recognized");
			break;
		case ClientConstants.SEND_PRIVILEGE_CHOICES:
			gameController.notifyChosenPrivileges(input);
			break;
		case ClientConstants.SEND_DISCARDED_LEADER_CARD:
			gameController.notifyDiscardedLeaderCard(input);
			break;
		case ClientConstants.SEND_MARKET_SLOT:
			actionConstructor.add(input);
			gameController.notifyMarket(actionConstructor);
			break;
		case ClientConstants.SEND_HARVEST_ACTION_SPACE:
			actionConstructor.add(input);
			gameController.notifyHarvest(actionConstructor);
			break;
		case ClientConstants.SEND_PRODUCTION_ACTION_SPACE:
			actionConstructor.add(input);
			gameController.notifyProduction(actionConstructor);
			break;
		default:
			print("Command not recognized");
			break;
		}
	}


	@Override
	public void startDraft(ArrayList<LeaderCard> leaderCards) {
		print("Select a leader card from the following: ");
		if (leaderCards.size() == 0)
			System.out.println("leader cards è 0");
		for (int i = 0; i < leaderCards.size(); i++) {
			print("Number " + i + ":\n" + leaderCards.get(i).toString());
		}
		readerState = ClientConstants.SEND_CHOSEN_LEADERCARD;
	}

	@Override
	public void askMove() {
		moveState=0;
		takeCardState=0;
		actionConstructor.clear();
		print("Choose what you want to do:");
		print("1- Action");
		print("2- Discard leader card");
		readerState = ClientConstants.SEND_MOVE;
	}
	
	private void takeCardParams(String input){
		switch(takeCardState){
		case 0:
			print("Select the tower:");
			print("1 - Territory");
			print("2 - Building");
			print("3 - Character");
			print("4 - Venture");
			takeCardState=ClientConstants.SEND_TAKE_CARD_TOWER;
			break;
		case ClientConstants.SEND_TAKE_CARD_TOWER:
			actionConstructor.add(input);
			print("Select the floor:");
			print("1 - First floor");
			print("2 - Second floor");
			print("3 - Third floor");
			print("4 - Fourth floor");
			takeCardState = ClientConstants.SEND_TAKE_CARD_FLOOR;
			break;
		case ClientConstants.SEND_TAKE_CARD_FLOOR:
			actionConstructor.add(input);
			gameController.notifyTakeCardAction(actionConstructor);
			break;
		}
		
	}
	
	private void distinguishAction(String string, String takecard){
		switch(string){
		case "1": 
			takeCardParams(takecard);
			break;
		case "2":	
			print("Select market slot:");
			print("1 - First marketplace slot");
			print("2 - Second marketplace slot ");
			print(".. And so on ..");
			readerState = ClientConstants.SEND_MARKET_SLOT;
			break;
		case "3":
			gameController.notifyCouncilPalace(actionConstructor);
			break;
		case "4":
			print("Select action space: ");
			print("1 - Single action space");
			print("2 - Multiple action space");
			readerState  = ClientConstants.SEND_HARVEST_ACTION_SPACE;
			break;
		case "5": 
			print("Select action space: ");
			print("1 - Single action space");
			print("2 - Multiple action space");
			readerState = ClientConstants.SEND_PRODUCTION_ACTION_SPACE;
			break;
			default: 
				gameController.notifyInvalidInput();
			
		}
	}

	private void moveHandler(String string) {
		actionConstructor = new ArrayList<String>();
		actionConstructor.add(string);
		
		switch (moveState) {
		case 0:  //First possible case
			print("Select your available family member: ");
			moveState=ClientConstants.SEND_FAMILY_MEMBER;
			break;
		case ClientConstants.SEND_FAMILY_MEMBER:
			actionConstructor.add(string);
			print("How many servants do you want to pay to raise your selected family member action value?");
			moveState=ClientConstants.SEND_PAID_SERVANTS;
			break;
		case ClientConstants.SEND_PAID_SERVANTS:
			actionConstructor.add(string);
			print("What action do you want to perform?");
			print("3 - Take card");
			print("4 - Place into Council Palace");
			print("5 - Place into Marketplace slot");
			print("6 - Harvest");
			print("7 - Product");
			moveState=ClientConstants.SEND_CHOSEN_ACTION;
			break;
		case ClientConstants.SEND_CHOSEN_ACTION:
			actionConstructor.add(string);
			distinguishAction(actionConstructor.get(2),string);
			break;
		}
	}


	@Override
	public void invalidInput() {
		printImp("Invalid Input");
	}

	@Override
	public void askPersonalBonusTile(ArrayList<PersonalBonusTile> personalBonusTiles) {
		// TODO Auto-generated method stub

	}

	@Override
	public void displayOpponentsStatus(Player player) {
		print(player.getName() + " with color " + player.getColor() + " has changed his status: ");
		print("Victory points: " + player.getResourceChest().getResourceInChest(ResourceType.VICTORYPOINT));
		print("Faith points: " + player.getResourceChest().getResourceInChest(ResourceType.FAITHPOINT));
		print("Military points: " + player.getResourceChest().getResourceInChest(ResourceType.MILITARYPOINT));
		print("");
	}

	@Override
	public void assignColor(String color) {
		print("Ti è stato assegnato il colore: "+color);
		gameController.setPlayerColor(color);
	}

	public void notApplicableAction() {
		print("The action chosen is not applicable, please select another one!");
		
	}

	@Override
	public void notifyExcommunication() {
		print("God seems very offended by your behaviour, he established your excommunication.");
		
	}

	@Override
	public void opponentStatusChanged(Player maskedPlayer) {
		print("The ");
		print(maskedPlayer.getColor());
		print(" has changed his status :\nNew details: ");
		print(maskedPlayer.getResourceChest().toString());
		
	}

	@Override
	public void newChatMessage(String message) {
		print(message);
		
	}

	@Override
	public void askNameAndPassword() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void askForProductionExchangeEffect(ArrayList<String[]> choices) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyRoundTimerExpired() {
		print("Your time to move is elapsed, you have lost the turn.");
		
	}

	@Override
	public void askForExcommunicationPayment(String excommunicationEffect) {
		// TODO Auto-generated method stub
		
	}

}
