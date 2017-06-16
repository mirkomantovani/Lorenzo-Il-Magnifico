package it.polimi.ingsw.ps19;

import java.io.FileNotFoundException;
import java.io.IOException;

import it.polimi.ingsw.ps19.constant.CardConstants;
import it.polimi.ingsw.ps19.constant.FileConstants;
import it.polimi.ingsw.ps19.model.area.Board;
import it.polimi.ingsw.ps19.model.area.Floor;
import it.polimi.ingsw.ps19.model.card.CardType;
import it.polimi.ingsw.ps19.model.deck.DeckCreator;
import it.polimi.ingsw.ps19.model.deck.LeaderDeck;
import it.polimi.ingsw.ps19.server.MatchHandler;
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
	private String[] playercolors= new String[4];
	private int playerscreated;
	private LeaderDeck leaderCards;
	

	public Match(int numPlayers, MatchHandler matchObserver) {
		this.setMatchObserver(matchObserver);
		try {
			board = new Board();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// players = new ArrayList<Player>();
		players = new Player[numPlayers];
		System.out.println("Match: sono stato creato e ho"+numPlayers+" giocatori");
		playercolors[0]="verde";
		playercolors[1]="rosso";
		playercolors[2]="blu";
		playercolors[3]="giallo";
		try {
			DeckCreator.createLeaderCardDeck(FileConstants.LEADERCARDS, CardConstants.LEADER_DECK_LENGTH);
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
//		addPlayerToBoard(player);
		return player;
	}

	public void setInitialPlayer() {
		currentPlayer = players[0];
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}
	
	public Floor getFloor(CardType cardType, int index){
		return this.board.getFloor(cardType, index);
	}
	
	public void setMatchObserver(MatchObserver observer){
		this.observer=observer;
	}

}
