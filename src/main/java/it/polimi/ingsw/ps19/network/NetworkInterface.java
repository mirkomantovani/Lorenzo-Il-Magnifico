package it.polimi.ingsw.ps19.network;

import it.polimi.ingsw.ps19.client.ClientCommandHandler;
import it.polimi.ingsw.ps19.command.ClientToServerCommand;
import it.polimi.ingsw.ps19.command.ServerToClientCommand;

public interface NetworkInterface{

	
	void connect() throws Exception;

	
	void sendCommand( ClientToServerCommand command) throws Exception;


	void notifyClient(ServerToClientCommand command);

	
	void closeConnection();


	void addCommandObserver(ClientCommandHandler handler);

}