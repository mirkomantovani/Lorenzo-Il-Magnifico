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
import it.polimi.ingsw.ps19.view.UserInterface;

/**
 * Command Line Interface play mode.
 * 
 * @author Jimmy
 *
 */
public class CommandLineInterface implements UserInterface, InputListener {

	/** The welcome message. */
	private final String welcomeMessage = "|------------------------------Welcome to Lorenzo il Magnifico, the board game!----------------------------|";


	/** The reader. */
	private InputReader reader;
	
	/** The game controller. */
	private ClientController gameController;
	
	/** The input reader thread. */
	private Thread inputReaderThread;
	
	/** The reader state. */
	private int readerState;
	
	/** The move state. */
	private int moveState;
	
	/** The take card state. */
	private int takeCardState;
	
	/** The action constructor. */
	private ArrayList<String> actionConstructor;

	/**
	 * Instantiates a new command line interface.
	 *
	 * @param clientController the client controller
	 */
	public CommandLineInterface(ClientController clientController) {
		this.gameController = clientController;
		actionConstructor=new ArrayList<String>();
		reader = new InputReader();
		inputReaderThread = new Thread(reader);
		inputReaderThread.start();
		reader.addListener(this);
		print(welcomeMessage);
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.UserInterface#initializeMatch(int)
	 */
	@Override
	public void initializeMatch(int numPlayers) {
		print("A new game is about to start!");
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.UserInterface#startTurn()
	 */
	@Override
	public void startTurn() {
		print("-------------------------------------------------------------");
		print("\nIt is now your turn, what do you want to do next?");
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.UserInterface#playerStatusChange(it.polimi.ingsw.ps19.Player)
	 */
	@Override
	public void playerStatusChange(Player p) {
		print("This is your status updated :\n");
		print(p.toString());

	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.UserInterface#playerMove()
	 */
	@Override
	public void playerMove() {

	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.UserInterface#win()
	 */
	@Override
	public void win() {
		print("CONGRATULATIONS! You won the game!\nPress any key to exit the game");
		readerState = ClientConstants.SEND_END_GAME;
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.UserInterface#lose()
	 */
	/*
	 * This is just provisional, we will adopt another mechanism
	 */
	@Override
	public void lose() {
		print("OOPS, You lost. Try again, next time you will be luckier!\nPress any key to exit the game");
		readerState = ClientConstants.SEND_END_GAME;
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.UserInterface#playerTurn()
	 */
	@Override
	public void playerTurn() {

	}

	/**
	 * Prints the.
	 *
	 * @param s the s
	 */
	private void print(String s) {
		System.out.println(s);

	}

	/**
	 * Prints the imp.
	 *
	 * @param s the s
	 */
	private void printImp(String s) {
		System.out.println("¤¤¤  " + s.toUpperCase() + "  ¤¤¤");
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.UserInterface#initializeTurn(it.polimi.ingsw.ps19.Period, int)
	 */
	@Override
	public void initializeTurn(Period period, int turn) {
		printImp("New turn");
		print("Period: " + period.toString() + "\tTurn: " + turn);
	}
	
	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.UserInterface#refreshBoard(it.polimi.ingsw.ps19.model.area.Board)
	 */
	@Override
	public void refreshBoard(Board board){
		print("Board status:");
		print(board.toString());
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.UserInterface#AskPrivilegeChoice(int, java.util.List)
	 */
	@Override
	public void AskPrivilegeChoice(int numberOfPrivilege, List<ResourceChest> privilegeResources) {
		print("Choose " + numberOfPrivilege
				+ " different resources from the following (Please enter the numbers associated with your choices separated by commas, e.g. x,y,z, if you have more than one privilege to choose):");
		for (int i = 0; i < privilegeResources.size(); i++) {
			print("Number " + i + ":\n" + privilegeResources.get(i).toString() + "\n");
		}

		readerState = ClientConstants.SEND_PRIVILEGE_CHOICES;
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.cli.InputListener#notify(java.lang.String)
	 */
	@Override
	public void notify(String input) {
		switch (readerState) {
		case ClientConstants.SEND_NAME:
			actionConstructor.add(input);
			print("Insert your password: ");
		case ClientConstants.SEND_CHOSEN_LEADERCARD:
			gameController.notifyChosenLeaderCard(input);
			break;
		case ClientConstants.SEND_MOVE:
			if(input.toLowerCase().equals("discard")){
				print("Select a leader card to discard (insert its name): ");
				readerState = ClientConstants.SEND_DISCARDED_LEADER_CARD;
			} else if(input.toLowerCase().equals("end")){
				gameController.notifyFinishRound();
			} else if(input.toLowerCase().equals("activate")){
				print("Select the leader card you own by typing its name");
				readerState = ClientConstants.SEND_ACTIVATED_LEADER_CARD;
			}
			else
				moveHandler(input);
			break;
		case ClientConstants.SEND_PASSWORD:
			actionConstructor.add(input);
			gameController.notifyCredentials(actionConstructor);
			break;
		case ClientConstants.SEND_PRIVILEGE_CHOICES:
			gameController.notifyChosenPrivileges(input);
			break;
		case ClientConstants.SEND_DISCARDED_LEADER_CARD:
			gameController.notifyDiscardedLeaderCard(input);
			break;
		case ClientConstants.SEND_MARKET_SLOT:
			actionConstructor.add(input);
			
//			System.out.println("\nCLI:market action: actionconstructor:"+actionConstructor.get(0)+
//					" "+actionConstructor.get(1)+" "+actionConstructor.get(2)+ " "+
//					" "+actionConstructor.get(3));
			
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
		case ClientConstants.SEND_EXCOMMUNICATION_PAYMENT_CHOICE:
			if(input.equals("1"))
				gameController.notifyExcommunicationEffectChoice(true);
			else if (input.equals("2"))
				gameController.notifyExcommunicationEffectChoice(false);
			else{
				gameController.notifyExcommunicationEffectChoice(false);  //TODO questo è solo provvisorio
			}
			break;
		case ClientConstants.SEND_PRODUCTION_CHOICES:
			gameController.notifyProductionChoices(input);
			break;
		case ClientConstants.SEND_ACTIVATED_LEADER_CARD:
			gameController.notifyLeaderEffectActivation(input);
			break;
		default:
			print("Command not recognized");
			break;
		}
	}


	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.UserInterface#startDraft(java.util.ArrayList)
	 */
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

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.UserInterface#askMove()
	 */
	@Override
	public void askMove() {
		moveState=0;
		takeCardState=0;
		actionConstructor.clear();
		print("Choose what you want to do:");
		print("To perform an action, type \"action\"");
		print("To discard a leader card and get a privilege, type \"discard\"");
		print("To activate a leader card effect, type \"activate\"");
		readerState = ClientConstants.SEND_MOVE;
	}
	
	/**
	 * Take card params.
	 *
	 * @param input the input
	 */
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
//			actionConstructor.add(input);
			print("Select the floor:");
			print("0 - First floor");
			print("1 - Second floor");
			print("2 - Third floor");
			print("3 - Fourth floor");
			takeCardState = ClientConstants.SEND_TAKE_CARD_FLOOR;
			break;
		case ClientConstants.SEND_TAKE_CARD_FLOOR:
//			actionConstructor.add(input);
			gameController.notifyTakeCardAction(actionConstructor);
			break;
		}
		
	}
	
	/**
	 * Distinguish action.
	 *
	 * @param string the string
	 * @param takecard the takecard
	 */
	private void distinguishAction(String string, String takecard){
		switch(string){
		case "1": 
			takeCardParams(takecard);
			break;
		case "3":	
			print("Select market slot:");
			print("1 - First marketplace slot");
			print("2 - Second marketplace slot ");
			print(".. And so on ..");
			readerState = ClientConstants.SEND_MARKET_SLOT;
			break;
		case "2":
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

	/**
	 * Move handler.
	 *
	 * @param string the string
	 */
	private void moveHandler(String string) {
//		actionConstructor = new ArryList<String>();
//		actionConstructor.add(string);

		switch (moveState) {
		case 0:  //First possible case
			print("Select your available family member: ");
			moveState=ClientConstants.SEND_FAMILY_MEMBER;
			break;
		case ClientConstants.SEND_FAMILY_MEMBER:
			actionConstructor= new ArrayList<String>();
			actionConstructor.add(string);
			print("How many servants do you want to pay to raise your selected family member action value?");
			moveState=ClientConstants.SEND_PAID_SERVANTS;
			break;
		case ClientConstants.SEND_PAID_SERVANTS:
			actionConstructor.add(string);
			print("What action do you want to perform?");
			print("1 - Take card");
			print("2 - Place into Council Palace");
			print("3 - Place into Marketplace slot");
			print("4 - Harvest");
			print("5 - Product");
			moveState=ClientConstants.SEND_CHOSEN_ACTION;
			break;
		case ClientConstants.SEND_CHOSEN_ACTION:
			actionConstructor.add(string);
			distinguishAction(actionConstructor.get(2),string);
			break;
		}
	}


	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.UserInterface#invalidInput()
	 */
	@Override
	public void invalidInput() {
		printImp("Invalid Input");
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.UserInterface#askPersonalBonusTile(java.util.List)
	 */
	@Override
	public void askPersonalBonusTile(List<PersonalBonusTile> personalBonusTiles) {
		// TODO Auto-generated method stub

	}

	

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.UserInterface#assignColor(java.lang.String)
	 */
	@Override
	public void assignColor(String color) {
		print("Ti è stato assegnato il colore: "+color);
		gameController.setPlayerColor(color);
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.UserInterface#notifyExcommunication()
	 */
	@Override
	public void notifyExcommunication() {
		print("God seems very offended by your behaviour, he established your excommunication.");
		
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.UserInterface#opponentStatusChanged(it.polimi.ingsw.ps19.Player)
	 */
	@Override
	public void opponentStatusChanged(Player maskedPlayer) {
		print("The ");
		print(maskedPlayer.getColor());
		print(" has changed his status :\nNew details: ");
		print(maskedPlayer.getResourceChest().toString());
		
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.UserInterface#newChatMessage(java.lang.String)
	 */
	@Override
	public void newChatMessage(String message) {
		print(message);
		
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.UserInterface#askNameAndPassword()
	 */
	@Override
	public void askNameAndPassword() {
		actionConstructor.clear();
		print("Insert your name: ");
		readerState = ClientConstants.SEND_NAME;
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.UserInterface#askForProductionExchangeEffect(java.util.List)
	 */
	@Override
	public void askForProductionExchangeEffect(List<String[]> choices) {
		
		for(int i = 0; i < choices.size(); i++){
			print("You can choose one of these production effects from \"" + choices.get(i)[1] + "\" card (please enter the choices separated by a comma, e.g 1,2,1,1) :");
			print("1 - " + choices.get(i)[2]);
			print("2 - " + choices.get(i)[3]);
			readerState = ClientConstants.SEND_PRODUCTION_CHOICES;
		}
		
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.UserInterface#notifyRoundTimerExpired()
	 */
	@Override
	public void notifyRoundTimerExpired() {
		print("Your time to move is elapsed, you have lost the turn.");
		
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.UserInterface#askForExcommunicationPayment(java.lang.String)
	 */
	@Override
	public void askForExcommunicationPayment(String excommunicationEffect) {
		print("Do you accept the following excommunication effect?\n");
		print(excommunicationEffect);
		print("\n1 - No, I want to pay the faith points");
		print("2 - Yes, I accept the excommunication");
		readerState = ClientConstants.SEND_EXCOMMUNICATION_PAYMENT_CHOICE;
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.UserInterface#askFinishRoundOrDiscard()
	 */
	@Override
	public void askFinishRoundOrDiscard() {
		print("What do you want to do next?");
		print("- type \"end\" to end the turn");
		print("- type \"discard\" to discard a leader card, get a privilege and end your turn");
		readerState = ClientConstants.SEND_MOVE;
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.UserInterface#commandNotValid()
	 */
	@Override
	public void commandNotValid() {
		printImp("Invalid command!");
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.UserInterface#actionCommandNotValid(java.lang.String)
	 */
	@Override
	public void actionCommandNotValid(String reason) {
		print("Your action is invalid!");
		print(reason);
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.UserInterface#notifyServerClosed()
	 */
	@Override
	public void notifyServerClosed() {
		print("The server has closed the game");		
	}

	@Override
	public void authenticatedCorrectly(String username) {
		System.out.println("You have been authenticated correctly, get ready to play!");
		
	}

	@Override
	public void displayWrongPasswordMessage(String username) {
		print("Wrong password!");
	}

	@Override
	public void displayPlayerDisconnected(String color) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displaySatanAction(String color) {
		
		
	}

	@Override
	public void askSatanMove() {
		// TODO Auto-generated method stub
		
	}
	
}