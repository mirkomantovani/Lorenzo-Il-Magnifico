package it.polimi.ingsw.ps19;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.ps19.model.area.Board;
import it.polimi.ingsw.ps19.model.area.Floor;
import it.polimi.ingsw.ps19.model.card.CardType;

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

	public Match(int numPlayers) {
		try {
			board = new Board();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// players = new ArrayList<Player>();
		players = new Player[numPlayers];

	}

	public void addPlayer(Player p) throws MatchFullException {
		if (addedPlayers == players.length)
			throw new MatchFullException();
		else
			this.players[addedPlayers] = p;
		addedPlayers++;
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

	public Player setPlayer(int id) {
//		Player player = new Player();
//		players.add(player);
//		addPlayerToBoard(player);
//		return player;
		return null;
	}

	public void setInitialPlayer() {
//		currentPlayer = players.get(0);
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}
	
	public Floor getFloor(CardType cardType, int index){
		return this.board.getFloor(cardType, index);
	}

}
