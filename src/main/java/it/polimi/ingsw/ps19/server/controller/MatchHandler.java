package it.polimi.ingsw.ps19.server.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.polimi.ingsw.ps19.Match;
import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.command.toclient.AskFinishRoundOrDiscardCommand;
import it.polimi.ingsw.ps19.command.toclient.AskMoveCommand;
import it.polimi.ingsw.ps19.command.toclient.AskPrivilegeChoiceCommand;
import it.polimi.ingsw.ps19.command.toclient.AssignColorCommand;
import it.polimi.ingsw.ps19.command.toclient.ChooseLeaderCardCommand;
import it.polimi.ingsw.ps19.command.toclient.InitializeMatchCommand;
import it.polimi.ingsw.ps19.command.toclient.InitializeTurnCommand;
import it.polimi.ingsw.ps19.command.toclient.InvalidCommand;
import it.polimi.ingsw.ps19.command.toclient.LoseCommand;
import it.polimi.ingsw.ps19.command.toclient.OpponentStatusChangeCommand;
import it.polimi.ingsw.ps19.command.toclient.PlayerStatusChangeCommand;
import it.polimi.ingsw.ps19.command.toclient.RefreshBoardCommand;
import it.polimi.ingsw.ps19.command.toclient.RoundTimerExpiredCommand;
import it.polimi.ingsw.ps19.command.toclient.ServerToClientCommand;
import it.polimi.ingsw.ps19.command.toclient.WinCommand;
import it.polimi.ingsw.ps19.constant.CardConstants;
import it.polimi.ingsw.ps19.constant.FileConstants;
import it.polimi.ingsw.ps19.exception.NotApplicableException;
import it.polimi.ingsw.ps19.exception.WrongClientHandlerException;
import it.polimi.ingsw.ps19.exception.WrongPlayerException;
import it.polimi.ingsw.ps19.model.action.Action;
import it.polimi.ingsw.ps19.model.area.BoardInitializer;
import it.polimi.ingsw.ps19.model.card.CardType;
import it.polimi.ingsw.ps19.model.card.LeaderCard;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;
import it.polimi.ingsw.ps19.model.resource.ResourceType;
import it.polimi.ingsw.ps19.server.ClientHandler;
import it.polimi.ingsw.ps19.server.ServerCommandHandler;
import it.polimi.ingsw.ps19.server.ServerInterface;
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
	private Thread roundTimer;
	private int leaderResponseCounter = 0;
	private ArrayList<ArrayList<LeaderCard>> leaderSets;
	private int cycle = 1;
	private int roundNumber = 0;
	private ServerToClientCommand lastCommandSent;

	public MatchHandler(List<ClientHandler> clients, ServerInterface ServerInterface) {
		this.clients = clients;
		this.ServerInterface = ServerInterface;
		closedClients = new ArrayList<ClientHandler>();
//		System.out.println("match handler: sono stato creato");
		// leaderSets = match.getLeaderCards()
		// .getStartingLeaderSets(match.getPlayers().length);
	}

	@Override
	public void run() {
		initMatch();
	}


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
//		startLeaderDiscardPhase(); // dovrebbe esserci questo
		// provaPlayer();
		 startTurn();
		// startMatch(); non parte qui ma dopo aver scartato i familiari
		
//		provaLeaderPlayer();
	}

