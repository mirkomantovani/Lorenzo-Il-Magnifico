package it.polimi.ingsw.ps19.client;


import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.ps19.command.toserver.ChosenLeaderCardCommand;
import it.polimi.ingsw.ps19.command.toserver.ChosenPrivilegeCommand;
import it.polimi.ingsw.ps19.command.toserver.ClientToServerCommand;
import it.polimi.ingsw.ps19.command.toserver.DiscardLeaderCardCommand;
import it.polimi.ingsw.ps19.command.toserver.HarvestCommand;
import it.polimi.ingsw.ps19.command.toserver.InvalidInputCommand;
import it.polimi.ingsw.ps19.command.toserver.PlaceIntoCouncilPalaceCommand;
import it.polimi.ingsw.ps19.command.toserver.PlaceIntoMarketCommand;
import it.polimi.ingsw.ps19.command.toserver.PlayerMoveCommand;
import it.polimi.ingsw.ps19.command.toserver.ProductionCommand;
import it.polimi.ingsw.ps19.command.toserver.TakeCardCommand;
import it.polimi.ingsw.ps19.model.card.CardType;
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
	private String playerColor;
	
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
		sendCommand(new ChosenLeaderCardCommand(leaderCardName, playerColor));
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
			else{
				userInterface.invalidInput();
				notifyInvalidInput();
				return;
			}
		}
		sendCommand(new ChosenPrivilegeCommand(commandConstructor));
	}

	

	public void setCommandHandler(ClientCommandHandler handler) {
		this.commandHandler = handler;
	}

	public void setUserInterface(UserInterface userInterface) {
		this.userInterface = userInterface;
	}



	@Override
	public void notifyInvalidInput() {
		sendCommand(new InvalidInputCommand());		
	}



	@Override
	public void notifyCouncilPalace(ArrayList<String> actionConstructor) {
		sendCommand(new PlaceIntoCouncilPalaceCommand(actionConstructor.get(0), Integer.parseInt(actionConstructor.get(1))));
	}



	@Override
	public void notifyTakeCardAction(ArrayList<String> actionConstructor) {
		TakeCardCommand takeCardCommand = new TakeCardCommand(actionConstructor.get(0),Integer.parseInt(actionConstructor.get(4)), Integer.parseInt(actionConstructor.get(2)), CardType.values()[Integer.parseInt(actionConstructor.get(4))-1]);
		sendCommand(takeCardCommand);
	}



	@Override
	public void notifyMarket(ArrayList<String> actionConstructor) {
		PlaceIntoMarketCommand placeIntoMarketCommand = new PlaceIntoMarketCommand(actionConstructor.get(0), actionConstructor.get(3), Integer.parseInt(actionConstructor.get(1)));
		sendCommand(placeIntoMarketCommand);
	}



	@Override
	public void notifyHarvest(ArrayList<String> actionConstructor) {
		HarvestCommand harvestCommand = new HarvestCommand(actionConstructor.get(0), Integer.parseInt(actionConstructor.get(1)), Integer.parseInt(actionConstructor.get(3)));
		sendCommand(harvestCommand);
	}



	@Override
	public void notifyProduction(ArrayList<String> actionConstructor) {
		ProductionCommand productionCommand = new ProductionCommand(actionConstructor.get(0), Integer.parseInt(actionConstructor.get(1)), Integer.parseInt(actionConstructor.get(3)));
		sendCommand(productionCommand);
	}



	@Override
	public void notifyDiscardedLeaderCard(String discardedLeaderCard) {
		sendCommand(new DiscardLeaderCardCommand(discardedLeaderCard));
		
	}



	public void setPlayerColor(String color) {
		this.playerColor=color;
	}

}
