package it.polimi.ingsw.ps19;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.ps19.constant.BoardConstants;
import it.polimi.ingsw.ps19.model.area.Board;
import it.polimi.ingsw.ps19.model.area.Floor;
import it.polimi.ingsw.ps19.model.card.BuildingCard;
import it.polimi.ingsw.ps19.model.card.CardType;
import it.polimi.ingsw.ps19.model.card.DevelopmentCard;
import it.polimi.ingsw.ps19.model.deck.LeaderDeck;
import it.polimi.ingsw.ps19.model.resource.MilitaryPoint;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;
import it.polimi.ingsw.ps19.server.controller.MatchHandler;
import it.polimi.ingsw.ps19.server.observers.MatchObserver;

/**
 * @author Mirko
 *
 */
public class Match {

	

	private static final ResourceChest roundResourceSupply=new ResourceChest(0,2,2,3,0,0,0);
	private Board board;
	// private List<Player> players;
	private Player[] players;
	private int addedPlayers;
	private int currentPlayer=0;
	private MatchObserver observer;
	private String[] playercolors = new String[4];
	private int playerscreated;
	private LeaderDeck leaderCards;
	private Period period;
	private int turn=0;
	


	public Match(int numPlayers, MatchHandler matchObserver) {
		this.setMatchObserver(matchObserver);
		try {
			board = new Board(numPlayers);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// players = new ArrayList<Player>();
		players = new Player[numPlayers];
		System.out.println("Match: sono stato creato e ho" + numPlayers + " giocatori");
		playercolors[0] = "verde";
		playercolors[1] = "rosso";
		playercolors[2] = "blu";
		playercolors[3] = "giallo";
		try {
			leaderCards = new LeaderDeck();
			
			System.out.println("match: creato oggetto leaderdeck");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	public void addPlayer(Player p) throws MatchFullException {

		if (addedPlayers == players.length)
			throw new MatchFullException();
		else {
			this.players[addedPlayers] = p;
			p.addObserver(this.observer);
			addedPlayers++;
		}
	}

	public Board getBoard() {
		return board;
	}

	public synchronized Player[] getPlayers() {
		return players;
	}

	public int getAddedPlayers() {
		return addedPlayers;
	}

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

	public void setInitialPlayer() {
		currentPlayer = 0;
	}

	public Player getCurrentPlayer() {
		return this.players[this.currentPlayer];
	}

	public Floor getFloor(CardType cardType, int index) {
		return this.board.getFloor(cardType, index);
	}

	public void setMatchObserver(MatchObserver observer) {
		this.observer = observer;
	}

	public List<String[]> getCurrentPlayerProductionChoices() {
		List<String[]> choices = new ArrayList();
		List<DevelopmentCard> buildingCards = this.getCurrentPlayer().getDeckOfType(CardType.BUILDING);
		for (DevelopmentCard card : buildingCards) {

			BuildingCard buildingCard = (BuildingCard) card;

			if (buildingCard.hasProductionChoice()) {
				choices.add(buildingCard.getProductionChoice());
			}
		}
		return choices;
	}
	
	public int getChurchSupportCostInPeriod(){
		if(this.period == Period.FIRST){
			return BoardConstants.FIRSTPERIOD_CHURCHSUPPORTCOST;
		} else if(this.period == Period.SECOND){
			return BoardConstants.SECONDPERIOD_CHURCHSUPPORTCOST;
		} else {
			return BoardConstants.THIRDPERIOD_CHURCHSUPPORTCOST;
		}
	}
		
	public MilitaryPoint getChurchSupportPrizeInPeriod(){
		
		if(this.period == Period.FIRST){
			return this.board.getChurch().getMilitaryPoints()[BoardConstants.FIRSTPERIOD_CHURCHSUPPORTCOST];
		} else if(this.period == Period.SECOND){
			return this.board.getChurch().getMilitaryPoints()[BoardConstants.SECONDPERIOD_CHURCHSUPPORTCOST];
		} else {
			return this.board.getChurch().getMilitaryPoints()[BoardConstants.THIRDPERIOD_CHURCHSUPPORTCOST];
		}
	}
	
	public Period getPeriod(){
		return period;
	}
		
	public LeaderDeck getLeaderCards() {
		return leaderCards;
	}

	public void distributeTurnResources() {
		for(int i=0;i<players.length;i++){
			
//			ResourceChest rs=new ResourceChest(
//		    		BoardConstants.ROUND_COIN_FIRST_PLAYER+i,0,0,0,0,0,0);
//			rs.addChest(roundResourceSupply);
			
			//per prova aggiungiamone tante
			ResourceChest rs=new ResourceChest(100,100,100,100,100,100,100);
			
		    players[i].addResources(rs);
		    		
		}
		
	}



	public void incrementTurn() {
		this.turn++;
	}

	public void handlePeriodsAndTurns() {
		incrementTurn();
		if(this.turn==1)this.period=Period.FIRST;
		else if(this.turn==3)period=Period.SECOND;
		else if(this.turn==5)period=Period.THIRD;
		
	}

	public int getTurn() {
		return turn;
	}

	public void setNextPlayer() {
		if(this.currentPlayer==players.length-1)
			this.currentPlayer=0;
		else
		this.currentPlayer++;
	}

	public void setPlayerOrder() {
		ArrayList<String> colors=new ArrayList<String>();
		for(int i=0;i<players.length;i++)
		colors.add(playercolors[i]);
		this.board.setPlayerOrder(colors);
	}

	public void addFamilyMembersToPlayers() {
		for(int i=0;i<players.length;i++){
			players[i].addFamilyMembers();
		}
		
	}

}
