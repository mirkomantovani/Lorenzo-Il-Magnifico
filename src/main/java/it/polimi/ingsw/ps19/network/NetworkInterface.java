package it.polimi.ingsw.ps19.network;

import it.polimi.ingsw.ps19.command.ClientToServerCommand;
import it.polimi.ingsw.ps19.command.ServerToClientCommand;

public interface NetworkInterface{

	
	void connect();

	
	void sendCommand(ClientToServerCommand command);


	void notifyNewCommand(ServerToClientCommand command);

	
	void closeConnection();

}