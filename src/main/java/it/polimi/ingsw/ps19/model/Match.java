package it.polimi.ingsw.ps19.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.polimi.ingsw.ps19.constant.BoardConstants;
import it.polimi.ingsw.ps19.exception.EveryPlayerDisconnectedException;
import it.polimi.ingsw.ps19.model.area.Board;
import it.polimi.ingsw.ps19.model.area.Floor;
import it.polimi.ingsw.ps19.model.card.BuildingCard;
import it.polimi.ingsw.ps19.model.card.CardType;
import it.polimi.ingsw.ps19.model.card.DevelopmentCard;
import it.polimi.ingsw.ps19.model.deck.LeaderDeck;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;
import it.polimi.ingsw.ps19.model.resource.VictoryPoint;
import it.polimi.ingsw.ps19.server.controller.MatchHandler;
import it.polimi.ingsw.ps19.server.observers.MatchObserver;

/**
 * The Class Match.
 *
 * @author Mirko
 */
public class Match implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4611746373897593079L;

	/** The Constant roundResourceSupply. */
	private static final ResourceChest roundResourceSupply = new ResourceChest(0, 2, 2, 3, 0, 0, 0);
	
	/** The board. */
	private Board board;
	
	/** The players. */
	// private List<Player> players;
	private Player[] players;
	/** The added players. */
	private int addedPlayers;
	
	private Player satan;
	
	
	private ArrayList<Player> disconnectedPlayers;
	
	
	/** The current player. */
	private int currentPlayer = 0;
	
	/** The match finished. */
	private boolean matchFinished;
	
	/** The observer. */
	private transient MatchObserver observer;
	
	/** The playercolors. */
	private String[] playercolors;
	
	/** The playerscreated. */
	private int playerscreated;
	
	/** The leader cards. */
	private LeaderDeck leaderCards;
	
	/** The period. */
	private Period period;
	
	/** The turn. */
	private int turn = 0;

	/**
	 * Instantiates a new match.
	 *
	 * @param numPlayers the num players
	 * @param matchObserver the match observer
	 */
	public Match(int numPlayers, MatchHandler matchObserver) {
		this.setMatchObserver(matchObserver);
		try {
			board = new Board(numPlayers);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		players = new Player[numPlayers];
		disconnectedPlayers=new ArrayList<Player>();

		playercolors = new String[numPlayers];

		shuffleColors();

		try {
			leaderCards = new LeaderDeck();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Shuffle colors.
	 */
	private void shuffleColors() {
		ArrayList<String> colors = new ArrayList<>();
		colors.add("Red");
		colors.add("Green");
		colors.add("Blue");
		colors.add("Yellow");
		Collections.shuffle(colors);

		for (int i = 0; i < playercolors.length; i++) {
			playercolors[i] = colors.get(i);
		}

	}

	/**
	 * Adds the player.
	 *
	 * @param p the p
	 * @throws MatchFullException the match full exception
	 */
	public void addPlayer(Player p) throws MatchFullException {

		if (addedPlayers == players.length)
			throw new MatchFullException();
		else {
			this.players[addedPlayers] = p;
			p.addObserver(this.observer);
			addedPlayers++;
		}
	}
	
	/**
	 * Adds the disconnected player.
	 *
	 * @param p the p
	 * @throws MatchFullException the match full exception
	 */
	public void addDisconnectedPlayer(Player p) throws MatchFullException{
		if(!isDisconnected(p)){
		
			
		if (disconnectedPlayers.size() == players.length)
			throw new MatchFullException();
		else {
			this.disconnectedPlayers.add(p);
		}
		}
		
	}
	
	public void reconnectPlayer(Player p){
		disconnectedPlayers.remove(p);
	
	}

	/**
	 * Checks if is disconnected.
	 *
	 * @param p the p
	 * @return true, if is disconnected
	 */
	private boolean isDisconnected(Player p) {
		
		return this.disconnectedPlayers.contains(p);
//		for(int i=0;i<disconnectedPlayers.length;i++){
//			if(p==disconnectedPlayers[i])
//				return true;
//		}
//		return false;
	}

	/**
	 * Gets the board.
	 *
	 * @return the board
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * Gets the players.
	 *
	 * @return the players
	 */
	public synchronized Player[] getPlayers() {
		return players;
	}

	/**
	 * Gets the added players.
	 *
	 * @return the added players
	 */
	public int getAddedPlayers() {
		return addedPlayers;
	}

	/**
	 * Creates the and return player.
	 *
	 * @param id the id
	 * @return the player
	 */
	public Player createAndReturnPlayer(int id) {
		Player player = new Player("", playercolors[playerscreated]);
		playerscreated++;
		try {
			this.addPlayer(player);
		} catch (MatchFullException e) {
			e.printStackTrace();
		}
		// addPlayerToBoard(player);
		return player;
	}

	/**
	 * Sets the initial player.
	 */
	public void setInitialPlayer() {
		currentPlayer = 0;
	}

	/**
	 * Gets the current player.
	 *
	 * @return the current player
	 */
	public Player getCurrentPlayer() {
		return this.players[this.currentPlayer];
	}

	/**
	 * Gets the floor.
	 *
	 * @param cardType the card type
	 * @param index the index
	 * @return the floor
	 */
	public Floor getFloor(CardType cardType, int index) {
		return this.board.getFloor(cardType, index);
	}

	/**
	 * Sets the match observer.
	 *
	 * @param observer the new match observer
	 */
	public void setMatchObserver(MatchObserver observer) {
		this.observer = observer;
	}

	/**
	 * Gets the current player production choices.
	 *
	 * @param familyMember the family member
	 * @param actionSpace the action space
	 * @param paidServants the paid servants
	 * @return the current player production choices
	 */
	public List<String[]> getCurrentPlayerProductionChoices(String familyMember, int actionSpace, int paidServants) {

		List<String[]> choices = new ArrayList<>();
		List<DevelopmentCard> buildingCards = this.getCurrentPlayer().getDeckOfType(CardType.BUILDING);
		Player player = getCurrentPlayer();
		FamilyMember fm = player.getFamilyMember(familyMember);
		String[] cardFields;

		for (DevelopmentCard card : buildingCards) {
			if (isApplicable(card, fm, player, paidServants)) {
				if ((card instanceof BuildingCard) && ((BuildingCard) card).hasProductionChoice()) {
					cardFields = new String[4];
					cardFields[0] = "" + card.getId();
					cardFields[1] = card.getName();
					String[] tmp = ((BuildingCard) card).getProductionChoice();
					cardFields[2] = tmp[0];
					cardFields[3] = tmp[1];

					choices.add(cardFields);

				}
			}
		}

		return choices;
	}

	/**
	 * Checks if is applicable.
	 *
	 * @param card the card
	 * @param fm the fm
	 * @param player the player
	 * @param paidServants the paid servants
	 * @return true, if is applicable
	 */
	public boolean isApplicable(DevelopmentCard card, FamilyMember fm, Player player, int paidServants) {
		return (fm.getActionValue() + player.getBonuses().getActivationVariation(card.getCardType())
				+ paidServants >= card.getActivationCost());
	}

	/**
	 * Gets the church support cost in period.
	 *
	 * @return the church support cost in period
	 */
	public int getChurchSupportCostInPeriod() {
		if (this.period == Period.FIRST) {
			return BoardConstants.FIRSTPERIOD_CHURCHSUPPORTCOST;
		} else if (this.period == Period.SECOND) {
			return BoardConstants.SECONDPERIOD_CHURCHSUPPORTCOST;
		} else {
			return BoardConstants.THIRDPERIOD_CHURCHSUPPORTCOST;
		}
	}

	/**
	 * Gets the church support prize in period.
	 *
	 * @return the church support prize in period
	 */
	public VictoryPoint getChurchSupportPrizeInPeriod() {

		if (this.period == Period.FIRST) {
			return this.board.getChurch().getVictoryPoints()[BoardConstants.FIRSTPERIOD_CHURCHSUPPORTCOST];
		} else if (this.period == Period.SECOND) {
			return this.board.getChurch().getVictoryPoints()[BoardConstants.SECONDPERIOD_CHURCHSUPPORTCOST];
		} else {
			return this.board.getChurch().getVictoryPoints()[BoardConstants.THIRDPERIOD_CHURCHSUPPORTCOST];
		}
	}
	
	public int getDisconnectedPlayersNum(){
		return disconnectedPlayers.size();
	}

	/**
	 * Gets the period.
	 *
	 * @return the period
	 */
	public Period getPeriod() {
		return period;
	}

	/**
	 * Gets the leader cards.
	 *
	 * @return the leader cards
	 */
	public LeaderDeck getLeaderCards() {
		return leaderCards;
	}

	/**
	 * Distribute turn resources.
	 */
	public void distributeTurnResources() {
		for (int i = 0; i < players.length; i++) {

			 ResourceChest rs=new ResourceChest(
			 BoardConstants.ROUND_COIN_FIRST_PLAYER+i,0,0,0,0,0,0);
			 rs.addChest(roundResourceSupply);

			// per prova aggiungiamone tante
//			ResourceChest rs = new ResourceChest(100, 100, 100, 100, 100, 100, 100);

			players[i].addResources(rs);

		}

	}

	/**
	 * Sets the players.
	 *
	 * @param players the new players
	 */
	public void setPlayers(Player[] players) {
		this.players = players;
		this.refreshOrder();
	}
	
	

	/**
	 * Refresh order.
	 */
	private void refreshOrder() {
		ArrayList<String> playerOrder=new ArrayList<String>();
		for(int i=0;i<players.length;i++){
		playerOrder.add(players[i].getColor());
		}
		
		board.setPlayerOrder(playerOrder);
		
	}

	/**
	 * Increment turn.
	 */
	public void incrementTurn() {
		this.turn++;
	}

	/**
	 * Handle periods and turns.
	 */
	public void handlePeriodsAndTurns() {
		incrementTurn();
		if (this.turn == 1)
			this.period = Period.FIRST;
		else if (this.turn == 3)
			period = Period.SECOND;
		else if (this.turn == 5)
			period = Period.THIRD;

		
	}

	/**
	 * Gets the turn.
	 *
	 * @return the turn
	 */
	public int getTurn() {
		return turn;
	}

	/**
	 * Sets the next player.
	 *
	 * @throws EveryPlayerDisconnectedException the every player disconnected exception
	 */
	public void setNextPlayer() throws EveryPlayerDisconnectedException {
		
		if(players.length==disconnectedPlayers.size()){
			matchFinished=true;
			throw new EveryPlayerDisconnectedException();
		}
		
		if (this.currentPlayer == players.length - 1)
			this.currentPlayer = 0;
		else
			this.currentPlayer++;
		
		if(isDisconnected(currentPlayer)){
			this.setNextPlayer();
		
		}
	}

	/**
	 * Checks if is disconnected.
	 *
	 * @param currentPlayer the current player
	 * @return true, if is disconnected
	 */
	private boolean isDisconnected(int currentPlayer) {
		
		return this.disconnectedPlayers.contains(players[currentPlayer]);
	}
	
	public Player getSatan(){
		return this.satan;
	}

	/**
	 * Sets the player order.
	 */
	public void setPlayerOrder() {
		ArrayList<String> colors = new ArrayList<String>();
		for (int i = 0; i < players.length; i++)
			colors.add(playercolors[i]);
		this.board.setPlayerOrder(colors);
	}

	/**
	 * Adds the family members to players.
	 */
	public void addFamilyMembersToPlayers() {
		for (int i = 0; i < players.length; i++) {
			players[i].addFamilyMembers();
		}

	}

	/**
	 * Refresh dices value for players.
	 */
	public void refreshDicesValueForPlayers() {
		for (int i = 0; i < this.getPlayers().length; i++)
			this.getPlayers()[i].refreshFamilyMemberValues();

	}

	/**
	 * Clear board.
	 *
	 * @author matteo this method removes all the familyMember placed in the game Board
	 */
	public void clearBoard() {

		// clearing the council palace
		this.getBoard().getCouncilPalace().getMembers().clear();

		// clearing harvest area
		this.getBoard().getHarvestArea().getMultipleActionSpace().getMembers().clear();
		this.getBoard().getHarvestArea().getSingleActionSpace().setFamilyMember(null);

		// clearing production area
		this.getBoard().getProductionArea().getMultipleActionSpace().getMembers().clear();
		this.getBoard().getProductionArea().getSingleActionSpace().setFamilyMember(null);

		// clearing the market
		this.getBoard().getMarket().getMarktActionSpace("1").setFamilyMember(null);
		this.getBoard().getMarket().getMarktActionSpace("2").setFamilyMember(null);
		if (this.players.length == 4) {
			this.getBoard().getMarket().getMarktActionSpace("3").setFamilyMember(null);
			this.getBoard().getMarket().getMarktActionSpace("4").setFamilyMember(null);
		}

		// clearing the towers
		for (CardType c : CardType.values()) {
			if (c != CardType.ANY) {
				for (int i = 0; i < this.getBoard().getTower(c).getFloors().size(); i++) {
					this.getBoard().getTower(c).getFloor(i).getActionSpace().setFamilyMember(null);
				}
			}
		}
	}

	/**
	 * Checks if is anyone still playing.
	 *
	 * @return true, if is anyone still playing
	 */
	public boolean isAnyoneStillPlaying() {
		return !matchFinished;
	}

	public Player getPlayerFromName(String name) {
		for(int i=0;i<players.length;i++){
			if(players[i].getName().equals(name))
				return players[i];
		}
		return null;
	}

	public void createSatan() {
		this.satan=new Player("Satan","black");
		satan.getResourceChest().addResource(new VictoryPoint(99));
		
	}
	

}
