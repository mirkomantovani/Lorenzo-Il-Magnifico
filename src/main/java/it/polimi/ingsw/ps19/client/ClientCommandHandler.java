package it.polimi.ingsw.ps19.client;

import it.polimi.ingsw.ps19.network.NetworkInterface;
import it.polimi.ingsw.ps19.view.UserInterface;

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
	
	//TODO the applyCommand() for each Command from Server to Client we define	
}
