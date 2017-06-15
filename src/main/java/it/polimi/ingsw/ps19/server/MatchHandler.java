package it.polimi.ingsw.ps19.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.polimi.ingsw.ps19.Match;
import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.command.ClientToServerCommand;
import it.polimi.ingsw.ps19.command.ServerToClientCommand;
import it.polimi.ingsw.ps19.command.StartTurnCommand;
import it.polimi.ingsw.ps19.command.toclient.OpponentStatusChangeCommand;
import it.polimi.ingsw.ps19.command.toclient.PlayerStatusChangeCommand;
import it.polimi.ingsw.ps19.exception.NotApplicableException;
import it.polimi.ingsw.ps19.exception.WrongPlayerException;
import it.polimi.ingsw.ps19.model.action.Action;
import it.polimi.ingsw.ps19.server.observers.MatchObserver;

/**
 * @author Mirko
 *
 */
public class MatchHandler implements Runnable, MatchHandlerObserver, MatchObserver {

	private List<ClientHandler> clients;
	private List<ClientHandler> closedClients;
	private ServerCommandHandler commandHandler;
	private ServerInterface ServerInterface;
	private Match match;
	// private TurnTimer timer;
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
	public MatchHandler(List<ClientHandler> clients, ServerInterface ServerInterface) {
		this.clients = clients;
		this.ServerInterface = ServerInterface;
		closedClients = new ArrayList<ClientHandler>();
		System.out.println("match handler: sono stato creato");
	}

	@Override
	public void run() {
		initMatch();
	}

