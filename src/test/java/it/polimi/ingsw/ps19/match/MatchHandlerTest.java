package it.polimi.ingsw.ps19.match;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.ps19.constant.FileConstants;
import it.polimi.ingsw.ps19.model.Match;
import it.polimi.ingsw.ps19.model.MatchFullException;
import it.polimi.ingsw.ps19.model.Player;
import it.polimi.ingsw.ps19.model.area.BoardInitializer;
import it.polimi.ingsw.ps19.model.card.CardType;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;
import it.polimi.ingsw.ps19.model.resource.ResourceType;

public class MatchHandlerTest {
	
	private Match match;
	private Player player1,player2;
	
	@Before
	public void setUp() throws MatchFullException{
		
		player1 = new Player("matteo","blue");
		player2 = new Player("enemy","red");
		match = new Match(2,null);
		match.addPlayer(player1);
		match.addPlayer(player2);
		player2.setResources(new ResourceChest(100,100,100,100,100,100,100));
		player1.setResources(new ResourceChest(50,50,50,50,50,50,50));
	}
	
	@Test
	public void handleEndGameTest(){
		Player[] rank = new Player[match.getPlayers().length];
		Player prevPlayer;
		for (int i = 0; i < match.getPlayers().length; i++) {
			int val = calculatePlayerPoints(match.getPlayers()[i]);
			rank[i] = match.getPlayers()[i];
			if (i>0 && val > calculatePlayerPoints(rank[i - 1])) {
				prevPlayer = rank[i - 1];
				rank[i - 1] = match.getPlayers()[i];
				rank[i] = prevPlayer;
			}
			
		}
		System.out.println(calculatePlayerPoints(match.getPlayers()[0]));
		System.out.println(calculatePlayerPoints(match.getPlayers()[1]));
		assertEquals(rank[0], player2);
		assertEquals(rank[1], player1);
		assertEquals(calculatePlayerPoints(player1),92);
		assertEquals(calculatePlayerPoints(player2),185);
	
	}
	
	private int calculatePlayerPoints(Player p) {
		int points = 0;
		for (Player player : match.getPlayers()) {
			if (player == p) {
				points = points + player.getResourceChest().getResourceInChest(ResourceType.VICTORYPOINT).getAmount();
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
			if (i > 0 && val > match.getPlayers()[i - 1].getResourceChest().getResourceInChest(ResourceType.MILITARYPOINT)
					.getAmount()) {
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


}
