package it.polimi.ingsw.ps19.view.gui;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.Permissions;
import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.ps19.Period;
import it.polimi.ingsw.ps19.PersonalBonusTile;
import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.client.ClientController;
import it.polimi.ingsw.ps19.model.area.Board;
import it.polimi.ingsw.ps19.model.card.LeaderCard;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;
import it.polimi.ingsw.ps19.model.resource.ResourceType;
import it.polimi.ingsw.ps19.view.UserInterface;
import javafx.beans.value.WritableObjectValue;

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

	public GraphicalUserInterface(ClientController clientController) {
		this.gameController = clientController;
		// try {
		// icon = ImageIO.read(this.getClass().getResource(
		// ImagesConstants.icona));
		// } catch (IOException e) {
		// }
		frame = new MyFrame();
		personalBoard=new PersonalBoard();
		// frame.setIconImage(icon);
		frame.validate();
	}

	public void addListeners() {
		frame.getGamePanel().getSendChat().addActionListener(this);
		frame.getGamePanel().getShowPersonalBoard().addActionListener(this);
	}

	@Override
	public void initializeMatch() {
		frame.removeInitialImage();
		frame.initializeGameFrame();
		this.addListeners(); 
		frame.getGamePanel().setObserver(this);
		frame.pack();   //?
		frame.repaint();  //?
		writeGameMessage("The game has started");
		

	}

	@Override
	public void startTurn() {
	

	}

	private void writeGameMessage(String string) {
		writeMessage("\n<-GAME-> "+string+"\n");
	}

	@Override
	public void commandNotValid() {
		// TODO Auto-generated method stub

	}

	@Override
	public void playerStatusChange(Player p) {
		frame.refreshPlayerStatus(p);
//		frame.getGamePanel().getBoardPanel().add(new VictoryPointMarkerDisk(p.getColor(),p.getResourceChest().getResourceInChest(ResourceType.VICTORYPOINT).getAmount()));
//		frame.getGamePanel().getBoardPanel().add(new FaithPointMarkerDisk(p.getColor(),p.getResourceChest().getResourceInChest(ResourceType.FAITHPOINT).getAmount()));
//		frame.getGamePanel().getBoardPanel().add(new MilitaryPointMarkerDisk(p.getColor(),p.getResourceChest().getResourceInChest(ResourceType.MILITARYPOINT).getAmount()));
		

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
		writeGameMessage("A new turn is starting, Period:"+period.toString()+" Turn:"+turn);
	}

	@Override
	public void askMove() {
		writeGameMessage("It's your turn, decide an action to perform");
		frame.showChooseAction();
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
	public void displayOpponentsStatus(Player player) {
		// TODO Auto-generated method stub

	}

	@Override
	public void refreshBoard(Board board) {
		frame.refreshBoard(board);
//		frame.pack();
//		OrderMarkerDisk.Ordercounter = 0;
//		for(int i = 0; i< board.getPlayerOrder().size();i++){
//			frame.getGamePanel().getBoardPanel().add(new OrderMarkerDisk(board.getPlayerOrder().get(i)));
//		}
//		frame.getGamePanel().getBoardPanel().PlaceFamiliars(board);
		
		frame.repaint();
	
	}

	@Override
	public void notifyExcommunication() {
		writeGameMessage("God seems very offended by your behaviour, he established your excommunication.");
	}

	@Override
	public void opponentStatusChanged(Player maskedPlayer) {
		frame.getGamePanel().getBoardPanel().add(new VictoryPointMarkerDisk(maskedPlayer.getColor(),maskedPlayer.getResourceChest().getResourceInChest(ResourceType.VICTORYPOINT).getAmount()));
		frame.getGamePanel().getBoardPanel().add(new FaithPointMarkerDisk(maskedPlayer.getColor(),maskedPlayer.getResourceChest().getResourceInChest(ResourceType.FAITHPOINT).getAmount()));
		frame.getGamePanel().getBoardPanel().add(new MilitaryPointMarkerDisk(maskedPlayer.getColor(),maskedPlayer.getResourceChest().getResourceInChest(ResourceType.MILITARYPOINT).getAmount()));
		

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
		writeGameMessage("Your time's up, you'll skip this round");
	}

	@Override
	public void askForExcommunicationPayment(String excommunicationEffect) {
		// TODO Auto-generated method stub

	}

	@Override
	public void assignColor(String color) {
			gameController.setPlayerColor(color);
			frame.setPlayerColor(color);
			
	}

	@Override
	public void startDraft(ArrayList<LeaderCard> leaderCards) {
		// TODO Auto-generated method stub

	}

	@Override
	public void AskPrivilegeChoice(int numberOfPrivilege, List<ResourceChest> privilegeResources) {
		writeGameMessage("You have "+numberOfPrivilege+" council privileges to "
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
		writeGameMessage("Your action is invalid: "+reason);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(" GUI: Sono nell'action performed");
		if (e.getSource() == frame.getGamePanel().getSendChat()) {
			String message;
			message=frame.getGamePanel().getAndDeleteChatInput();
		
			
			gameController.notifyChatMessage(message);

		} else if(e.getSource() == frame.getGamePanel().getShowPersonalBoard()){
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
		personalBoard.dispose();
		frame.dispose();
		
	}

	public void notifyChosenPrivilege(String chosenP) {
		gameController.notifyChosenPrivileges(chosenP);
		
	}

}