	private void initMatch() {
		try {
		    Thread.sleep(10000);                 //1000 milliseconds is one second.
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
		match = new Match(clients.size(), this);
		// match.setNotifier(this);
		commandHandler = new ServerCommandHandler(this, match);
		setPlayers();
		// notifyAllStartMatch();
		match.setInitialPlayer();
		startTurn();
	}

	/**
	 * 
	 * @return the current id player
	 */
	// public int getCurrentIdPlayer() {
	// return match.getCurrentPlayer().getPlayerId();
	// }

	/**
	 * 
	 * @return the current player of the match
	 */
	public Player getCurrentPlayer() {
		return match.getCurrentPlayer();
	}

	/**
	 * randomly shuffling clients
	 */
	private void setPlayers() {
		Collections.shuffle(clients);
		int i = 1;
		for (ClientHandler c : clients) {
			c.addPlayer(match.createAndReturnPlayer(i));
			c.addObserver(this);
			c.addCommandObserver(commandHandler);
			i++;
		}
	}

	/**
	 * method invoked at the end of a turn that checks if the game is end or set
	 * the next player
	 */
	public void setNext() {
		// Map<Player, Boolean> winners = match.checkWinners();
		// if (winners.isEmpty() && !clients.isEmpty()) {
		// match.setNextPlayer();
		// startTurn();
		// } else if (!winners.isEmpty() && !clients.isEmpty())
		// notifyEndOfGame(winners);
	}

	private void startTurn() {
//		sendToCurrentPlayer(new StartTurnCommand());
		sendToAllPlayers(new StartTurnCommand());
		// notifyCurrentPlayer(new CommandAskMove());
		// createTurnTimer();
	}

	// Method of notification
	/**
	 * method invoked that notify the start of the match
	 */
	// private void notifyAllStartMatch() {
	// for (ClientHandler client : clients)
	// try {
	// client.sendCommand(new CommandStartMatch(match.getBoard()
	// .getBoard(), client.getPlayer(), match.getBoard()
	// .getNameMap()));
	// } catch (Exception e) {
	// LOGGER.fatal(e);
	// closedClients.add(client);
	// }
	// checkDisconnection();
	// }

	/**
	 * notifying command in broadcast
	 * 
	 * @param command
	 *            to be notified
	 */
	public void sendToAllPlayers(ServerToClientCommand command) {
		for (ClientHandler client : clients) {
			try {
				client.sendCommand(command);
			} catch (Exception e) {
				closedClients.add(client);
			}
		}
		// checkDisconnection();
	}

	public void sendToAllPlayersExceptCurrent(ServerToClientCommand command) {
		ClientHandler dontSendClient;
		try {
			dontSendClient = this.getRightClientHandler(match.getCurrentPlayer());
		} catch (WrongPlayerException e1) {
			e1.printStackTrace();
			return;
		}
		for (ClientHandler client : clients) {
			if (client != dontSendClient) {
				try {
					client.sendCommand(command);
				} catch (Exception e) {
					closedClients.add(client);
				}
			}
		}
		// checkDisconnection();
	}

	public void sendToPlayer(ServerToClientCommand command, Player player) {
		ClientHandler client;
		try {
			client = getRightClientHandler(player);
		} catch (WrongPlayerException e1) {
			System.out.println(e1.getError());
			e1.printStackTrace();
			return;
		}

		try {
			client.sendCommand(command);
		} catch (Exception e) {
			closedClients.add(client);
		}

		// checkDisconnection();
	}

	public void sendToCurrentPlayer(ServerToClientCommand command) {
		sendToPlayer(command, match.getCurrentPlayer());
		// checkDisconnection();
	}





	public void notifySetNext() {
		setNext();
	}

	/**
	 * method invoked by the ping timer to check if the current player is always
	 * on
	 */
	// @Override
	// public void turnTimerExpired() {
	// List<ClientHandler> list = new ArrayList<ClientHandler>(clients);
	// for (ClientHandler clientHandler : list)
	// if (clientHandler.getPlayer().equals(getCurrentPlayer())) {
	// closedClients.add(clientHandler);
	// try {
	// clientHandler.sendCommand(new CommandDisconnection());
	// } catch (Exception e) {
	// }
	// }
	// removeClosedClients();
	// }

	/**
	 * method to make the ping timer start
	 */
	// public void createTurnTimer() {
	// timer = new TurnTimer(this);
	// timerThread = new Thread(timer);
	// timerThread.start();
	// }

	/**
	 * method to interrupt the turn timer if it is alive
	 */

	private void checkDisconnection() {
		// if (!closedClients.isEmpty())
		// removeClosedClients();
	}

	/**
	 * close the match and all its connection with client
	 * 
	 */
	public synchronized void closeMatch() {
		// timerNotNeed();
		// if (!clients.isEmpty()) {
		// List<ClientHandler> list = new ArrayList<ClientHandler>();
		// for (ClientHandler clientHandler : clients)
		// list.add(clientHandler);
		// for (ClientHandler clientHandler : list) {
		// clients.remove(clientHandler);
		// if (!clientHandler.getPlayer().isDead())
		// match.killPlayer(clientHandler.getPlayer());
		// clientHandler.closeByServer();
		// }
		// }
		// ServerInterface.notifyClose(this);
	}

	@Override
	public boolean isAllowed(ClientToServerCommand command, Player player) {
		if (getCurrentPlayer() == player)
			return true;
		else
			return false;
	}

	@Override
	public void removeClient(ClientHandler clientHandler) {
		// TODO Auto-generated method stub

	}

	private ClientHandler getRightClientHandler(Player player) throws WrongPlayerException {
		for (ClientHandler client : clients)
			if (client.getPlayer().equals(player))
				return client;

		throw new WrongPlayerException();
	}

	public void applyAction(Action action) throws NotApplicableException {
		action.apply();

	}

	@Override
	public void notifyPlayerStatusChange(Player player) {
		Player currentPlayer = match.getCurrentPlayer();
		if (player == currentPlayer) {
			this.sendToCurrentPlayer(new PlayerStatusChangeCommand(player));
			this.sendToAllPlayers(new OpponentStatusChangeCommand(player.maskedClone()));
		}
	}

	@Override
	public void notifyFamilyPlaced() {

	}

}
