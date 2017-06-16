package it.polimi.ingsw.ps19;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.ps19.constant.BoardConstants;
import it.polimi.ingsw.ps19.constant.CardConstants;
import it.polimi.ingsw.ps19.constant.FileConstants;
import it.polimi.ingsw.ps19.model.area.Board;
import it.polimi.ingsw.ps19.model.area.Floor;
import it.polimi.ingsw.ps19.model.card.BuildingCard;
import it.polimi.ingsw.ps19.model.card.CardType;
import it.polimi.ingsw.ps19.model.card.DevelopmentCard;
import it.polimi.ingsw.ps19.model.deck.DeckCreator;
import it.polimi.ingsw.ps19.model.deck.LeaderDeck;
import it.polimi.ingsw.ps19.model.resource.MilitaryPoint;
import it.polimi.ingsw.ps19.server.controller.MatchHandler;
import it.polimi.ingsw.ps19.server.observers.MatchObserver;

/**
 * @author Mirko
 *
 */
public class Match {

	

	private Board board;
	// private List<Player> players;
	private Player[] players;
	private int addedPlayers;
	private Player currentPlayer;
	private MatchObserver observer;
	private String[] playercolors = new String[4];
	private int playerscreated;
	private LeaderDeck leaderCards;
	private Period period;
	


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

	public Player[] getPlayers() {
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
		currentPlayer = players[0];
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
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

}
