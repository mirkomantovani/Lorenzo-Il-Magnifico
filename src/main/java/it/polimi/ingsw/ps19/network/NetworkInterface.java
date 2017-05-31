package it.polimi.ingsw.ps19.network;

public interface NetworkInterface{

	
	void connect();

	
	void sendCommand(ClientToServerCommand command);


	void notifyNewCommand(ServerToClientCommand command);

	
	void closeConnection();

}