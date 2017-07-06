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
	public void setUp() throws MatchFullException{
		player1 = new Player("matteo", "blue");
		player2 = new Player("enemy","red");
		match = new Match(2, null);
		match.addPlayer(player1);
		match.addPlayer(player2);
		
	}
	
	@Test
	public void MatchCreated(){
		assertNotNull(match);
	}
	
	@Test
	public void PlayersInMatch() throws MatchFullException{
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
	
	@Test
	public void distributedResources(){
		match.distributeTurnResources();
		assertTrue(match.getPlayers()[0].getResourceChest().getResourceInChest(ResourceType.SERVANT).getAmount() == 100);
	}
	
	@Test
	public void turnChanges(){
		match.incrementTurn();
		assertTrue(match.getTurn() == 1);
	}
	
	@Test
	public void clearBoardTest(){
		match.getBoard().getCouncilPalace().setFamilyMember(player1.getFamilyMember(Color.BLACK));
		match.getBoard().getHarvestArea().getSingleActionSpace().setFamilyMember(player1.getFamilyMember(Color.ORANGE));
		match.getBoard().getMarket().getMarktActionSpace("1").setFamilyMember(player1.getFamilyMember(Color.WHITE));
		match.getBoard().getProductionArea().getMultipleActionSpace().setFamilyMember(player2.getFamilyMember(Color.BLACK));
		match.getBoard().getMarket().getMarktActionSpace("2").setFamilyMember(player2.getFamilyMember(Color.ORANGE));
		match.getBoard().getTower(CardType.BUILDING).getFloor(0).getActionSpace().setFamilyMember(player2.getFamilyMember(Color.WHITE));
		match.clearBoard();
		assertTrue(!match.getBoard().getCouncilPalace().isOccupied());
		assertTrue(!match.getBoard().getMarket().getMarktActionSpace("1").isOccupied());
		assertTrue(!match.getBoard().getMarket().getMarktActionSpace("2").isOccupied());
		assertTrue(!match.getBoard().getHarvestArea().getSingleActionSpace().isOccupied());
		assertTrue(!match.getBoard().getFloor(CardType.BUILDING, 0).getActionSpace().isOccupied());
		assertTrue(!match.getBoard().getProductionArea().getMultipleActionSpace().isOccupied());
	}
	
	


}
