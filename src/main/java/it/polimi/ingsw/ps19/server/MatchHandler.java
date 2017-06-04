package it.polimi.ingsw.ps19.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.polimi.ingsw.ps19.Match;
import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.command.ServerToClientCommand;




public class MatchHandler implements Runnable {

	private List<ClientHandler> clients;
	private List<ClientHandler> closedClients;
	private ServerCommandHandler commandHandler;
	private ServerInterface ServerInterface;
	private Match match;
//	private TurnTimer timer;
	private Thread timerThread;


	/**
	 * This constructor creates the clients and sets the observer to notify the
	 * server
	 * 
	 * @param clients
	 *            connected to the game
	 * @param ServerInterface
	 *            main server to which will be notified the end of the game
	 */
	public MatchHandler(List<ClientHandler> clients,
			ServerInterface ServerInterface) {
		this.clients = clients;
		this.ServerInterface = ServerInterface;
		closedClients = new ArrayList<ClientHandler>();
	}

	@Override
	public void run() {
		initiazializeMatch();
	}

	private void initiazializeMatch() {
//		String map = createIdMap();
//		match = new Match(map, clients.size());
//		match.setNotifier(this);
//		commandHandler = new ServerCommandHandlerImpl(this, match);
//		setPlayers();
//		notifyAllStartMatch();
//		match.setInitialPlayer();
//		startTurn();
	}

	private void sendCommand(ClientHandler client, ServerToClientCommand command) {
		try {
			client.sendCommand(command);
		} catch (Exception e) {
			closedClients.add(client);
		}
		checkDisconnection();
	}



	/**
	 * 
	 * @return the current id player
	 */
//	public int getCurrentIdPlayer() {
//		return match.getCurrentPlayer().getPlayerId();
//	}

	/**
	 * 
	 * @return the current player of the match
	 */
	public Player getCurrentPlayer() {
		return match.getCurrentPlayer();
	}

	/**
	 * set players and their client handler for start the match;
	 */
//	private void setPlayers() {
//		Collections.shuffle(clients);
//		int i = 1;
//		for (ClientHandler c : clients) {
//			c.addPlayer(match.setPlayer(i));
//			c.addAllower(this);
//			c.addCommandHandler(commandHandler);
//			i++;
//		}
//	}

	/**
	 * method invoked at the end of a turn that checks if the game is end or set
	 * the next player
	 */
	public void setNext() {
//		Map<Player, Boolean> winners = match.checkWinners();
//		if (winners.isEmpty() && !clients.isEmpty()) {
//			match.setNextPlayer();
//			startTurn();
//		} else if (!winners.isEmpty() && !clients.isEmpty())
//			notifyEndOfGame(winners);
	}

	private void startTurn() {
	}

	// Method of notification
	/**
	 * method invoked that notify the start of the match
	 */
//	private void notifyAllStartMatch() {
//		for (ClientHandler client : clients)
//			try {
//				client.sendCommand(new CommandStartMatch(match.getBoard()
//						.getBoard(), client.getPlayer(), match.getBoard()
//						.getNameMap()));
//			} catch (Exception e) {
//				LOGGER.fatal(e);
//				closedClients.add(client);
//			}
//		checkDisconnection();
//	}

	/**
	 * notifying command in broadcast
	 * 
	 * @param command
	 *            to be notified
	 */
	public void notifyAllClients(ServerToClientCommand command) {
		for (ClientHandler client : clients) {
			try {
				client.sendCommand(command);
			} catch (Exception e) {
				closedClients.add(client);
			}
		}
		checkDisconnection();
	}



//	@Override
//	public void genericNotifyToAll(String message) {
//		this.notifyAllClients(new CommandNotification(message));
//
//	}


	
	

	/**
	 * This method sends a new command to the current player
	 * 
	 * @param command
	 */
//	public void notifyCurrentPlayer(ServerToClientCommand command) {
//		for (ClientHandler client : clients)
//			if (client.getPlayer().equals(match.getCurrentPlayer())) {
//				sendCommand(client, command);
//				return;
//			}
//	}

	/**
	 * This method notifyies a generic player
	 * 
	 * @param command
	 * @param player
	 */
//	public void notifyGenericPlayer(ServerToClientCommand command, Player player) {
//		for (ClientHandler client : clients)
//			if (client.getPlayer().equals(player))
//				sendCommand(client, command);
//		return;
//	}

	

	

//	public void notifyWinners(Map<Player, Boolean> winners) {
//		notifyEndOfGame(winners);
//	}

	public void notifySetNext() {
		setNext();
	}

	



	

	

	/**
	 * method invoked by the ping timer to check if the current player is always
	 * on
	 */
//	@Override
//	public void turnTimerExpired() {
//		List<ClientHandler> list = new ArrayList<ClientHandler>(clients);
//		for (ClientHandler clientHandler : list)
//			if (clientHandler.getPlayer().equals(getCurrentPlayer())) {
//				closedClients.add(clientHandler);
//				try {
//					clientHandler.sendCommand(new CommandDisconnection());
//				} catch (Exception e) {
//				}
//			}
//		removeClosedClients();
//	}

	/**
	 * method to make the ping timer start
	 */
//	public void createTurnTimer() {
//		timer = new TurnTimer(this);
//		timerThread = new Thread(timer);
//		timerThread.start();
//	}

	/**
	 * method to interrupt the turn timer if it is alive
	 */
	

	private void checkDisconnection() {
//		if (!closedClients.isEmpty())
//			removeClosedClients();
	}

	/**
	 * close the match and all its connection with client
	 * 
	 */
	public synchronized void closeMatch() {
//		timerNotNeed();
//		if (!clients.isEmpty()) {
//			List<ClientHandler> list = new ArrayList<ClientHandler>();
//			for (ClientHandler clientHandler : clients)
//				list.add(clientHandler);
//			for (ClientHandler clientHandler : list) {
//				clients.remove(clientHandler);
//				if (!clientHandler.getPlayer().isDead())
//					match.killPlayer(clientHandler.getPlayer());
//				clientHandler.closeByServer();
//			}
//		}
//		ServerInterface.notifyClose(this);
	}


}
