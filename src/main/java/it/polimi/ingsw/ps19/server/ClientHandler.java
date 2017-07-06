package it.polimi.ingsw.ps19.server;


import java.io.IOException;
import java.rmi.RemoteException;

import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.command.toclient.ServerToClientCommand;
import it.polimi.ingsw.ps19.server.controller.MatchHandlerObserver;

/**
 * A Runnable abstract messages client-server handler to be implemented by classes
 * using different connection types
 *
 * @author Mirko
 */
public abstract class ClientHandler implements Runnable {

	
	/**  Every client handler is associated to a player. */
	protected Player player;
	
	/** The code. */
	protected int code;
	
	/** The closed. */
	protected boolean closed;
	
	/**
	 * Adds the player.
	 *
	 * @param player the player
	 */
	public void addPlayer(Player player) {
		this.player = player;
		System.out.println("clienthandler: mi hanno assegnato il player di colore"+player.getColor());
	}

	/**
	 * Gets the player.
	 *
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}
	

	/**
	 * Send command.
	 *
	 * @param command the command
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public abstract void sendCommand(ServerToClientCommand command) throws IOException;


//	public abstract void notifyNewCommand(CommandClientToServer command);

	/**
 * Closed by server.
 *
 * @throws RemoteException the remote exception
 */
public abstract void closedByServer() throws RemoteException;
	
	/**
	 * Closed by client.
	 */
	public abstract void closedByClient();

	/**
	 * Checks if is closed.
	 *
	 * @return true, if is closed
	 */
	public boolean isClosed(){
		return closed;
	}
	
	/**
	 * Adds an observer to MatchHandler in order to check if a command is valid .
	 *
	 * @param matchObserver the match observer
	 */
	public abstract void addObserver(MatchHandlerObserver matchObserver);
	
	
	/**
	 * Adds the command observer.
	 *
	 * @param commandHandler the command handler
	 */
	public abstract void addCommandObserver(ServerCommandHandler commandHandler);


	
}
