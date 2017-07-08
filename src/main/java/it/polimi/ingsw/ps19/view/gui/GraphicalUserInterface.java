package it.polimi.ingsw.ps19.view.gui;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.ps19.Period;
import it.polimi.ingsw.ps19.PersonalBonusTile;
import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.client.ClientController;
import it.polimi.ingsw.ps19.model.area.Board;
import it.polimi.ingsw.ps19.model.card.CardType;
import it.polimi.ingsw.ps19.model.card.DevelopmentCard;
import it.polimi.ingsw.ps19.model.card.LeaderCard;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;
import it.polimi.ingsw.ps19.view.UserInterface;

/**
 * The GUI, implementations of the User Interface using Swing and AWT components
 *
 * @author Mirko
 */
public class GraphicalUserInterface implements UserInterface, ActionListener {

	/** The frame. */
	private MyFrame frame;
	
	/** The personal board. */
	private PersonalBoard personalBoard;
	
	/** The icon. */
	// private String player;
	private Image icon;
	
	/** The game controller. */
	private ClientController gameController;
	
	/** The excommunication cube needed. */
	private boolean excommunicationCubeNeeded;

	/**
	 * Instantiates a new graphical user interface.
	 *
	 * @param clientController the client controller
	 */
	public GraphicalUserInterface(ClientController clientController) {
		this.gameController = clientController;
		// try {
		// icon = ImageIO.read(this.getClass().getResource(
		// ImagesConstants.icona));
		// } catch (IOException e) {
		// }
		frame = new MyFrame();
		personalBoard = new PersonalBoard();
		// frame.setIconImage(icon);
		frame.validate();
	}

