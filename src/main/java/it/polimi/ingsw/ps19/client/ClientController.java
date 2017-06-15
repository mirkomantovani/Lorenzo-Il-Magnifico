package it.polimi.ingsw.ps19.client;


import it.polimi.ingsw.ps19.command.ClientToServerCommand;

import it.polimi.ingsw.ps19.command.ServerToClientCommand;
import it.polimi.ingsw.ps19.model.card.Card;
import it.polimi.ingsw.ps19.network.NetworkInterface;
import it.polimi.ingsw.ps19.view.UserInterface;
/**
 * @author matteo
 *
 *  this is the game controller of the MVC pattern
 */
public class ClientController {
	
//	
//	private UserInterface userInterface;
//	private NetworkInterface networkInterface;
//	private ClientCommandHandler commandHandler;
//
//	public ClientController(NetworkInterface networkInterface) {
//		this.networkInterface = networkInterface;
//	}
//
//	public void setCommandHandler(ClientCommandHandler handler) {
//		this.commandHandler = handler;
//	}
//
//	public void setUserInterface(UserInterface userInterface) {
//		this.userInterface = userInterface;
//	}
//
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
//
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
//	

}
