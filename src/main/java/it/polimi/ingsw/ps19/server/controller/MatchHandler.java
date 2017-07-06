package it.polimi.ingsw.ps19.server.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.polimi.ingsw.ps19.FamilyMember;
import it.polimi.ingsw.ps19.LeaderCardRequirement;
import it.polimi.ingsw.ps19.Match;
import it.polimi.ingsw.ps19.MatchFullException;
import it.polimi.ingsw.ps19.Period;
import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.command.toclient.AskFinishRoundOrDiscardCommand;
import it.polimi.ingsw.ps19.command.toclient.AskForExcommunicationPaymentCommand;
import it.polimi.ingsw.ps19.command.toclient.AskMoveCommand;
import it.polimi.ingsw.ps19.command.toclient.AskPrivilegeChoiceCommand;
import it.polimi.ingsw.ps19.command.toclient.AssignColorCommand;
import it.polimi.ingsw.ps19.command.toclient.ChooseLeaderCardCommand;
import it.polimi.ingsw.ps19.command.toclient.InitializeMatchCommand;
import it.polimi.ingsw.ps19.command.toclient.InitializeTurnCommand;
import it.polimi.ingsw.ps19.command.toclient.InvalidActionCommand;
import it.polimi.ingsw.ps19.command.toclient.InvalidCommand;
import it.polimi.ingsw.ps19.command.toclient.LoseCommand;
import it.polimi.ingsw.ps19.command.toclient.NotifyExcommunicationCommand;
import it.polimi.ingsw.ps19.command.toclient.OpponentStatusChangeCommand;
import it.polimi.ingsw.ps19.command.toclient.PlayerStatusChangeCommand;
import it.polimi.ingsw.ps19.command.toclient.RefreshBoardCommand;
import it.polimi.ingsw.ps19.command.toclient.RoundTimerExpiredCommand;
import it.polimi.ingsw.ps19.command.toclient.ServerToClientCommand;
import it.polimi.ingsw.ps19.command.toclient.WinCommand;
import it.polimi.ingsw.ps19.command.toserver.ProductionActivationCommand;
import it.polimi.ingsw.ps19.command.toserver.ProductionCommand;
import it.polimi.ingsw.ps19.constant.CardConstants;
import it.polimi.ingsw.ps19.constant.FileConstants;
import it.polimi.ingsw.ps19.exception.EveryPlayerDisconnectedException;
import it.polimi.ingsw.ps19.exception.NotApplicableException;
import it.polimi.ingsw.ps19.exception.WrongClientHandlerException;
import it.polimi.ingsw.ps19.exception.WrongPlayerException;
import it.polimi.ingsw.ps19.model.action.Action;
import it.polimi.ingsw.ps19.model.action.IndustrialAction;
import it.polimi.ingsw.ps19.model.area.BoardInitializer;
import it.polimi.ingsw.ps19.model.area.Church;
import it.polimi.ingsw.ps19.model.card.CardType;
import it.polimi.ingsw.ps19.model.card.LeaderCard;
import it.polimi.ingsw.ps19.model.excommunicationtile.ExcommunicationTile;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;
import it.polimi.ingsw.ps19.model.resource.ResourceType;
import it.polimi.ingsw.ps19.server.ClientHandler;
import it.polimi.ingsw.ps19.server.ServerCommandHandler;
import it.polimi.ingsw.ps19.server.ServerInterface;
import it.polimi.ingsw.ps19.server.observers.MatchObserver;

/**
 * The is The Controller of the entire Gameplay, the server is the only one that
 * makes decision about the match, this class has the references to every model object
 * and uses the methods of it, it also decides the commands to send to the clients
 * and handles the commands arrived by every client, an instance of MatchHandler exist for
 * every Match created by the server
 *
 * @author Mirko
 */
public class MatchHandler implements Runnable, MatchHandlerObserver, MatchObserver {

	/** The clients. */
	private List<ClientHandler> clients;
	
	/** The closed clients. */
	private List<ClientHandler> closedClients;
	
	/** The command handler. */
	private ServerCommandHandler commandHandler;
	
	/** The Server interface. */
	private ServerInterface ServerInterface;
	
	/** The match. */
	private Match match;
	
	/** The round timer thread. */
	// private RoundTimer roundTimer;
	private Thread roundTimerThread;
	
	/** The leader response counter. */
	private int leaderResponseCounter = 0;
	
	/** The leader sets. */
	private ArrayList<ArrayList<LeaderCard>> leaderSets;
	
	/** The cycle. */
	private int cycle = 1;
	
	/** The round number. */
	private int roundNumber = 0;
	
	/** The last command sent. */
	private ServerToClientCommand lastCommandSent;
	