//	private void provaLeaderPlayer() {
//		match.getPlayers()[0].addLeaderCards(match.getLeaderCards().getCard(0));
//		match.getPlayers()[0].addLeaderCards(match.getLeaderCards().getCard(1));
//		try {
//			this.sendToClientHandler(new PlayerStatusChangeCommand(match.getPlayers()[0]),
//					this.getRightClientHandler(match.getPlayers()[0]));
//		} catch (WrongPlayerException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

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


	private void startLeaderDiscardPhase() {
		leaderSets = match.getLeaderCards().getStartingLeaderSets(match.getPlayers().length);

//		System.out.println("matchhandler: lunghezza leadersets" + leaderSets.size());

		for (int i = 0; i < clients.size(); i++) {
			sendToClientHandler(new ChooseLeaderCardCommand(leaderSets.get(i)), clients.get(i));
			// System.out.println("matchHH : creato comando da
			// inv"+leaderSets.get(i).get(0).toString());
		}

	}

	/**
	 * 
	 * @return the current id player
	 */
	// public int getCurrentIdPlayer() {
	// return match.getCurrentPlayer().getPlayerId();
	// }

	private void startMatch() {
		sendToAllPlayers(new InitializeMatchCommand());
		startTurn();
		// notifyCurrentPlayer(new CommandAskMove());
		// createTurnTimer();
	}

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

		match.setPlayerOrder();
	}

	/**
	 * method invoked at the end of a turn that checks if the game is end or set
	 * the next player
	 */
	public void setNext() {
		// Map<Player, Boolean> winners = match.checkWinners();
		// if (winners.isEmpty() && !clients.isEmpty()) {
		match.setNextPlayer();

		// } else if (!winners.isEmpty() && !clients.isEmpty())
		// notifyEndOfGame(winners);
	}

	private void startTurn() {
		// sendToCurrentPlayer(new StartTurnCommand());
		match.handlePeriodsAndTurns();
		if (match.getTurn() == 7) {
			handleEndGame();
		} else {

//			System.out.println("rollo i dadi");
			match.getBoard().rollDices();
			match.addFamilyMembersToPlayers();
			match.distributeTurnResources();
			

			sendToAllPlayers(new InitializeTurnCommand(match.getPeriod(), match.getTurn()));

			this.match.getBoard().changeCardInTowers();
			
			sendToAllPlayers(new RefreshBoardCommand(match.getBoard()));

			roundNumber = 0;
			startRound();
			// notifyCurrentPlayer(new CommandAskMove());
			// createTurnTimer();
		}

	}

	private void startRound() {
		roundNumber++;
		sendToCurrentPlayer(new AskMoveCommand());
		startRoundTimer();
	}

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
			lastCommandSent = command;
		} catch (Exception e) {
			closedClients.add(client);
		}

		// checkDisconnection();
	}

	public void sendToCurrentPlayer(ServerToClientCommand command) {
		sendToPlayer(command, match.getCurrentPlayer());
		// checkDisconnection();
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
		roundTimer = new Thread(new RoundTimer(this, timeMillis));
		roundTimer.start();
	}

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
	public boolean isAllowed(Player player) {
		if (getCurrentPlayer() != null) {
			if (getCurrentPlayer() == player)
				return true;
			else
				return false;
		}
		return true;
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
		System.out.println("\nmatchhandler: applyaction\n");
		System.out.println("\nMATCHHANDLER, SOLO PROVVISORIO, DO SUBITO UNA CARTA TERRITORIO AL PLAYER\n");
		getCurrentPlayer().addCard(match.getBoard().getTower(CardType.TERRITORY).getFloor(1).getCard());
		System.out.println("\nMATCHHANDLER, SOLO PROVVISORIO, HO DATO LA CARTA: "  + getCurrentPlayer().getDeckOfType(CardType.TERRITORY).get(0).toString() + "\n");
		action.apply();
		System.out.println("\nMATCHHANDLER: HO FINITO DI APPLICARE LA ACTION\n");
		sendToAllPlayers(new RefreshBoardCommand(match.getBoard()));
		
		if(match.getCurrentPlayer().getCouncilPrivilege()!=0)
			sendPrivilegeToCurrentPlayer(match.getCurrentPlayer().getCouncilPrivilege());
		
		match.getCurrentPlayer().resetPrivileges();
		
		sendToCurrentPlayer(new AskFinishRoundOrDiscardCommand());
		// TODO MANDARE comando per scegliere terminare turno o scartare
		// leadercards

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

	@Override
	public void notifyPlayerStatusChange(Player player) {
		try {
//			System.out.println("matchhandler: invio player:" + player.toString());
			this.sendToClientHandler(new PlayerStatusChangeCommand(player), this.getRightClientHandler(player));
		} catch (WrongPlayerException e) {
			e.printStackTrace();
		}
		this.sendToAllPlayers(new OpponentStatusChangeCommand(player.maskedClone()));
	}

	@Override
	public void notifyFamilyPlaced() {

	}

	public void roundTimerExpired() {
		sendToCurrentPlayer(new RoundTimerExpiredCommand());
		setNext();
		nextStep();
	}

	private void nextStep() {
		if (roundNumber == match.getPlayers().length * 4) {
			if (match.getTurn() % 2 == 1)
				startTurn();
			else
				startExcommunicationPhase();
		} else
			startRound();

	}

	private void startExcommunicationPhase() {

		startTurn();
	}

	public void handleCredentials(String username, String password, ClientHandler clientHandler) {
		// for now it's just setting the name of the user
		try {
			getRightPlayer(clientHandler).setName(username);
		} catch (WrongClientHandlerException e) {
			sendToClientHandler(new InvalidCommand(), clientHandler);

		}

	}

	private void sendToClientHandler(ServerToClientCommand command, ClientHandler clientHandler) {
		try {
			clientHandler.sendCommand(command);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Player getRightPlayer(ClientHandler clientHandler) throws WrongClientHandlerException {
		for (ClientHandler c : clients) {
			if (c == clientHandler)
				return c.getPlayer();
		}
		throw new WrongClientHandlerException();

	}

	private Player getPlayerFromColor(String playerColor) {
		Player[] players = this.match.getPlayers();
		for (int i = 0; i < players.length; i++) {
//			System.out.println("matchhandler: getplayerfromcolor: p" + "layer.getcolor:" + players[i].getColor()
//					+ "playerColor:" + playerColor);
			if (players[i].getColor().equals(playerColor))
				return players[i];
		}
//		System.out.println("matchhandler: getplayerfromcolor: sto ritornando null");
		return null;
	}

	public void handleLeaderChoice(String name, String playerColor) {
		 System.out.println("matchhandler: sono in handleleaderchoice");
		leaderResponseCounter++;
		try {
			// System.out.println("matchhandler: cerco di aggiungere la carta di
			// nome:");
//			System.out.println("matchhandler: handleleaderchoice: leadername:" + name + "playercolor: " + playerColor);

			
				this.getPlayerFromColor(playerColor).addLeaderCards(match.getLeaderCards().getCard(name));

			removeLeaderFromSets(match.getLeaderCards().getCard(name));
		} catch (Exception e) {
			e.printStackTrace();
		}
//		System.out.println("matchhandler: leaderresponsecounter=" + leaderResponseCounter);
//		System.out.println("matchhandler: giocatori:=" + match.getPlayers().length);
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
				startMatch();
		}

	}

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

	public void finishRound() {
		setNext();
		nextStep();
	}

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

	private int calculatePointsFromResources(Player p) {
		int ResourceSum = 0;
		for (ResourceType r : ResourceType.values()) {
			if (r != ResourceType.VICTORYPOINT && r != ResourceType.FAITHPOINT && r != ResourceType.MILITARYPOINT) {
				ResourceSum = ResourceSum + p.getResourceChest().getResourceInChest(r).getAmount();
			}
		}
		return ResourceSum / 5;
	}

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

	public void handleInvalidCommand() {
		sendToCurrentPlayer(lastCommandSent);

	}
	
	public void sendPrivilegeToCurrentPlayer(int numberOfPrivilege){
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

	public void discardLeaderCard(String leaderName) {
		match.getCurrentPlayer().removeLeaderCard(leaderName);
		sendPrivilegeToCurrentPlayer(1);
	}

	private ArrayList<ResourceChest> getArrayListPrivilegeFromArray(ResourceChest[] rc) {
		ArrayList<ResourceChest> arr = new ArrayList<ResourceChest>();
		for (int i = 0; i < rc.length; i++) {
			arr.add(rc[i]);
		}
		return arr;
	}

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

		}

	}

	private boolean isPrivilegeCorrect(ArrayList<Integer> choice) {
		if (privilegeChoiceHasDuplicates(choice))
			return false;
		else
			return true;
	}

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

}
