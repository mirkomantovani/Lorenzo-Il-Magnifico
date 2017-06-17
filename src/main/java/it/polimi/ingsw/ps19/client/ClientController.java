package it.polimi.ingsw.ps19.client;


import java.util.ArrayList;

import it.polimi.ingsw.ps19.command.PlayerMoveCommand;
import it.polimi.ingsw.ps19.command.toserver.ChosenLeaderCardCommand;
import it.polimi.ingsw.ps19.command.toserver.ChosenPrivilegeCommand;
import it.polimi.ingsw.ps19.command.toserver.ClientToServerCommand;
import it.polimi.ingsw.ps19.command.toserver.InvalidInputCommand;
import it.polimi.ingsw.ps19.network.NetworkInterface;
import it.polimi.ingsw.ps19.view.InputObserver;
import it.polimi.ingsw.ps19.view.UserInterface;
/**
 * @author matteo
 *
 *  this is the game controller of the MVC pattern
 */
public class ClientController implements InputObserver{

	private UserInterface userInterface;
	private NetworkInterface networkInterface;
	private ClientCommandHandler commandHandler;
	
	public ClientController(NetworkInterface networkInterface) {
		this.networkInterface = networkInterface;
	}
	

	
	private void sendCommand(ClientToServerCommand command){
		try {
			networkInterface.sendCommand(command);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	@Override
	public void notifyName(String name) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void notifyPassword(String password){
		// TODO Auto-generated method stub
		
	}



	@Override
	public void notifyChosenLeaderCard(String leaderCardName) {
		sendCommand(new ChosenLeaderCardCommand(leaderCardName));
	}
	
	@Override
	public void notifyMove(String move){
		sendCommand(new PlayerMoveCommand(move));
	}
	
	@Override
	public void notifyChosenPrivileges(String choices){
		char[] charArray = choices.toCharArray();
		ArrayList<Integer> commandConstructor = new ArrayList<Integer>();
		for(int i = 0; i<charArray.length; i+=2){
			if(Character.getNumericValue(charArray[i]) != -1)
				commandConstructor.add(Character.getNumericValue(charArray[i]));
			else
				userInterface.invalidInput();
				sendCommand(new InvalidInputCommand());
				return;
		}
		sendCommand(new ChosenPrivilegeCommand(commandConstructor));
	}

	

	public void setCommandHandler(ClientCommandHandler handler) {
		this.commandHandler = handler;
	}

	public void setUserInterface(UserInterface userInterface) {
		this.userInterface = userInterface;
	}

//	@Override
//	public void notify(Coordinates coord) {
//		sendCommand(new CommandSendCoordinates(coord));
//	}
//
//	@Override
//	public void notify(Card card) {
//		sendCommand(new CommandCard(card));
//	}
//
//	@Override
//	public void notifyMove() {
//		sendCommand(new CommandMove());
//	}
//
//	@Override
//	public void notifyAttack() {
//		sendCommand(new CommandAttack());
//	}
//
//	@Override
//	public void notifyPassTurn() {
//		sendCommand(new CommandEndTurn());
//	}
//
//	@Override
//	public void notifyDrawCard() {
//		sendCommand(new CommandSolveSector());
//	}
//
//	@Override
//	public void notifyDiscardItem() {
//		sendCommand(new CommandDiscardItem());
//	}

//	private void sendCommand(ClientToServerCommand command) {
//		try {
//			networkInterface.sendCommand(command);
//		} catch (Exception e) {
//		
//			 networkInterface.closeConnection();
//		}
//	}
//
//	// THERE WILL BE A LOT OF NOTIFIES FOR EACH COMMAND
//	@Override
//	public void notify(int coordinates) {
//		// TODO Auto-generated method stub
//		
//	}
	

}
