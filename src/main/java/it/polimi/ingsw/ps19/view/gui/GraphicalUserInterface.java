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
import it.polimi.ingsw.ps19.model.card.LeaderCard;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;
import it.polimi.ingsw.ps19.view.UserInterface;

public class GraphicalUserInterface implements UserInterface, ActionListener {

	private MyFrame frame;
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
		// frame.setIconImage(icon);
		frame.validate();
	}

	public void addListeners() {
		frame.getGamePanel().getSendChat().addActionListener(this);
	}

	@Override
	public void initializeMatch() {
		frame.removeInitialImage();
		// frame.initializeGameFrame();

	}

	@Override
	public void startTurn() {
		// TODO Auto-generated method stub

	}

	@Override
	public void commandNotValid() {
		// TODO Auto-generated method stub

	}

	@Override
	public void playerStatusChange(Player p) {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

	}

	@Override
	public void lose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initializeTurn(Period period, int turn) {

	}

	@Override
	public void askMove() {
		// TODO Auto-generated method stub

	}

	@Override
	public void invalidInput() {
		// TODO Auto-generated method stub

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
		frame.removeInitialImage();
		frame.initializeGameFrame(board);
		this.addListeners();
		frame.pack();
		frame.repaint();
	}

	@Override
	public void notifyExcommunication() {
		// TODO Auto-generated method stub

	}

	@Override
	public void opponentStatusChanged(Player maskedPlayer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void newChatMessage(String message) {
		frame.getGamePanel().addMessageToConsole(message);

	}

	@Override
	public void notifyRoundTimerExpired() {
		// TODO Auto-generated method stub

	}

	@Override
	public void askForExcommunicationPayment(String excommunicationEffect) {
		// TODO Auto-generated method stub

	}

	@Override
	public void assignColor(String color) {
			gameController.setPlayerColor(color);
	}

	@Override
	public void startDraft(ArrayList<LeaderCard> leaderCards) {
		// TODO Auto-generated method stub

	}

	@Override
	public void AskPrivilegeChoice(int numberOfPrivilege, List<ResourceChest> privilegeResources) {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

	}

	@Override
	public void actionCommandNotValid(String reason) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(" GUI: Sono nell'action performed");
		if (e.getSource() == frame.getGamePanel().getSendChat()) {
			String message;
			message=frame.getGamePanel().getAndDeleteChatInput();
		
			
			gameController.notifyChatMessage(message);

		}
	}

}
