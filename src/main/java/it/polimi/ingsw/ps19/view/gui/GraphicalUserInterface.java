package it.polimi.ingsw.ps19.view.gui;

import java.awt.Container;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import it.polimi.ingsw.ps19.Period;
import it.polimi.ingsw.ps19.PersonalBonusTile;
import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.model.area.Board;
import it.polimi.ingsw.ps19.model.card.LeaderCard;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;
import it.polimi.ingsw.ps19.view.UserInterface;

public class GraphicalUserInterface implements UserInterface{

	@Override
	public void initializeMatch() {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void AskPrivilegeChoice(int numberOfPrivilege, List<ResourceChest> privilegeResources) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void startDraft(List<LeaderCard> leaderCards) {
		List<ImageButton> buttons;
		JDialog leaderFrame = new JDialog();
		
		leaderFrame.setResizable(false);
		leaderFrame.setTitle("Choose one leader card from the following:");
		buttons = new ArrayList<ImageButton>();
		
		leaderFrame.setVisible(true);
		leaderFrame.setContentPane(new Container());
	
		for(int i = 0; i<leaderCards.size(); i++){
		
		buttons.add(new ImageButton("src/main/resources/leaders_f_c_01.jpg"));
		leaderFrame.getContentPane().add(buttons.get(i), buttons.get(i).getUI());
		buttons.get(i).setLocation(((i)*300), 0);
		}
		
		leaderFrame.setResizable(true);
		leaderFrame.setBounds(0, 0, 300*4, 555);
		
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
	public void askPersonalBonusTile(ArrayList<PersonalBonusTile> personalBonusTiles) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayOpponentsStatus(Player player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refreshBoard(Board board) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notApplicableAction() {
		// TODO Auto-generated method stub
		
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
	public void notifyRoundTimerExpired() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void askForExcommunicationPayment(String excommunicationEffect) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void assignColor(String color) {
		// TODO Auto-generated method stub
		
	}

}
