package it.polimi.ingsw.ps19.client;

import it.polimi.ingsw.ps19.gui.UserInterface;
import it.polimi.ingsw.ps19.network.NetworkInterface;

/**
 * @author matteo
 *
 *  this object modifies the view of the client by applying commands
 */
public class ClientCommandHandler {

	private UserInterface userInterface;
	private NetworkInterface networkInterface;
	
	public ClientCommandHandler(UserInterface ui, NetworkInterface networkInterface){
		this.userInterface = ui;
		this.networkInterface = networkInterface;
	}
	
}
