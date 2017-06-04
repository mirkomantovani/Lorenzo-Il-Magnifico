package it.polimi.ingsw.ps19.client;

import it.polimi.ingsw.ps19.command.ProvaCommand;
import it.polimi.ingsw.ps19.model.resource.Servant;
import it.polimi.ingsw.ps19.network.ClientSocketInterface;

/**
 * @author matteo
 * 
 * this is the client, it should have a main to ask the user which 
 * connection and which UI he wants to use, and to set up the choice
 *
 */
public class Client {
	
	public static void main(String[] args){
		
		Servant serv = new Servant(3);
		
		ClientSocketInterface clientSocket = new ClientSocketInterface();
		
		clientSocket.connect();
		
		clientSocket.sendCommand(new ProvaCommand(serv));
		
		System.out.println("ho inviato il comando sullo stream");
		
	}

}
