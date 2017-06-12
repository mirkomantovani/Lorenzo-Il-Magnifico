package it.polimi.ingsw.ps19.server;


import java.io.IOException;
import java.rmi.RemoteException;

import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.command.ServerToClientCommand;

/**
 * @author Mirko
 *
 */
public abstract class ClientHandler implements Runnable {

	
	/**
	 *  Every client handler is associated to a player
	 */
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
	
	/**
	 * Adds an observer to MatchHandler in order to check if a command is valid 
	 * @param matchObserver
	 */
	public abstract void addObserver(MatchHandlerObserver matchObserver);
	
	
	public abstract void addCommandHandler(ServerCommandHandler commandHandler);

	
}
