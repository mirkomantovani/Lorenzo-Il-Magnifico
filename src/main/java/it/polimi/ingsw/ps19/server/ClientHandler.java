package it.polimi.ingsw.ps19.server;


import java.io.IOException;

import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.command.ServerToClientCommand;

public abstract class ClientHandler {

	
	protected Player player;
	protected int code;
	protected boolean closed;
	

	public abstract void sendCommand(ServerToClientCommand command) throws IOException;


//	public abstract void notifyNewCommand(CommandClientToServer command);

	public abstract void closedByServer();
	
	public abstract void closedByClient();

	public abstract boolean isClosed();
	
	public abstract void Close();
}