	/** The num players answered excomm. */
	private int numPlayersAnsweredExcomm;

	/** The prod family member. */
	private String prodFamilyMember;
	
	/** The prod action space. */
	private int prodActionSpace;
	
	/** The prod paid servant. */
	private int prodPaidServant;

	/**
	 * Instantiates a new match handler.
	 *
	 * @param clients the clients
	 * @param ServerInterface the server interface
	 */
	public MatchHandler(List<ClientHandler> clients, ServerInterface ServerInterface) {
		this.clients = clients;
		this.ServerInterface = ServerInterface;
		closedClients = new ArrayList<ClientHandler>();
		// System.out.println("match handler: sono stato creato");
		// leaderSets = match.getLeaderCards()
		// .getStartingLeaderSets(match.getPlayers().length);
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		initMatch();
	}

	/**
	 * Inits the match.
	 */
	private void initMatch() {

		match = new Match(clients.size(), this);
		// match.setNotifier(this);
		commandHandler = new ServerCommandHandler(this, match);
		setPlayers();
		// notifyAllStartMatch();

		communicateColors();

		match.setInitialPlayer();
		// asking credentials to everyone ma se facciamo riconnessione alla
		// partita deve essere
		// chiesto ancora prima, dal server
		// startLeaderDiscardPhase(); // dovrebbe esserci questo
		// provaPlayer();
		// match.handlePeriodsAndTurns();
		startMatch();
		// startMatch(); non parte qui ma dopo aver scartato i familiari

		// provaLeaderPlayer();
	}

	// private void provaLeaderPlayer() {
	// match.getPlayers()[0].addLeaderCards(match.getLeaderCards().getCard(0));
	// match.getPlayers()[0].addLeaderCards(match.getLeaderCards().getCard(1));
	// try {
	// this.sendToClientHandler(new
	// PlayerStatusChangeCommand(match.getPlayers()[0]),
	// this.getRightClientHandler(match.getPlayers()[0]));
	// } catch (WrongPlayerException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

	/**
	 * Communicate colors.
	 */
	private void communicateColors() {
		for (ClientHandler c : clients) {
			String color = null;
			try {
				color = this.getRightPlayer(c).getColor();
			} catch (WrongClientHandlerException e) {
				e.printStackTrace();
			}
			sendToClientHandler(new AssignColorCommand(color), c);
		}

	}

	/**
	 * Start leader discard phase.
	 */
	private void startLeaderDiscardPhase() {
		leaderSets = match.getLeaderCards().getStartingLeaderSets(match.getPlayers().length);

		// System.out.println("matchhandler: lunghezza leadersets" +
		// leaderSets.size());

		for (int i = 0; i < clients.size(); i++) {
			sendToClientHandler(new ChooseLeaderCardCommand(leaderSets.get(i)), clients.get(i));
			// System.out.println("matchHH : creato comando da
			// inv"+leaderSets.get(i).get(0).toString());
		}

	}

	/**
	 * Start match.
	 *
	 * @return the current id player
	 */
	// public int getCurrentIdPlayer() {
	// return match.getCurrentPlayer().getPlayerId();
	// }

	private void startMatch() {
		sendToAllPlayers(new InitializeMatchCommand(match.getPlayers().length));
		sendToAllPlayers(new RefreshBoardCommand(match.getBoard()));
//		startLeaderDiscardPhase();
		
		 startTurn();
		// notifyCurrentPlayer(new CommandAskMove());
		// createTurnTimer();
	}

	/**
	 * Gets the current player.
	 *
	 * @return the current player of the match
	 */
	public Player getCurrentPlayer() {
		return match.getCurrentPlayer();
	}

	/**
	 * randomly shuffling clients.
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

		match.setPlayerOrder();
	}

	/**
	 * method invoked at the end of a turn that checks if the game is end or set
	 * the next player.
	 */
	public void setNext() {
		// Map<Player, Boolean> winners = match.checkWinners();
		// if (winners.isEmpty() && !clients.isEmpty()) {
		try {
			match.setNextPlayer();
		} catch (EveryPlayerDisconnectedException e) {
			closeMatch();
			e.printStackTrace();
		}

		// } else if (!winners.isEmpty() && !clients.isEmpty())
		// notifyEndOfGame(winners);
	}

	/**
	 * Start turn.
	 */
	private void startTurn() {
		// sendToCurrentPlayer(new StartTurnCommand());
		
		
		match.handlePeriodsAndTurns();
		if (match.getTurn() == 7) {
			handleEndGame();
		} else {
			match.incrementTurn();

			initTurn();

			sendToAllPlayers(new InitializeTurnCommand(match.getPeriod(), match.getTurn()));

			sendToAllPlayers(new RefreshBoardCommand(match.getBoard()));

			match.distributeTurnResources(); // this needs to be here and not in
												// initTurn,
			// otherwise the GUI wouldn't have a playerResources Panel to add
			// resources into

			startRound();
			// notifyCurrentPlayer(new CommandAskMove());
			// createTurnTimer();
		}

	}

