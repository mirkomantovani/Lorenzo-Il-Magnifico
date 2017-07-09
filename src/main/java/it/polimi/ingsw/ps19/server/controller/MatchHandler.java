package it.polimi.ingsw.ps19.server.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.Executors;

import it.polimi.ingsw.ps19.FamilyMember;
import it.polimi.ingsw.ps19.Match;
import it.polimi.ingsw.ps19.MatchFullException;
import it.polimi.ingsw.ps19.MatchSaver;
import it.polimi.ingsw.ps19.Period;
import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.command.toclient.AskAuthenticationCommand;
import it.polimi.ingsw.ps19.command.toclient.AskFinishRoundOrDiscardCommand;
import it.polimi.ingsw.ps19.command.toclient.AskForExcommunicationPaymentCommand;
import it.polimi.ingsw.ps19.command.toclient.AskMoveCommand;
import it.polimi.ingsw.ps19.command.toclient.AskPrivilegeChoiceCommand;
import it.polimi.ingsw.ps19.command.toclient.AskSatanMoveCommand;
import it.polimi.ingsw.ps19.command.toclient.AssignColorCommand;
import it.polimi.ingsw.ps19.command.toclient.AuthenticatedCorrectlyCommand;
import it.polimi.ingsw.ps19.command.toclient.ChooseLeaderCardCommand;
import it.polimi.ingsw.ps19.command.toclient.InitializeMatchCommand;
import it.polimi.ingsw.ps19.command.toclient.InitializeTurnCommand;
import it.polimi.ingsw.ps19.command.toclient.InvalidActionCommand;
import it.polimi.ingsw.ps19.command.toclient.InvalidCommand;
import it.polimi.ingsw.ps19.command.toclient.LoseCommand;
import it.polimi.ingsw.ps19.command.toclient.NotifyExcommunicationCommand;
import it.polimi.ingsw.ps19.command.toclient.NotifySatanActionCommand;
import it.polimi.ingsw.ps19.command.toclient.OpponentStatusChangeCommand;
import it.polimi.ingsw.ps19.command.toclient.PlayerDisconnectedCommand;
import it.polimi.ingsw.ps19.command.toclient.PlayerStatusChangeCommand;
import it.polimi.ingsw.ps19.command.toclient.RefreshBoardCommand;
import it.polimi.ingsw.ps19.command.toclient.RoundTimerExpiredCommand;
import it.polimi.ingsw.ps19.command.toclient.ServerToClientCommand;
import it.polimi.ingsw.ps19.command.toclient.WinCommand;
import it.polimi.ingsw.ps19.command.toclient.WrongPasswordCommand;
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
import it.polimi.ingsw.ps19.model.effect.Effect;
import it.polimi.ingsw.ps19.model.effect.InstantResourcesEffect;
import it.polimi.ingsw.ps19.model.excommunicationtile.ExcommunicationTile;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;
import it.polimi.ingsw.ps19.model.resource.ResourceType;
import it.polimi.ingsw.ps19.server.ClientHandler;
import it.polimi.ingsw.ps19.server.ServerCommandHandler;
import it.polimi.ingsw.ps19.server.ServerInterface;
import it.polimi.ingsw.ps19.server.observers.MatchObserver;
import it.polimi.ingsw.ps19.utils.User;
import it.polimi.ingsw.ps19.utils.UsersCreator;

/**
 * The is The Controller of the entire Gameplay, the server is the only one that
 * makes decision about the match, this class has the references to every model
 * object and uses the methods of it, it also decides the commands to send to
 * the clients and handles the commands arrived by every client, an instance of
 * MatchHandler exist for every Match created by the server
 *
 * @author Mirko
 */
public class MatchHandler implements Runnable, MatchHandlerObserver, MatchObserver {

	/** The clients. */
	private List<ClientHandler> clients;

	/** The closed clients. */
	private List<ClientHandler> closedClients;

	/** The command handler. */
	private transient ServerCommandHandler commandHandler;

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

