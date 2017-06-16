package it.polimi.ingsw.ps19.network;

import it.polimi.ingsw.ps19.command.toclient.ServerToClientCommand;
import it.polimi.ingsw.ps19.command.toserver.ClientToServerCommand;

public interface NetworkInterface{

	
	void connect() throws Exception;

	
	void sendCommand( ClientToServerCommand command) throws Exception;


	void notifyClient(ServerToClientCommand command);

	
	void closeConnection();

}