	/**
	 * Inits the turn.
	 */
	private void initTurn() {
		refreshPlayerOrder();
		roundNumber = 0;
		// System.out.println("rollo i dadi");

		match.clearBoard();

		match.getBoard().rollDices();
		match.refreshDicesValueForPlayers();
		match.addFamilyMembersToPlayers();

		this.match.getBoard().changeCardInTowers();

		// TODO ripulire il board dai family members
	}

	/**
	 * Start round.
	 */
	private void startRound() {
		stopTimerIfAlive();

		roundNumber++;
		sendToCurrentPlayer(new AskMoveCommand());
		startRoundTimer();
	}

	/**
	 * notifying command in broadcast.
	 *
	 * @param command            to be notified
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

	/**
	 * Send to all players except current.
	 *
	 * @param command the command
	 */
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

	/**
	 * Send to player.
	 *
	 * @param command the command
	 * @param player the player
	 */
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
			lastCommandSent = command;
		} catch (Exception e) {
			closedClients.add(client);
		}

		// checkDisconnection();
	}

	/**
	 * Send to current player.
	 *
	 * @param command the command
	 */
	public void sendToCurrentPlayer(ServerToClientCommand command) {
		sendToPlayer(command, match.getCurrentPlayer());
		// checkDisconnection();
	}

	/**
	 * method invoked by the ping timer to check if the current player is always
	 * on.
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
	public void startRoundTimer() {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(FileConstants.ROUND_TIME));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		int timeMillis = 0;
		try {
			timeMillis = Integer.parseInt(reader.readLine());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("\n\n Marchhandler: new thread for roundtimer\n\n");
		roundTimerThread = new Thread(new RoundTimer(this, timeMillis));
		roundTimerThread.start();
	}

	/**
	 * method to interrupt the turn timer if it is alive.
	 */

	private void checkDisconnection() {
		// if (!closedClients.isEmpty())
		// removeClosedClients();
	}

	/**
	 * close the match and all its connection with client.
	 */
	public synchronized void closeMatch() {
		// timerNotNeed();
		if (!clients.isEmpty()) {
			for (ClientHandler clientHandler : clients) {
//				clients.remove(clientHandler);
				try {
					clientHandler.closedByServer();
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		}
		ServerInterface.closeMatch(this);
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.server.controller.MatchHandlerObserver#isAllowed(it.polimi.ingsw.ps19.Player)
	 */
	@Override
	public boolean isAllowed(Player player) {
		if (getCurrentPlayer() != null) {
			if (getCurrentPlayer() == player)
				return true;
			else
				return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.server.controller.MatchHandlerObserver#removeClient(it.polimi.ingsw.ps19.server.ClientHandler)
	 */
	@Override
	public void removeClient(ClientHandler clientHandler) {
		try {
			this.match.addDisconnectedPlayer(getRightPlayer(clientHandler));
		} catch (MatchFullException e) {
			System.out.println("Disconnected more players than the ones in the game");
			e.printStackTrace();
		} catch (WrongClientHandlerException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets the right client handler.
	 *
	 * @param player the player
	 * @return the right client handler
	 * @throws WrongPlayerException the wrong player exception
	 */
	private ClientHandler getRightClientHandler(Player player) throws WrongPlayerException {
		for (ClientHandler client : clients)
			if (client.getPlayer().equals(player))
				return client;

		throw new WrongPlayerException();
	}

	/**
	 * Apply action.
	 *
	 * @param action the action
	 * @throws NotApplicableException the not applicable exception
	 */
	public void applyAction(Action action) throws NotApplicableException {
		action.apply();
		sendToAllPlayers(new RefreshBoardCommand(match.getBoard()));

		if (match.getCurrentPlayer().getCouncilPrivilege() != 0) {
			sendPrivilegeToCurrentPlayer(match.getCurrentPlayer().getCouncilPrivilege());

			match.getCurrentPlayer().resetPrivileges();

		} else {
			sendToCurrentPlayer(new AskFinishRoundOrDiscardCommand());
		}

		// TODO MANDARE comando per scegliere terminare turno o scartare
		// leadercards

	}

	/**
	 * Apply action.
	 *
	 * @param choices the choices
	 * @param industrialAction the industrial action
	 * @throws NotApplicableException the not applicable exception
	 */
	private void applyAction(List<Integer> choices, IndustrialAction industrialAction) throws NotApplicableException {
		industrialAction.apply(choices);
	}

	// @Override
	// public void notifyPlayerStatusChange(Player player) {
	// System.out.println("matchhandler: notifyplayer status change");
	//
	// Player currentPlayer = match.getCurrentPlayer();
	// if (player == currentPlayer) {
	// this.sendToCurrentPlayer(new PlayerStatusChangeCommand(player));
	// this.sendToAllPlayers(new
	// OpponentStatusChangeCommand(player.maskedClone()));
	// }
	// }

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.server.observers.MatchObserver#notifyPlayerStatusChange(it.polimi.ingsw.ps19.Player)
	 */
	@Override
	public void notifyPlayerStatusChange(Player player) {
		try {
			// System.out.println("matchhandler: invio player:" +
			// player.toString());
			this.sendToClientHandler(new PlayerStatusChangeCommand(player), this.getRightClientHandler(player));
		} catch (WrongPlayerException e) {
			e.printStackTrace();
		}
		this.sendToAllPlayers(new OpponentStatusChangeCommand(player.maskedClone()));
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.server.observers.MatchObserver#notifyFamilyPlaced()
	 */
	@Override
	public void notifyFamilyPlaced() {

	}

	/**
	 * Round timer expired.
	 */
	public void roundTimerExpired() {
		sendToCurrentPlayer(new RoundTimerExpiredCommand());
		try {
			closedClients.add(getRightClientHandler(getCurrentPlayer()));
		} catch (WrongPlayerException e) {
			System.out.println(e.getError());
			e.printStackTrace();
		}
		try {
			this.match.addDisconnectedPlayer(getCurrentPlayer());
		} catch (MatchFullException e) {
			e.printStackTrace();
		}
		if(match.isAnyoneStillPlaying()){
		setNext();
		nextStep();
		}
		else {
			this.match=null;
		}
	}

	/**
	 * Next step.
	 */
	private void nextStep() {
		if ((roundNumber == match.getPlayers().length * 4)||currentPlayerWithoutFamilyMembers()) {
			System.out.println("roundNumber= " + roundNumber + "\n cambio turno");
			if (match.getTurn() % 2 == 1) {
				System.out.println(match.getTurn() + "ho fatto modulo due");
				startTurn();
			} else {
				startExcommunicationPhase();
				System.out.println("sono nell'else di modulo due, inizia l'excommphase" + match.getTurn());
			}
		} else
			startRound();

	}

	/**
	 * Current player without family members.
	 *
	 * @return true, if successful
	 */
	private boolean currentPlayerWithoutFamilyMembers() {
		if(this.match.getCurrentPlayer().getFamilyMembers().isEmpty()){
			return true;
		}
		return false;
	}

	/**
	 * Start excommunication phase.
	 */
	private void startExcommunicationPhase() {
		
		stopTimerIfAlive();

		ExcommunicationTile excommTile = getCurrentExcommTile();

		sendToAllPlayers(new AskForExcommunicationPaymentCommand(excommTile.getEffect().toString()));
	}

	/**
	 * Stop timer if alive.
	 */
	private void stopTimerIfAlive() {
		if (roundTimerThread != null)
			if (roundTimerThread.isAlive())
				roundTimerThread.interrupt();
	}

	/**
	 * Handle credentials.
	 *
	 * @param username the username
	 * @param password the password
	 * @param clientHandler the client handler
	 */
	public void handleCredentials(String username, String password, ClientHandler clientHandler) {
		// for now it's just setting the name of the user
		try {
			getRightPlayer(clientHandler).setName(username);
		} catch (WrongClientHandlerException e) {
			sendToClientHandler(new InvalidCommand(), clientHandler);

		}

	}

	/**
	 * Send to client handler.
	 *
	 * @param command the command
	 * @param clientHandler the client handler
	 */
	private void sendToClientHandler(ServerToClientCommand command, ClientHandler clientHandler) {
		try {
			clientHandler.sendCommand(command);
		} catch (IOException e) {
			e.printStackTrace();
			closedClients.add(clientHandler);
		}
	}

	/**
	 * Gets the right player.
	 *
	 * @param clientHandler the client handler
	 * @return the right player
	 * @throws WrongClientHandlerException the wrong client handler exception
	 */
	private Player getRightPlayer(ClientHandler clientHandler) throws WrongClientHandlerException {
		for (ClientHandler c : clients) {
			if (c == clientHandler)
				return c.getPlayer();
		}
		throw new WrongClientHandlerException();

	}

	/**
	 * Gets the player from color.
	 *
	 * @param playerColor the player color
	 * @return the player from color
	 */
	private Player getPlayerFromColor(String playerColor) {
		Player[] players = this.match.getPlayers();
		for (int i = 0; i < players.length; i++) {
			// System.out.println("matchhandler: getplayerfromcolor: p" +
			// "layer.getcolor:" + players[i].getColor()
			// + "playerColor:" + playerColor);
			if (players[i].getColor().equals(playerColor))
				return players[i];
		}
		// System.out.println("matchhandler: getplayerfromcolor: sto ritornando
		// null");
		return null;
	}

	/**
	 * Handle leader choice.
	 *
	 * @param name the name
	 * @param playerColor the player color
	 */
	public void handleLeaderChoice(String name, String playerColor) {
		System.out.println("matchhandler: sono in handleleaderchoice");
		leaderResponseCounter++;
		try {
			// System.out.println("matchhandler: cerco di aggiungere la carta di
			// nome:");
			// System.out.println("matchhandler: handleleaderchoice:
			// leadername:" + name + "playercolor: " + playerColor);

			this.getPlayerFromColor(playerColor).addLeaderCards(match.getLeaderCards().getCard(name));

			removeLeaderFromSets(match.getLeaderCards().getCard(name));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.println("matchhandler: leaderresponsecounter=" +
		// leaderResponseCounter);
		// System.out.println("matchhandler: giocatori:=" +
		// match.getPlayers().length);
		if (leaderResponseCounter == match.getPlayers().length) {
			// System.out.println("matchhandler: entro nell'if di quando tutti e
			// quattro hanno scelto");

			leaderResponseCounter = 0;
			for (int i = 0; i < clients.size(); i++) {
				if (cycle == 3) {
					// System.out.println("matchhandler: sono nell'if perchè
					// cycle ="+cycle);
					try {
						this.getRightPlayer(clients.get((i + cycle) % (match.getPlayers().length)))
								.addLeaderCards(leaderSets.get(i).get(0));
					} catch (WrongClientHandlerException e) {
						e.printStackTrace();
					}
				} else {
					// System.out.println("matchhandler: sono nell'else perchè
					// cycle ="+cycle);
					if (i >= match.getPlayers().length - cycle) {
						sendToClientHandler(new ChooseLeaderCardCommand(leaderSets.get(i)),
								clients.get((i + cycle) % (match.getPlayers().length)));
					} else {
						sendToClientHandler(new ChooseLeaderCardCommand(leaderSets.get(i)), clients.get(i + cycle));
					}
				}
			}
			cycle++;
			if (cycle == 4)
				// startMatch();
				startTurn();
		}

	}

	/**
	 * Removes the leader from sets.
	 *
	 * @param leaderCard the leader card
	 */
	private void removeLeaderFromSets(LeaderCard leaderCard) {
		// System.out.println("matchhandler: sono in remove leadercard");
		for (ArrayList<LeaderCard> set : leaderSets) {
			for (LeaderCard card : set) {
				if (leaderCard == card) {
					set.remove(card);
					return;
				}
			}
		}
	}

	/**
	 * Finish round.
	 */
	public void finishRound() {
		setNext();
		nextStep();
	}

	/**
	 * Handle end game.
	 */
	private void handleEndGame() {
		Player[] rank = new Player[match.getPlayers().length];
		Player prevPlayer;
		for (int i = 0; i < match.getPlayers().length; i++) {
			int val = calculatePlayerPoints(match.getPlayers()[i]);
			rank[i] = match.getPlayers()[i];
			if (val > calculatePlayerPoints(match.getPlayers()[i - 1]) && i > 0) {
				prevPlayer = rank[i - 1];
				rank[i - 1] = match.getPlayers()[i];
				rank[i] = prevPlayer;
			}
		}
		sendToPlayer(new WinCommand(), rank[0]);
		for (Player p : rank) {
			if (p != rank[0]) {
				sendToPlayer(new LoseCommand(), p);
			}
		}

	}

	/**
	 * Calculate player points.
	 *
	 * @param p the p
	 * @return the int
	 */
	private int calculatePlayerPoints(Player p) {
		int points = 0;
		for (Player player : match.getPlayers()) {
			if (player == p) {
				points = points + p.getResourceChest().getResourceInChest(ResourceType.VICTORYPOINT).getAmount();
				points = points + calculatePointsFromResources(player);
				points = points + calculatePointsForTerritories(player);
				points = points + calculatePointsForCharacterCards(player);
				try {
					points = points + calculatePointsForMilitaryPoints(player);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return points;
		}

		return points;
	}

	/**
	 * Calculate points from resources.
	 *
	 * @param p the p
	 * @return the int
	 */
	private int calculatePointsFromResources(Player p) {
		int ResourceSum = 0;
		for (ResourceType r : ResourceType.values()) {
			if (r != ResourceType.VICTORYPOINT && r != ResourceType.FAITHPOINT && r != ResourceType.MILITARYPOINT) {
				ResourceSum = ResourceSum + p.getResourceChest().getResourceInChest(r).getAmount();
			}
		}
		return ResourceSum / 5;
	}

	/**
	 * Calculate points for territories.
	 *
	 * @param p the p
	 * @return the int
	 */
	private int calculatePointsForTerritories(Player p) {
		int points = 0;
		ArrayList<Integer> territoryBonuses = new ArrayList<Integer>();
		try {
			territoryBonuses = BoardInitializer.playerBoardBonusesForTerritory();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < p.getDeckOfType(CardType.TERRITORY).size(); i++) {
			points = points + territoryBonuses.get(i);
		}

		return points;
	}

	/**
	 * Calculate points for character cards.
	 *
	 * @param p the p
	 * @return the int
	 */
	private int calculatePointsForCharacterCards(Player p) {
		int points = 0;
		ArrayList<Integer> characterBonuses = new ArrayList<Integer>();
		try {
			characterBonuses = BoardInitializer.playerBoardBonusesForCharacter();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < p.getDeckOfType(CardType.CHARACTER).size(); i++) {
			points = points + characterBonuses.get(i);
		}

		return points;
	}

	/**
	 * Calculate points for military points.
	 *
	 * @param p the p
	 * @return the int
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private int calculatePointsForMilitaryPoints(Player p) throws IOException {
		Player[] rank = new Player[match.getPlayers().length];
		Player prevPlayer;
		int points = 0;
		for (int i = 0; i < match.getPlayers().length; i++) {
			int val = match.getPlayers()[i].getResourceChest().getResourceInChest(ResourceType.MILITARYPOINT)
					.getAmount();
			rank[i] = match.getPlayers()[i];
			if (val > match.getPlayers()[i - 1].getResourceChest().getResourceInChest(ResourceType.MILITARYPOINT)
					.getAmount() && i > 0) {
				prevPlayer = rank[i - 1];
				rank[i - 1] = match.getPlayers()[i];
				rank[i] = prevPlayer;
			}
		}

		BufferedReader reader;

		reader = new BufferedReader(new FileReader(FileConstants.VICTORYFORMILITARY));

		int[] pointsFromFile = new int[match.getPlayers().length];

		for (int i = 0; i < pointsFromFile.length; i++) {
			pointsFromFile[i] = Integer.parseInt(reader.readLine());
			if (rank[i] == p) {
				points = pointsFromFile[i];
			}
		}

		return points;
	}

	/**
	 * Handle invalid command.
	 */
	public void handleInvalidCommand() {
		sendToCurrentPlayer(lastCommandSent);

	}

	/**
	 * Send privilege to current player.
	 *
	 * @param numberOfPrivilege the number of privilege
	 */
	public void sendPrivilegeToCurrentPlayer(int numberOfPrivilege) {
		ResourceChest[] rc = null;
		try {
			rc = BoardInitializer.createPrivilegeResources(CardConstants.PRIVILEGE_RESOURCES);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ArrayList<ResourceChest> arrayListPrivilege = getArrayListPrivilegeFromArray(rc);

		sendToCurrentPlayer(new AskPrivilegeChoiceCommand(numberOfPrivilege, arrayListPrivilege));

	}

	/**
	 * Discard leader card.
	 *
	 * @param leaderName the leader name
	 */
	public void discardLeaderCard(String leaderName) {
		match.getCurrentPlayer().removeLeaderCard(leaderName);
		sendPrivilegeToCurrentPlayer(1);
	}

	/**
	 * Gets the array list privilege from array.
	 *
	 * @param rc the rc
	 * @return the array list privilege from array
	 */
	private ArrayList<ResourceChest> getArrayListPrivilegeFromArray(ResourceChest[] rc) {
		ArrayList<ResourceChest> arr = new ArrayList<ResourceChest>();
		for (int i = 0; i < rc.length; i++) {
			arr.add(rc[i]);
		}
		return arr;
	}

	/**
	 * Adds the privilege resources.
	 *
	 * @param choice the choice
	 */
	public void addPrivilegeResources(ArrayList<Integer> choice) {

		if (isPrivilegeCorrect(choice)) {
			ResourceChest[] rc = null;
			try {
				rc = BoardInitializer.createPrivilegeResources(CardConstants.PRIVILEGE_RESOURCES);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			ResourceChest resourcesToGive = new ResourceChest();

			for (int i = 0; i < choice.size(); i++) {
				resourcesToGive.addChest(rc[choice.get(i)]);
			}
			this.getCurrentPlayer().addResources(resourcesToGive);

			sendToCurrentPlayer(new AskFinishRoundOrDiscardCommand());

		} else {
			sendToCurrentPlayer(new InvalidActionCommand("You modified the code and "
					+ "tried to get resources from a council privilege which are not valid in the game"));
			sendToCurrentPlayer(new AskFinishRoundOrDiscardCommand());
		}

	}

	/**
	 * Checks if is privilege correct.
	 *
	 * @param choice the choice
	 * @return true, if is privilege correct
	 */
	private boolean isPrivilegeCorrect(ArrayList<Integer> choice) {
		if (privilegeChoiceHasDuplicates(choice))
			return false;
		else
			return true;
	}

	/**
	 * Privilege choice has duplicates.
	 *
	 * @param choice the choice
	 * @return true, if successful
	 */
	private boolean privilegeChoiceHasDuplicates(ArrayList<Integer> choice) {
		for (int i = 0; i < choice.size(); i++) {
			for (int j = 0; j < choice.size(); j++) {
				if (i != j && choice.get(i) == choice.get(j)) {
					return true;
				}
			}
		}
		return false;

	}

	/**
	 * Refresh player order.
	 */
	private void refreshPlayerOrder() {

		Player[] oldList = match.getPlayers();

		ArrayList<FamilyMember> councilMemberList = match.getBoard().getCouncilPalace().getMembers();

		System.out.println(councilMemberList.toString());

		ArrayList<Player> councilPlayers = new ArrayList<Player>();
		System.out.println("sono nella refresh player order");

		if (!councilMemberList.isEmpty()) {
			System.out.println("sono nell if della refresh player");
			for (int i = 0; i < councilMemberList.size(); i++) {
				councilPlayers.add(councilMemberList.get(i).getPlayer());
			}
			System.out.println("ho ricavato i player che hanno giocato nel council palace");
			for (int i = 0; i < councilPlayers.size(); i++) {
				for (int j = 0; j < councilPlayers.size(); j++) {
					if (i != j && councilPlayers.get(i) == councilPlayers.get(j)) {
						councilPlayers.remove(j);
					}
				}
			}
			System.out.println("ho rimosso i duplicati");
			for (int i = 0; i < oldList.length; i++) {
				if (!councilPlayers.contains(oldList[i])) {
					councilPlayers.add(oldList[i]);
				}
			}
			System.out.println("ho aggiunto chi non ha giocato nel councilPalace");
			Player[] newList = new Player[oldList.length];
			for (int i = 0; i < councilPlayers.size(); i++) {
				newList[i] = councilPlayers.get(i);
			}

			match.setPlayers(newList);

			System.out.println("questo è il nuovo ordine");
			for (int i = 0; i < councilPlayers.size(); i++)
				System.out.println(newList[i].toString() + "\n");
		}
		
	}

	/**
	 * Gets the round number.
	 *
	 * @return the round number
	 */
	public int getRoundNumber() {
		return roundNumber;
	}

	/**
	 * Handle church support decision.
	 *
	 * @param playerColor the player color
	 * @param decision the decision
	 */
	public void handleChurchSupportDecision(String playerColor, boolean decision) {
		numPlayersAnsweredExcomm++;
		if (decision) {
			System.out.println("Non ho scomunicato il giocatore" + playerColor);
			this.getPlayerFromColor(playerColor).payFaithPoint();
			ResourceChest rc = new ResourceChest();
			rc.addResource(match.getChurchSupportPrizeInPeriod());
			this.getPlayerFromColor(playerColor).addResources(rc);
		} else {
			System.out.println("Scomunico il giocatore" + playerColor);
			ExcommunicationTile tile;
			tile = this.getCurrentExcommTile();
			tile.getEffect().applyEffect(getPlayerFromColor(playerColor));
			System.out.println("Ho scomunicato il giocatore" + playerColor);

			if (match.getPeriod() == Period.FIRST)
				this.getPlayerFromColor(playerColor).setExcommunicatedFirst(true);
			else if (match.getPeriod() == Period.SECOND) {
				this.getPlayerFromColor(playerColor).setExcommunicatedSecond(true);
			} else if (match.getPeriod() == Period.SECOND) {
				this.getPlayerFromColor(playerColor).setExcommunicatedThird(true);
			}

			this.sendToPlayer(new NotifyExcommunicationCommand(), this.getPlayerFromColor(playerColor));
			System.out.println("matchHandler: excommunicationCommandSent");
		}

		if (numPlayersAnsweredExcomm == this.match.getPlayers().length) {
			numPlayersAnsweredExcomm = 0;
			startTurn();
		}

	}

	/**
	 * Gets the current excomm tile.
	 *
	 * @return the current excomm tile
	 */
	private ExcommunicationTile getCurrentExcommTile() {
		Church c = this.match.getBoard().getChurch();
		Period p = this.match.getPeriod();
		ExcommunicationTile excommTile = c.getExcommunicationTile(p);
		return excommTile;
	}

	/**
	 * Save production params.
	 *
	 * @param command the command
	 */
	public void saveProductionParams(ProductionCommand command) {
		this.prodActionSpace = command.getActionSpace();
		this.prodPaidServant = command.getPaidServants();
		this.prodFamilyMember = command.getFamilyMember();

	}

	/**
	 * Handle production activation.
	 *
	 * @param productionActivationCommand the production activation command
	 */
	public void handleProductionActivation(ProductionActivationCommand productionActivationCommand) {

		FamilyMember member = getCurrentPlayer().getFamilyMember(this.prodFamilyMember);

		if (this.prodActionSpace == 1) {
			try {
				this.applyAction(productionActivationCommand.getChoices(),
						new IndustrialAction(member, match.getBoard().getProductionArea(),
								match.getBoard().getProductionArea().getSingleActionSpace(), this.prodPaidServant));
			} catch (NotApplicableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				this.applyAction(productionActivationCommand.getChoices(),
						new IndustrialAction(member, match.getBoard().getProductionArea(),
								match.getBoard().getProductionArea().getMultipleActionSpace(), this.prodPaidServant));
			} catch (NotApplicableException e) {
				sendToCurrentPlayer(new InvalidActionCommand(e.getNotApplicableCode()));
				sendToCurrentPlayer(new AskMoveCommand());
			}
		}
		
		if (match.getCurrentPlayer().getCouncilPrivilege() != 0) {
			sendPrivilegeToCurrentPlayer(match.getCurrentPlayer().getCouncilPrivilege());

			match.getCurrentPlayer().resetPrivileges();

		} else {
			sendToCurrentPlayer(new AskFinishRoundOrDiscardCommand());
		}

		this.prodActionSpace = 0;
		this.prodPaidServant = 0;
		this.prodFamilyMember = "";
	}

	/**
	 * Deactivate leader cards.
	 */
	public void deactivateLeaderCards() {
		if (!this.getCurrentPlayer().getLeaderCards().isEmpty())
			for (LeaderCard l : this.getCurrentPlayer().getLeaderCards().values()) {
				l.getSpecialEffect().disapplyEffect(getCurrentPlayer());
			}
	}

	/**
	 * Client closed the game.
	 *
	 * @param playerColor the player color
	 */
	public void clientClosedTheGame(String playerColor) {
		try {
			match.addDisconnectedPlayer(getPlayerFromColor(playerColor));
		} catch (MatchFullException e) {
			e.printStackTrace();
		}
	}

	public boolean isLeaderCardActivable(String leaderName) {
		LeaderCard leader = match.getLeaderCards().getCard(leaderName);
		ResourceChest resourcesRequired = leader.getRequirement().getResourcesRequired();
		int buildingCardRequired = leader.getRequirement().getBuildingCardRequired();
		int characterCardRequired = leader.getRequirement().getCharacterCardRequired();
		int ventureCardRequired = leader.getRequirement().getVentureCardRequired();
		int territoryCardRequired = leader.getRequirement().getTerritoryCardRequired();
		int anyCardRequired = leader.getRequirement().getAnyCardRequired();
		
		int totalCards = match.getCurrentPlayer().getDeckOfType(CardType.BUILDING).size()  
				+ match.getCurrentPlayer().getDeckOfType(CardType.CHARACTER).size()  
				+ match.getCurrentPlayer().getDeckOfType(CardType.VENTURE).size() 
				+ match.getCurrentPlayer().getDeckOfType(CardType.TERRITORY).size();
		
		ResourceChest cloned = match.getCurrentPlayer().getResourceChest().cloneChest();
		cloned.subChest(resourcesRequired);
		if(!cloned.isGreaterEqualThan(new ResourceChest(0,0,0,0,0,0,0)) ||
		match.getCurrentPlayer().getDeckOfType(CardType.BUILDING).size() < buildingCardRequired 
		|| match.getCurrentPlayer().getDeckOfType(CardType.CHARACTER).size() < characterCardRequired 
		|| match.getCurrentPlayer().getDeckOfType(CardType.VENTURE).size() < ventureCardRequired
		|| match.getCurrentPlayer().getDeckOfType(CardType.TERRITORY).size() < territoryCardRequired
		|| totalCards < anyCardRequired)
			return false;
		else 
			return true;
		
		
		
	}

}
