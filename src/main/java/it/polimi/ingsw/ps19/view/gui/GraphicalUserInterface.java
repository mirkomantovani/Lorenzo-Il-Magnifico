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
 * @author Mirko
 *
 */
public class GraphicalUserInterface implements UserInterface, ActionListener {

	private MyFrame frame;
	private PersonalBoard personalBoard;
	// private String player;
	private Image icon;
	private ClientController gameController;
	private boolean excommunicationCubeNeeded;

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

	public void addListeners() {
		frame.getGamePanel().getSendChat().addActionListener(this);
		frame.getGamePanel().getShowPersonalBoard().addActionListener(this);
	}

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

	@Override
	public void startTurn() {

	}

	private void writeGameMessage(String string) {
		writeMessage("\n<-GAME-> " + string + "\n");
	}

	@Override
	public void commandNotValid() {
		writeGameMessage("Your command is invalid");
	}

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

	@Override
	public void playerMove() {
		// TODO Auto-generated method stub

	}

	@Override
	public void playerTurn() {
		// TODO Auto-generated method stub

	}

	@Override
	public void win() {
		writeGameMessage("CONGRATULATIONS! You won the game!\nPress any key to exit the game");
	}

	@Override
	public void lose() {
		writeGameMessage("OOPS, You lost. Try again, next time you will be luckier!\nPress any key to exit the game");

	}

	@Override
	public void initializeTurn(Period period, int turn) {
		writeGameMessage("A new turn is starting, Period:" + period.toString() + " Turn:" + turn);
		FamilyMemberPawn.councilCounter = 0;
		frame.getGamePanel().removeCards();
		System.out.println("sono in initialize ho azzerato council");
		frame.getGamePanel().resetFamiliars();
		System.out.println("ho chiamato la reset da initialize");
	}

	@Override
	public void askMove() {
		writeGameMessage("It's your turn, decide an action to perform");
		frame.showChooseAction();
		frame.getGamePanel().setLeaderState("none");
	}

	@Override
	public void invalidInput() {
		writeMessage("--INVALID INPUT--");
	}

	@Override
	public void askPersonalBonusTile(List<PersonalBonusTile> personalBonusTiles) {
		// TODO Auto-generated method stub

	}


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

	@Override
	public void notifyExcommunication() {
		excommunicationCubeNeeded=true;
		writeGameMessage("God seems very offended by your behaviour, he established your excommunication.");
	}

	@Override
	public void opponentStatusChanged(Player maskedPlayer) {
		frame.getGamePanel().setPointsMarkers(maskedPlayer);
		System.out.println("sono in opponent status change");
		frame.getGamePanel().repaintBoard();

	}

	@Override
	public void newChatMessage(String message) {
		writeMessage(message);

	}

	private void writeMessage(String message) {
		frame.getGamePanel().addMessageToConsole(message);
	}

	@Override
	public void notifyRoundTimerExpired() {
		writeGameMessage("Your time's up, you've been disconnected");
		frame.getGamePanel().playerDisconnected();
	}

	@Override
	public void askForExcommunicationPayment(String excommunicationEffect) {
		writeGameMessage("The excommunication phase has started, choose if you want "
				+ "to be excommunicated or not, the excommunication effect is: "+
				excommunicationEffect);
		frame.showExcommunicationPanel();
	}

	@Override
	public void assignColor(String color) {
		gameController.setPlayerColor(color);
		frame.setPlayerColor(color);

	}

	@Override
	public void startDraft(ArrayList<LeaderCard> leaderCards) {
		if(leaderCards.size()==4)
			writeGameMessage("The Leader Draft phase has started!");
	
		writeGameMessage("Choose the leader card you want and pass the other 3 to"
				+ "the player at your right");
		frame.getGamePanel().showChooseLeaderDraft(leaderCards);
	}

	@Override
	public void AskPrivilegeChoice(int numberOfPrivilege, List<ResourceChest> privilegeResources) {
		writeGameMessage("You have " + numberOfPrivilege + " council privileges to "
				+ "choose, click on the resource you would like to get");
		frame.showPrivilegeChoice();
	}

	@Override
	public void askNameAndPassword() {
		// TODO Auto-generated method stub

	}

	@Override
	public void askForProductionExchangeEffect(List<String[]> choices) {
		// TODO Auto-generated method stub

	}

	@Override
	public void askFinishRoundOrDiscard() {
		writeGameMessage("Choose what you want to do next, Discard a leader card or end the turn");
		frame.showEndOrDiscard();

	}

	@Override
	public void actionCommandNotValid(String reason) {
		writeGameMessage("Your action is invalid: " + reason);
	}

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

	public void notifyTakeCard(ArrayList<String> actionConstructor) {
		gameController.notifyTakeCardAction(actionConstructor);
	}

	public void notifyEndRound() {
		gameController.notifyFinishRound();

	}

	public void notifyCloseGame() {
		gameController.notifyRequestClosureCommand();
		personalBoard.dispose();
		frame.dispose();

	}

	public void notifyChosenPrivilege(String chosenP) {
		gameController.notifyChosenPrivileges(chosenP);

	}

	public void notifyMarketAction(ArrayList<String> actionConstructor) {
		System.out.println("\nmarket action: actionconstructor:"+actionConstructor.get(0)+
				" "+actionConstructor.get(1)+" "+actionConstructor.get(2)+ " "+
				" "+actionConstructor.get(3));
		gameController.notifyMarket(actionConstructor);
	}

	public void notifyCouncilAction(ArrayList<String> actionConstructor) {
		gameController.notifyCouncilPalace(actionConstructor);
	}

	public void notifyHarvest(ArrayList<String> actionConstructor) {
		gameController.notifyHarvest(actionConstructor);
	}

	public void notifyProduction(ArrayList<String> actionConstructor) {
		gameController.notifyProduction(actionConstructor);
	}

	public void notifyExcommunicationChoice(boolean showSupportDecision) {
		gameController.notifyExcommunicationEffectChoice(showSupportDecision);
	}

	public void notifyChosenLeaderInDraft(String leaderName) {
		gameController.notifyChosenLeaderCard(leaderName);
	}

	public void notifyActivateLeader(String leaderName) {
		gameController.notifyLeaderEffectActivation(leaderName);
	}

	public void notifyDiscardLeader(String leaderName) {
		gameController.notifyDiscardedLeaderCard(leaderName);
	}

	@Override
	public void notifyServerClosed() {
		writeGameMessage("The server has closed the game and the connections"
				+ " because no one was connected anymore");
	}

}
