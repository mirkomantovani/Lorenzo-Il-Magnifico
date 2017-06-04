package it.polimi.ingsw.ps19.network;

import it.polimi.ingsw.ps19.command.ClientToServerCommand;
import it.polimi.ingsw.ps19.command.ServerToClientCommand;

public class SocketInterface implements NetworkInterface {

	@Override
	public void connect() {
		
	}

	@Override
	public void sendCommand(ClientToServerCommand command) {
		
	}

	@Override
	public void notifyNewCommand(ServerToClientCommand command) {
		
	}

	@Override
	public void closeConnection() {
		
	}

}
