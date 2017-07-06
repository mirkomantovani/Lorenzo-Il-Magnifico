package it.polimi.ingsw.ps19;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.ps19.model.card.CardType;
import it.polimi.ingsw.ps19.model.resource.ResourceType;

public class MatchTest {
	
	private Player player1;
	private Player player2;
	private Match match;
	
	@Before
	public void setUp(){
		player1 = new Player("matteo", "blue");
		player2 = new Player("enemy","red");
		match = new Match(2, null);
	}
	
	@Test
	public void MatchCreated(){
		assertNotNull(match);
	}
	
	@Test
	public void PlayersInMatch() throws MatchFullException{
		match.addPlayer(player1);
		match.addPlayer(player2);
		assertNotNull(match.getPlayers());
	}
	
	@Test
	public void BoardCreated(){
		assertNotNull(match.getBoard());
	}
	
	
	@Test 
	public void CreatedLeaders(){
		assertTrue(match.getLeaderCards().length() != 0);
	}
	
	public void distributedResources(){
		match.distributeTurnResources();
		assertTrue(match.getPlayers()[0].getResourceChest().getResourceInChest(ResourceType.SERVANT).getAmount() == 3);
	}
	
	public void turnChanges(){
		match.incrementTurn();
		assertTrue(match.getTurn() == 1);
	}
	
	


}
