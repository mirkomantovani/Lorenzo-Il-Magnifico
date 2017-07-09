package it.polimi.ingsw.ps19;

import it.polimi.ingsw.ps19.client.ClientCommandHandler;

public class ReconnectionManager {

	ClientCommandHandler handler;
	String name;
	String pword;
	
	public ReconnectionManager(ClientCommandHandler handler, String name, String pword) {
		this.name = name;
		this.pword= pword;
		this.handler = handler;
	}
	
	

}
