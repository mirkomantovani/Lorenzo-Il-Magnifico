package it.polimi.ingsw.ps19.client;


import java.util.ArrayList;

import it.polimi.ingsw.ps19.command.toserver.ChosenLeaderCardCommand;
import it.polimi.ingsw.ps19.command.toserver.ChosenPrivilegeCommand;
import it.polimi.ingsw.ps19.command.toserver.ChurchSupportCommand;
import it.polimi.ingsw.ps19.command.toserver.ClientToServerCommand;
import it.polimi.ingsw.ps19.command.toserver.DiscardLeaderCardCommand;
import it.polimi.ingsw.ps19.command.toserver.FinishRoundCommand;
import it.polimi.ingsw.ps19.command.toserver.HarvestCommand;
import it.polimi.ingsw.ps19.command.toserver.InvalidInputCommand;
import it.polimi.ingsw.ps19.command.toserver.PlaceIntoCouncilPalaceCommand;
import it.polimi.ingsw.ps19.command.toserver.PlaceIntoMarketCommand;
import it.polimi.ingsw.ps19.command.toserver.PlayerMoveCommand;
import it.polimi.ingsw.ps19.command.toserver.ProductionActivationCommand;
import it.polimi.ingsw.ps19.command.toserver.ProductionCommand;
import it.polimi.ingsw.ps19.command.toserver.SendCredentialsCommand;
import it.polimi.ingsw.ps19.command.toserver.TakeCardCommand;
import it.polimi.ingsw.ps19.model.card.CardType;
import it.polimi.ingsw.ps19.network.NetworkInterface;
import it.polimi.ingsw.ps19.view.InputObserver;
import it.polimi.ingsw.ps19.view.UserInterface;
/**
 * This class represents the client controller that takes care of sending through the network
 * all the commands requested by the player. 
 * 
 * @author Jimmy
 *
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
			
			System.out.println("clientcontro: invio comando al server");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		System.out.println("\nCLIENTCONTROLLER: before check and send\n");
		ArrayList<Integer> commandConstructor = parseString(choices);
		if(commandConstructor.size() != 0)
			sendCommand(new ChosenPrivilegeCommand(commandConstructor));
		else{
			userInterface.invalidInput();
			notifyInvalidInput();
		}
		System.out.println("\nCLIENTCONTROLLER: after check and send\n");
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
		TakeCardCommand takeCardCommand = new TakeCardCommand(actionConstructor.get(0),Integer.parseInt(actionConstructor.get(4)), Integer.parseInt(actionConstructor.get(1)), CardType.values()[Integer.parseInt(actionConstructor.get(3))-1]);
		sendCommand(takeCardCommand);
		System.out.println("clientcontroller: sending takecard comand");
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



	@Override
	public void notifyCredentials(ArrayList<String> actionConstructor) {
		sendCommand(new SendCredentialsCommand(actionConstructor.get(0),actionConstructor.get(1), playerColor));
	}



	@Override
	public void notifyFinishRound() {
		sendCommand(new FinishRoundCommand());
	}



	@Override
	public void notifyExcommunicationEffectChoice(Boolean choice) {
		sendCommand(new ChurchSupportCommand(playerColor, choice));
	}



	@Override
	public void notifyProductionChoices(String choices) {
		ArrayList<Integer> commandConstructor = parseString(choices);
		if(commandConstructor.size() != 0)
			sendCommand(new ProductionActivationCommand(commandConstructor));
		else{
			userInterface.invalidInput();
			notifyInvalidInput();
		}
	}
	
	private ArrayList<Integer> parseString(String choices){
		System.out.println("\n\n sono nella parseStirng" + choices + "\n\n");
		char[] charArray = choices.toCharArray();
		for(int i = 0; i < choices.length(); i++){
			System.out.println("\n\n" + charArray[i] + "\n\n");
		}
		ArrayList<Integer> commandConstructor = new ArrayList<Integer>();
		for(int i = 0; i<charArray.length; i+=2){
			if(Character.getNumericValue(charArray[i]) != -1)
				commandConstructor.add(Character.getNumericValue(charArray[i]));
			else{
				commandConstructor.clear();    //Se l'utente inserisce una stringa a muzzo
				break;						   //il metodo ritorna una lista vuota
			}
		}
		return commandConstructor;
//		sendCommand(new ChosenPrivilegeCommand(commandConstructor));
	}
	

}