	private boolean alreadyDoneAction = false;

	private ArrayList<User> users;

	private HashMap<String, User> userFromColor;

	private int authenticatedCorrectly;

	private long startTime;

	private ArrayList<User> disconnectedUsers;
	
	/**
	 * variable that states the presence of the fifth player
	 */
	private boolean satanIsPresent;
	
	private ClientHandler fifthPlayerClient;
	
	

	/**
	 * Instantiates a new match handler.
	 *
	 * @param clients
	 *            the clients
	 * @param ServerInterface
	 *            the server interface
	 */
	public MatchHandler(List<ClientHandler> clients, ServerInterface ServerInterface) {
		
		if(clients.size()==5){
			satanIsPresent=true;
			this.fifthPlayerClient=clients.remove(clients.size()-1);
		}
	
		this.clients = clients;
		this.ServerInterface = ServerInterface;
		closedClients = new ArrayList<ClientHandler>();
		userFromColor = new HashMap<>();

		disconnectedUsers = new ArrayList<User>();

		try {
			users = UsersCreator.getUsersFromFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		initMatch();
		startMatch();
	}

	/**
	 * Inits the match.
	 */
	private void initMatch() {

		match = new Match(clients.size(), this);
		
		if(satanIsPresent)
			match.createSatan();

		commandHandler = new ServerCommandHandler(this, match);

		setPlayers();

		communicateColors();

		match.setInitialPlayer();

	}

	private void initExistingMatch(int id) throws FileNotFoundException, ClassNotFoundException, IOException {

		match = MatchSaver.readMatch(id);

		commandHandler = new ServerCommandHandler(this, match);
	}

	/**
	 * Communicate colors.
	 */
	private void communicateColors() {
		
		if(satanIsPresent)
			try {
				
				fifthPlayerClient.sendCommand(new AssignColorCommand(match.getSatan().getColor()));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		
		
		for (ClientHandler c : clients) {
			String color = null;
			try {
				color = this.getRightPlayer(c).getColor();
				System.out.println("player color:"+color);
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

		for (int i = 0; i < clients.size(); i++) {
			sendToClientHandler(new ChooseLeaderCardCommand(leaderSets.get(i)), clients.get(i));
		}

	}

	/**
	 * Start match.
	 *
	 * @return the current id player
	 */

	private void startMatch() {
		sendToAllPlayers(new InitializeMatchCommand(match.getPlayers().length));
		sendToAllPlayers(new RefreshBoardCommand(match.getBoard()));

		sendToAllPlayers(new AskAuthenticationCommand());

		this.startTime = System.currentTimeMillis();

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
		
		if(satanIsPresent){
			fifthPlayerClient.addCommandObserver(commandHandler);
			System.out.println("ho eseguito la add observer");
			fifthPlayerClient.addObserver(this);
		}

		match.setPlayerOrder();
	}

	/**
	 * method invoked at the end of a turn that checks if the game is end or set
	 * the next player.
	 */
	public void setNext() {

		deactivateLeaderCards();

		try {
			match.setNextPlayer();
		} catch (EveryPlayerDisconnectedException e) {
			closeMatch();
			e.printStackTrace();
		}
	}

	/**
	 * Start turn.
	 */
	private void startTurn() {

		updateGamePlayTimeForEveryone();

		match.handlePeriodsAndTurns();
		if (match.getTurn() == 7) {
			handleEndGame();
		} else {

			initTurn();

			sendToAllPlayers(new InitializeTurnCommand(match.getPeriod(), match.getTurn()));

			sendToAllPlayers(new RefreshBoardCommand(match.getBoard()));

			match.distributeTurnResources(); // this needs to be here and not in
												// initTurn,
			// otherwise the GUI wouldn't have a playerResources Panel to add
			// resources into

			startRound();
		}

	}

	/**
	 * Inits the turn.
	 */
	private void initTurn() {
		refreshPlayerOrder();
		roundNumber = 0;

		match.clearBoard();

		match.getBoard().rollDices();
		match.refreshDicesValueForPlayers();
		match.addFamilyMembersToPlayers();

		this.match.getBoard().changeCardInTowers();

	}

	/**
	 * Start round.
	 */
	private void startRound() {
		alreadyDoneAction = false;
		stopTimerIfAlive();

		roundNumber++;
		sendToCurrentPlayer(new AskMoveCommand());
		startRoundTimer();
	}

	/**
	 * notifying command in broadcast.
	 *
	 * @param command
	 *            to be notified
	 */
	public void sendToAllPlayers(ServerToClientCommand command) {
		
		if(satanIsPresent)
			try {
				fifthPlayerClient.sendCommand(command);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		
		for (ClientHandler client : clients) {
			try {
				client.sendCommand(command);
			} catch (Exception e) {
				this.removeClient(client);
			}
		}
	}

	/**
	 * Send to all players except current.
	 *
	 * @param command
	 *            the command
	 */
	public void sendToAllPlayersExceptCurrent(ServerToClientCommand command) {
		
		if(satanIsPresent)
			try {
				fifthPlayerClient.sendCommand(command);
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		
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
					this.removeClient(client);
				}
			}
		}
	}

	/**
	 * Send to player.
	 *
	 * @param command
	 *            the command
	 * @param player
	 *            the player
	 */
	public void sendToPlayer(ServerToClientCommand command, Player player) {
		ClientHandler client;
		try {
			client = getRightClientHandler(player);
		} catch (WrongPlayerException e1) {
			// System.out.println(e1.getError());
			// e1.printStackTrace();
			return;
		}

		try {
			client.sendCommand(command);
			lastCommandSent = command;
		} catch (Exception e) {
			this.removeClient(client);
		}

	}

	/**
	 * Send to current player.
	 *
	 * @param command
	 *            the command
	 */
	public void sendToCurrentPlayer(ServerToClientCommand command) {
		sendToPlayer(command, match.getCurrentPlayer());
	}

	/**
	 * Starting the round timer thread
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

		roundTimerThread = new Thread(new RoundTimer(this, timeMillis));
		roundTimerThread.start();
	}

	/**
	 * close the match and all its connection with client.
	 */
	public synchronized void closeMatch() {
		if (!clients.isEmpty()) {
			for (ClientHandler clientHandler : clients) {
				try {
					clientHandler.closedByServer();
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		}
		ServerInterface.closeMatch(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.polimi.ingsw.ps19.server.controller.MatchHandlerObserver#isAllowed(it.
	 * polimi.ingsw.ps19.Player)
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.polimi.ingsw.ps19.server.controller.MatchHandlerObserver#removeClient(
	 * it.polimi.ingsw.ps19.server.ClientHandler)
	 */
	@Override
	public void removeClient(ClientHandler clientHandler) {

		if (!closedClients.contains(clientHandler)) {

			updateGamePlayTime(clientHandler.getPlayer().getColor());
			System.out.println("removing client and updating timeof " + clientHandler.getPlayer().getColor());

			closedClients.add(clientHandler);

			try {
				addDisconnectedUser(getRightPlayer(clientHandler).getName());
			} catch (WrongClientHandlerException e2) {
				e2.printStackTrace();
			}

			try {
				sendToAllPlayers(new PlayerDisconnectedCommand(getRightPlayer(clientHandler).getColor()));
			} catch (WrongClientHandlerException e1) {
				e1.printStackTrace();
			}

			try {
				this.match.addDisconnectedPlayer(getRightPlayer(clientHandler));
			} catch (MatchFullException e) {
				// System.out.println("Disconnected more players than the ones
				// in the game");
				// e.printStackTrace();
			} catch (WrongClientHandlerException e) {
				// e.printStackTrace();
			}

		}
	}

	private void addDisconnectedUser(String userName) {
		User u = getUserFromName(userName);
		if (u != null)
			disconnectedUsers.add(u);

	}

	private User getUserFromName(String name) {
		for (User u : users) {
			if (u.getUsername().equals(name))
				return u;
		}
		return null;

	}

	public void reconnectClient(ClientHandler clientHandler, User user) {
		 if(isUserInTheGameAndDisconnected(user))
		 {
			 Player p=match.getPlayerFromName(user.getUsername());
			 
			 if(p!=null){
			 disconnectedUsers.remove(user);
			 closedClients.remove(clientHandler);
			 clientHandler.addPlayer(p);
			 this.match.reconnectPlayer(p);
			 } else {
				 System.out.println("getplayerfromname returned NULL");
			 }
		 }

	}

	private boolean isUserInTheGameAndDisconnected(User user) {
		if (!users.contains(user))
			return false;
		if (!disconnectedUsers.contains(user))
			return false;

		return true;
	}

	private void updateGamePlayTimeForEveryone() {
		for (int i = 0; i < match.getPlayers().length; i++) {
			updateGamePlayTime(match.getPlayers()[i].getColor());
		}
	}

	/**
	 * Updates the gameplay time for the disconnected player
	 * 
	 * @param color
	 */
	private void updateGamePlayTime(String color) {

		long currentTime = System.currentTimeMillis();
		int elapsedTime = (int) ((currentTime - startTime) / 1000);

		userFromColor.get(color).incrementSecondsPlayed(elapsedTime);

		this.runUpdateFileThread();

	}

	private void runUpdateFileThread() {
		// new Thread( new Runnable() {
		// @Override
		// public void run() {
		// }
		// }).start();

		Executors.newSingleThreadExecutor().execute(new Runnable() {
			@Override
			public void run() {
				UsersCreator.updateFile(users);
			}
		});

	}

	/**
	 * Gets the right client handler.
	 *
	 * @param player
	 *            the player
	 * @return the right client handler
	 * @throws WrongPlayerException
	 *             the wrong player exception
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
	 * @param action
	 *            the action
	 * @throws NotApplicableException
	 *             the not applicable exception
	 */
	public void applyAction(Action action) throws NotApplicableException {
		action.apply();
		sendToAllPlayers(new RefreshBoardCommand(match.getBoard()));

		alreadyDoneAction = true;

		if (match.getCurrentPlayer().getCouncilPrivilege() != 0) {
			sendPrivilegeToCurrentPlayer(match.getCurrentPlayer().getCouncilPrivilege());

			match.getCurrentPlayer().resetPrivileges();

		} else {

			sendToCurrentPlayer(new AskFinishRoundOrDiscardCommand());
		}

	}

	/**
	 * Apply action.
	 *
	 * @param choices
	 *            the choices
	 * @param industrialAction
	 *            the industrial action
	 * @throws NotApplicableException
	 *             the not applicable exception
	 */
	private void applyAction(List<Integer> choices, IndustrialAction industrialAction) throws NotApplicableException {
		industrialAction.apply(choices);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.polimi.ingsw.ps19.server.observers.MatchObserver#
	 * notifyPlayerStatusChange(it.polimi.ingsw.ps19.Player)
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

	/**
	 * Round timer expired.
	 */
	public void roundTimerExpired() {
		sendToCurrentPlayer(new RoundTimerExpiredCommand());

		try {
			this.removeClient(getRightClientHandler(getCurrentPlayer()));
		} catch (WrongPlayerException e1) {
			e1.printStackTrace();
		}

		if (match.isAnyoneStillPlaying()) {
			System.out.println("qualcuno sta ancora giocando");
			setNext();
			nextStep();
		} else {
			this.match = null;
		}
	}

	/**
	 * Next step.
	 */
	private void nextStep() {
		
		if(satanIsPresent&&(roundNumber%4==0))
			sendToClientHandler(new AskSatanMoveCommand(), fifthPlayerClient);
		
		
		if ((roundNumber == match.getPlayers().length * 4) || currentPlayerWithoutFamilyMembers()) {
			if (match.getTurn() % 2 == 1) {
				startTurn();
			} else {
				startExcommunicationPhase();
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
		if (this.match.getCurrentPlayer().getFamilyMembers().isEmpty()) {
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
	 * @param username
	 *            the username
	 * @param password
	 *            the password
	 * @param clientHandler
	 *            the client handler
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
	 * @param command
	 *            the command
	 * @param clientHandler
	 *            the client handler
	 */
	private void sendToClientHandler(ServerToClientCommand command, ClientHandler clientHandler) {
		try {
			clientHandler.sendCommand(command);
		} catch (IOException e) {
			e.printStackTrace();
			this.removeClient(clientHandler);
		}
	}

	/**
	 * Gets the right player.
	 *
	 * @param clientHandler
	 *            the client handler
	 * @return the right player
	 * @throws WrongClientHandlerException
	 *             the wrong client handler exception
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
	 * @param playerColor
	 *            the player color
	 * @return the player from color
	 */
	private Player getPlayerFromColor(String playerColor) {
		Player[] players = this.match.getPlayers();
		for (int i = 0; i < players.length; i++) {
			if (players[i].getColor().equals(playerColor))
				return players[i];
		}
		return null;
	}

	/**
	 * Handle leader choice.
	 *
	 * @param name
	 *            the name
	 * @param playerColor
	 *            the player color
	 */
	public void handleLeaderChoice(String name, String playerColor) {
		leaderResponseCounter++;
		try {

			this.getPlayerFromColor(playerColor).addLeaderCards(match.getLeaderCards().getCard(name));

			removeLeaderFromSets(match.getLeaderCards().getCard(name));
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (leaderResponseCounter == match.getPlayers().length) {

			leaderResponseCounter = 0;
			for (int i = 0; i < clients.size(); i++) {
				if (cycle == 3) {
					try {
						this.getRightPlayer(clients.get((i + cycle) % (match.getPlayers().length)))
								.addLeaderCards(leaderSets.get(i).get(0));
					} catch (WrongClientHandlerException e) {
						e.printStackTrace();
					}
				} else {
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
				startTurn();
		}

	}

	/**
	 * Removes the leader from sets.
	 *
	 * @param leaderCard
	 *            the leader card
	 */
	private void removeLeaderFromSets(LeaderCard leaderCard) {
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
	public void handleEndGame() {
		Player[] rank = new Player[match.getPlayers().length];
		Player prevPlayer;
		for (int i = 0; i < match.getPlayers().length; i++) {
			int val = calculatePlayerPoints(match.getPlayers()[i]);
			rank[i] = match.getPlayers()[i];
			if (i > 0 && val > calculatePlayerPoints(match.getPlayers()[i - 1])) {
				prevPlayer = rank[i - 1];
				rank[i - 1] = match.getPlayers()[i];
				rank[i] = prevPlayer;
			}

		}
		if(this.satanIsPresent){
			int satanVictoryPoints = match.getSatan().getResourceChest().getResourceInChest(ResourceType.VICTORYPOINT).getAmount();
			if(calculatePlayerPoints(rank[0]) < satanVictoryPoints){
				sendToPlayer(new WinCommand(), match.getSatan());
				for (Player p : rank) {
						sendToPlayer(new LoseCommand(), p);
					
				}
			} else {
				sendToPlayer(new WinCommand(), rank[0]);
				sendToPlayer(new LoseCommand(),match.getSatan());
				for (Player p : rank) {
					if (p != rank[0]) {
						sendToPlayer(new LoseCommand(), p);
					}
				}
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
	 * @param p
	 *            the p
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

		}

		return points;
	}

	/**
	 * Calculate points from resources.
	 *
	 * @param p
	 *            the p
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
	 * @param p
	 *            the p
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
	 * @param p
	 *            the p
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
	 * @param p
	 *            the p
	 * @return the int
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private int calculatePointsForMilitaryPoints(Player p) throws IOException {
		Player[] rank = new Player[match.getPlayers().length];
		Player prevPlayer;
		int points = 0;
		for (int i = 0; i < match.getPlayers().length; i++) {
			int val = match.getPlayers()[i].getResourceChest().getResourceInChest(ResourceType.MILITARYPOINT)
					.getAmount();
			rank[i] = match.getPlayers()[i];
			if (i > 0 && val > match.getPlayers()[i - 1].getResourceChest()
					.getResourceInChest(ResourceType.MILITARYPOINT).getAmount()) {
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

		reader.close();
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
	 * @param numberOfPrivilege
	 *            the number of privilege
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
	 * @param leaderName
	 *            the leader name
	 */
	public void discardLeaderCard(String leaderName) {
		match.getCurrentPlayer().removeLeaderCard(leaderName);
		sendPrivilegeToCurrentPlayer(1);
	}

	/**
	 * Gets the array list privilege from array.
	 *
	 * @param rc
	 *            the rc
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
	 * @param choice
	 *            the choice
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

			if (alreadyDoneAction)
				sendToCurrentPlayer(new AskFinishRoundOrDiscardCommand());
			else
				sendToCurrentPlayer(new AskMoveCommand());

		} else {
			sendToCurrentPlayer(new InvalidActionCommand("You modified the code and "
					+ "tried to get resources from a council privilege which are not valid in the game"));
			sendToCurrentPlayer(new AskFinishRoundOrDiscardCommand());
		}

	}

	/**
	 * Checks if is privilege correct.
	 *
	 * @param choice
	 *            the choice
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
	 * @param choice
	 *            the choice
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

		ArrayList<Player> councilPlayers = new ArrayList<Player>();

		if (!councilMemberList.isEmpty()) {
			for (int i = 0; i < councilMemberList.size(); i++) {
				councilPlayers.add(councilMemberList.get(i).getPlayer());
			}
			for (int i = 0; i < councilPlayers.size(); i++) {
				for (int j = 0; j < councilPlayers.size(); j++) {
					if (i != j && councilPlayers.get(i) == councilPlayers.get(j)) {
						councilPlayers.remove(j);
					}
				}
			}
			for (int i = 0; i < oldList.length; i++) {
				if (!councilPlayers.contains(oldList[i])) {
					councilPlayers.add(oldList[i]);
				}
			}
			Player[] newList = new Player[oldList.length];
			for (int i = 0; i < councilPlayers.size(); i++) {
				newList[i] = councilPlayers.get(i);
			}

			match.setPlayers(newList);
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
	 * @param playerColor
	 *            the player color
	 * @param decision
	 *            the decision
	 */
	public void handleChurchSupportDecision(String playerColor, boolean decision) {
		numPlayersAnsweredExcomm++;
		if (decision) {

			if (match.getPeriod().ordinal() + 2 <= getPlayerFromColor(playerColor).getResourceChest()
					.getResourceInChest(ResourceType.FAITHPOINT).getAmount()) {

				this.getPlayerFromColor(playerColor).payFaithPoint();
				ResourceChest rc = new ResourceChest();
				rc.addResource(match.getChurchSupportPrizeInPeriod());
				this.getPlayerFromColor(playerColor).addResources(rc);
			} else {
				excommunicatePlayer(playerColor);
			}

		} else {
			excommunicatePlayer(playerColor);
		}

		if (numPlayersAnsweredExcomm == this.match.getPlayers().length) {
			numPlayersAnsweredExcomm = 0;
			startTurn();
		}

	}

	private void excommunicatePlayer(String playerColor) {
		ExcommunicationTile tile;
		tile = this.getCurrentExcommTile();
		tile.getEffect().applyEffect(getPlayerFromColor(playerColor));

		if (match.getPeriod() == Period.FIRST)
			this.getPlayerFromColor(playerColor).setExcommunicatedFirst(true);
		else if (match.getPeriod() == Period.SECOND) {
			this.getPlayerFromColor(playerColor).setExcommunicatedSecond(true);
		} else if (match.getPeriod() == Period.SECOND) {
			this.getPlayerFromColor(playerColor).setExcommunicatedThird(true);
		}

		this.sendToPlayer(new NotifyExcommunicationCommand(), this.getPlayerFromColor(playerColor));
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
	 * @param command
	 *            the command
	 */
	public void saveProductionParams(ProductionCommand command) {
		this.prodActionSpace = command.getActionSpace();
		this.prodPaidServant = command.getPaidServants();
		this.prodFamilyMember = command.getFamilyMember();

	}

	/**
	 * Handle production activation.
	 *
	 * @param productionActivationCommand
	 *            the production activation command
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
			alreadyDoneAction = true;
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
				if (l.isActivationState())
					l.getSpecialEffect().disapplyEffect(getCurrentPlayer());
				l.setActivationState(false);
			}
	}

	/**
	 * Client closed the game.
	 *
	 * @param playerColor
	 *            the player color
	 */
	public void clientClosedTheGame(String playerColor) {
		try {
			this.removeClient(getRightClientHandler(getPlayerFromColor(playerColor)));
		} catch (WrongPlayerException e) {
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
		if (!cloned.isGreaterEqualThan(new ResourceChest(0, 0, 0, 0, 0, 0, 0))
				|| match.getCurrentPlayer().getDeckOfType(CardType.BUILDING).size() < buildingCardRequired
				|| match.getCurrentPlayer().getDeckOfType(CardType.CHARACTER).size() < characterCardRequired
				|| match.getCurrentPlayer().getDeckOfType(CardType.VENTURE).size() < ventureCardRequired
				|| match.getCurrentPlayer().getDeckOfType(CardType.TERRITORY).size() < territoryCardRequired
				|| totalCards < anyCardRequired || leader.isActivationState())
			return false;
		else
			return true;

	}

	public Match getMatch() {
		return match;
	}

	public void handleAuthenticationRequest(String username, String password, String playerColor) {
		if (getUserOrCreateOne(username, password, playerColor)) {
			this.authenticatedCorrectly++;
			sendToPlayer(new AuthenticatedCorrectlyCommand(username), getPlayerFromColor(playerColor));
		} else
			sendToPlayer(new WrongPasswordCommand(username), getPlayerFromColor(playerColor));

		if (authenticatedCorrectly == this.match.getPlayers().length)
			startLeaderDiscardPhase();

	}

	/**
	 * 
	 * @param username
	 *            of the player that requested the authentication
	 * @param password
	 *            of the player that requested the authentication
	 * @return true if the password is correct or a new player has been created
	 *         successfully (and there was no other player with the same
	 *         username), false otherwise
	 */
	private boolean getUserOrCreateOne(String username, String password, String playerColor) {

		// using functional programming with streams of Users to get the user
		// from the username, if it exists
		final Optional<User> user = users.stream().filter(u -> u.getUsername().equals(username)).findFirst();

		if (user.isPresent()) {
			if (user.get().correctPassword(password)) {
				user.get().incrementMatches();

				this.runUpdateFileThread();

				userFromColor.put(playerColor, user.get());
				return true;
			} else
				return false;
		} else {
			User newUser = new User(username, password);
			this.users.add(newUser);
			userFromColor.put(playerColor, newUser);

			return true;
		}

	}

	public void handleSatanChoice(String color) {
		
		Random r=new Random();
		int amount=r.nextInt(3)+3;
		Effect instant=new InstantResourcesEffect(new ResourceChest(0,0,0,0,0,-amount,0));
		instant.applyEffect(getPlayerFromColor(color));
		
		System.out.println("\nhandling satan choice: chosen player:"+color);
		sendToAllPlayers(new NotifySatanActionCommand(color));
		
		notifyPlayerStatusChange(getPlayerFromColor(color));
		
	}

}