	/**
	 * Adds the listeners.
	 */
	public void addListeners() {
		frame.getGamePanel().getSendChat().addActionListener(this);
		frame.getGamePanel().getShowPersonalBoard().addActionListener(this);
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.UserInterface#initializeMatch(int)
	 */
	@Override
	public void initializeMatch(int numPlayers) {
		frame.removeInitialImage();
		frame.initializeGameFrame(numPlayers);
		this.addListeners();
		frame.getGamePanel().setObserver(this);
		frame.pack(); // ?
		frame.repaint(); // ?
		writeGameMessage("The game has started");

	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.UserInterface#startTurn()
	 */
	@Override
	public void startTurn() {

	}

	/**
	 * Write game message.
	 *
	 * @param string the string
	 */
	private void writeGameMessage(String string) {
		writeMessage("\n<-GAME-> " + string + "\n");
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.UserInterface#commandNotValid()
	 */
	@Override
	public void commandNotValid() {
		writeGameMessage("Your command is invalid");
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.UserInterface#playerStatusChange(it.polimi.ingsw.ps19.Player)
	 */
	@Override
	public void playerStatusChange(Player p) {
		
		if(excommunicationCubeNeeded){
			frame.getGamePanel().addExcommunicationCubes(p);
			excommunicationCubeNeeded=false;
		}
		
		frame.refreshPlayerStatus(p);
		addCardsToPersonalBoard(p);
		frame.refreshLeaderCards(p.getLeaderCards());
		frame.getGamePanel().setPointsMarkers(p);
		frame.getGamePanel().repaintResources();
	}

	/**
	 * Adds the cards to personal board.
	 *
	 * @param p the p
	 */
	private void addCardsToPersonalBoard(Player p) {
		
		ArrayList<DevelopmentCard> deck;
		
		for (int i = 0; i < CardType.values().length; i++) {
			if (CardType.values()[i] != CardType.ANY) {
				deck=p.getDeckOfType(CardType.values()[i]);
				if(deck.size()!=personalBoard.getRightNum(CardType.values()[i])){
					DevelopmentCard cardToAdd=deck.get(deck.size()-1);
					JDevelopmentCard jCard=new JDevelopmentCard
							(cardToAdd.getCardType(), cardToAdd.getId(), personalBoard.getRightNum(CardType.values()[i]));
					personalBoard.getPersonalBoardPanel().add(jCard);
					personalBoard.repaint();
					personalBoard.incrementRightNum(cardToAdd.getCardType());
					
				}
			}

		}
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.UserInterface#playerMove()
	 */
	@Override
	public void playerMove() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.UserInterface#playerTurn()
	 */
	@Override
	public void playerTurn() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.UserInterface#win()
	 */
	@Override
	public void win() {
		writeGameMessage("CONGRATULATIONS! You won the game!\nPress any key to exit the game");
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.UserInterface#lose()
	 */
	@Override
	public void lose() {
		writeGameMessage("OOPS, You lost. Try again, next time you will be luckier!\nPress any key to exit the game");

	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.UserInterface#initializeTurn(it.polimi.ingsw.ps19.Period, int)
	 */
	@Override
	public void initializeTurn(Period period, int turn) {
		writeGameMessage("A new turn is starting, Period:" + period.toString() + " Turn:" + turn);
		FamilyMemberPawn.councilCounter = 0;
		frame.getGamePanel().removeCards();
		System.out.println("sono in initialize ho azzerato council");
		frame.getGamePanel().resetFamiliars();
		System.out.println("ho chiamato la reset da initialize");
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.UserInterface#askMove()
	 */
	@Override
	public void askMove() {
		writeGameMessage("It's your turn, decide an action to perform");
		frame.showChooseAction();
		frame.getGamePanel().setLeaderState("none");
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.UserInterface#invalidInput()
	 */
	@Override
	public void invalidInput() {
		writeMessage("--INVALID INPUT--");
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.UserInterface#askPersonalBonusTile(java.util.List)
	 */
	@Override
	public void askPersonalBonusTile(List<PersonalBonusTile> personalBonusTiles) {
		// TODO Auto-generated method stub

	}


	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.UserInterface#refreshBoard(it.polimi.ingsw.ps19.model.area.Board)
	 */
	@Override
	public void refreshBoard(Board board) {
		
		frame.refreshBoard(board);
		// frame.pack();
		 OrderMarkerDisk.Ordercounter = 0;
		frame.getGamePanel().setExcommTiles(board);
		frame.getGamePanel().populateFamiliars(board);
		frame.getGamePanel().createMarkers(board);
		frame.getGamePanel().removeDicesAndMarkers();
		frame.getGamePanel().updateOrder(board);
		frame.getGamePanel().PlaceFamiliars(board);
		frame.getGamePanel().setDices(board);
		
		frame.getGamePanel().repaintBoard();

	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.UserInterface#notifyExcommunication()
	 */
	@Override
	public void notifyExcommunication() {
		excommunicationCubeNeeded=true;
		writeGameMessage("God seems very offended by your behaviour, he established your excommunication.");
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.UserInterface#opponentStatusChanged(it.polimi.ingsw.ps19.Player)
	 */
	@Override
	public void opponentStatusChanged(Player maskedPlayer) {
		frame.getGamePanel().addExcommunicationCubes(maskedPlayer);
		frame.getGamePanel().setPointsMarkers(maskedPlayer);
		System.out.println("sono in opponent status change");
		frame.getGamePanel().repaintBoard();

	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.UserInterface#newChatMessage(java.lang.String)
	 */
	@Override
	public void newChatMessage(String message) {
		writeMessage(message);

	}

	/**
	 * Write message.
	 *
	 * @param message the message
	 */
	private void writeMessage(String message) {
		frame.getGamePanel().addMessageToConsole(message);
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.UserInterface#notifyRoundTimerExpired()
	 */
	@Override
	public void notifyRoundTimerExpired() {
		writeGameMessage("Your time's up, you've been disconnected");
		frame.getGamePanel().playerDisconnected();
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.UserInterface#askForExcommunicationPayment(java.lang.String)
	 */
	@Override
	public void askForExcommunicationPayment(String excommunicationEffect) {
		writeGameMessage("The excommunication phase has started, choose if you want "
				+ "to be excommunicated or not, the excommunication effect is: "+
				excommunicationEffect);
		frame.showExcommunicationPanel();
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.UserInterface#assignColor(java.lang.String)
	 */
	@Override
	public void assignColor(String color) {
		gameController.setPlayerColor(color);
		frame.setPlayerColor(color);

	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.UserInterface#startDraft(java.util.ArrayList)
	 */
	@Override
	public void startDraft(ArrayList<LeaderCard> leaderCards) {
		if(leaderCards.size()==4)
			writeGameMessage("The Leader Draft phase has started!");
	
		writeGameMessage("Choose the leader card you want and pass the other 3 to"
				+ "the player at your right");
		frame.getGamePanel().showChooseLeaderDraft(leaderCards);
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.UserInterface#AskPrivilegeChoice(int, java.util.List)
	 */
	@Override
	public void AskPrivilegeChoice(int numberOfPrivilege, List<ResourceChest> privilegeResources) {
		writeGameMessage("You have " + numberOfPrivilege + " council privileges to "
				+ "choose, click on the resource you would like to get");
		frame.showPrivilegeChoice();
		frame.getGamePanel().setCurrentNumberOfPrivilege(numberOfPrivilege);
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.UserInterface#askNameAndPassword()
	 */
	@Override
	public void askNameAndPassword() {
		writeGameMessage("Insert Name and Password to Login or Signup");
		frame.getGamePanel().showAskAuthentication();

	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.UserInterface#askForProductionExchangeEffect(java.util.List)
	 */
	@Override
	public void askForProductionExchangeEffect(List<String[]> choices) {
		
		ArrayList<Integer> cardsIds=new ArrayList<>();
		
		writeGameMessage("Choose the production effects you want to activate:");
		for(String[] stringArray: choices){
			cardsIds.add(Integer.parseInt(stringArray[0]));
			writeGameMessage("Normal effect: "+stringArray[2]+" or alternative effect"+stringArray[3]);
		}
		
		frame.getGamePanel().showChooseProductionEffects(cardsIds);
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.UserInterface#askFinishRoundOrDiscard()
	 */
	@Override
	public void askFinishRoundOrDiscard() {
		writeGameMessage("Choose what you want to do next, Discard a leader card or end the turn");
		frame.showEndOrDiscard();

	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.UserInterface#actionCommandNotValid(java.lang.String)
	 */
	@Override
	public void actionCommandNotValid(String reason) {
		writeGameMessage("Your action is invalid: " + reason);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(" GUI: Sono nell'action performed");
		if (e.getSource() == frame.getGamePanel().getSendChat()) {
			String message;
			message = frame.getGamePanel().getAndDeleteChatInput();

			gameController.notifyChatMessage(message);

		} else if (e.getSource() == frame.getGamePanel().getShowPersonalBoard()) {
			personalBoard.setVisible(!personalBoard.isVisible());
		}

	}

	/**
	 * Notify take card.
	 *
	 * @param actionConstructor the action constructor
	 */
	public void notifyTakeCard(ArrayList<String> actionConstructor) {
		gameController.notifyTakeCardAction(actionConstructor);
	}

	/**
	 * Notify end round.
	 */
	public void notifyEndRound() {
		gameController.notifyFinishRound();

	}

	/**
	 * Notify close game.
	 */
	public void notifyCloseGame() {
		gameController.notifyRequestClosureCommand();
		personalBoard.dispose();
		frame.dispose();

	}

	/**
	 * Notify chosen privilege.
	 *
	 * @param chosenP the chosen P
	 */
	public void notifyChosenPrivilege(String chosenP) {
		gameController.notifyChosenPrivileges(chosenP);

	}

	/**
	 * Notify market action.
	 *
	 * @param actionConstructor the action constructor
	 */
	public void notifyMarketAction(ArrayList<String> actionConstructor) {
		System.out.println("\nmarket action: actionconstructor:"+actionConstructor.get(0)+
				" "+actionConstructor.get(1)+" "+actionConstructor.get(2)+ " "+
				" "+actionConstructor.get(3));
		gameController.notifyMarket(actionConstructor);
	}

	/**
	 * Notify council action.
	 *
	 * @param actionConstructor the action constructor
	 */
	public void notifyCouncilAction(ArrayList<String> actionConstructor) {
		gameController.notifyCouncilPalace(actionConstructor);
	}

	/**
	 * Notify harvest.
	 *
	 * @param actionConstructor the action constructor
	 */
	public void notifyHarvest(ArrayList<String> actionConstructor) {
		gameController.notifyHarvest(actionConstructor);
	}

	/**
	 * Notify production.
	 *
	 * @param actionConstructor the action constructor
	 */
	public void notifyProduction(ArrayList<String> actionConstructor) {
		gameController.notifyProduction(actionConstructor);
	}

	/**
	 * Notify excommunication choice.
	 *
	 * @param showSupportDecision the show support decision
	 */
	public void notifyExcommunicationChoice(boolean showSupportDecision) {
		gameController.notifyExcommunicationEffectChoice(showSupportDecision);
	}

	/**
	 * Notify chosen leader in draft.
	 *
	 * @param leaderName the leader name
	 */
	public void notifyChosenLeaderInDraft(String leaderName) {
		gameController.notifyChosenLeaderCard(leaderName);
	}

	/**
	 * Notify activate leader.
	 *
	 * @param leaderName the leader name
	 */
	public void notifyActivateLeader(String leaderName) {
		gameController.notifyLeaderEffectActivation(leaderName);
	}

	/**
	 * Notify discard leader.
	 *
	 * @param leaderName the leader name
	 */
	public void notifyDiscardLeader(String leaderName) {
		gameController.notifyDiscardedLeaderCard(leaderName);
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.view.UserInterface#notifyServerClosed()
	 */
	@Override
	public void notifyServerClosed() {
		writeGameMessage("The server has closed the game and the connections"
				+ " because no one was connected anymore");
	}

	/**
	 * Notify activate production.
	 *
	 * @param choices the choices
	 */
	public void notifyActivateProduction(ArrayList<Integer> choices) {
		gameController.notifyProductionChoices(choices);
	}

	public void notifyAuthenticationRequest(String username, String password) {
		gameController.notifyAuthenticationRequest(username,password);
	}

	@Override
	public void authenticatedCorrectly(String username) {
		writeGameMessage(username+" your authentication was successful");
		frame.getGamePanel().setUsername(username);
		
	}

	@Override
	public void displayWrongPasswordMessage(String username) {
		writeGameMessage("The password you inserted did not correspond to the one of the player: "+username+" , please try again");
		frame.getGamePanel().showAskAuthentication();
	}

	@Override
	public void displayPlayerDisconnected(String color) {
		writeGameMessage("The "+color+" player has disconnected from the game!");
	}

}
