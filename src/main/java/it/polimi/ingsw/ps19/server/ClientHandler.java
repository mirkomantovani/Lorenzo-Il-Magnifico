package it.polimi.ingsw.ps19.server;


import java.io.IOException;
import java.rmi.RemoteException;

import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.command.ServerToClientCommand;

public abstract class ClientHandler implements Runnable {

	
	protected Player player;
	protected int code;
	protected boolean closed;
	

	public abstract void sendCommand(ServerToClientCommand command) throws IOException;


//	public abstract void notifyNewCommand(CommandClientToServer command);

	public abstract void closedByServer() throws RemoteException;
	
	public abstract void closedByClient();

	public boolean isClosed(){
		return closed;
	}
	
	public abstract void close();
}
