package it.polimi.ingsw.ps19;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.ps19.exception.NotApplicableException;
import it.polimi.ingsw.ps19.model.action.Action;
import it.polimi.ingsw.ps19.model.action.MarketAction;
import it.polimi.ingsw.ps19.model.action.TakeCardAction;
import it.polimi.ingsw.ps19.model.area.Market;
import it.polimi.ingsw.ps19.model.card.CardType;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;
import it.polimi.ingsw.ps19.model.resource.ResourceType;
import it.polimi.ingsw.ps19.model.resource.Servant;
import it.polimi.ingsw.ps19.server.controller.MatchHandler;

public class ActionTest {
	
	private Match match;
	private Player player;
	
	@Before
	public void setUp() throws MatchFullException{
		player = new Player("matteo","blue");
		match = new Match(2, null);
		match.addPlayer(player);
		player.addFamilyMembers();
		match.getBoard().changeCardInTowers();
		match.getBoard().setMarket(new Market(2));
	}
	
	@Test
	public void takeCardActionTest() throws NotApplicableException{
		player.addResources(new ResourceChest(10,10,10,10,10,10,10));
		Action action = new TakeCardAction(player.getFamilyMembers().get(Color.BLACK),match.getBoard().
				getTower(CardType.BUILDING).getFloor(0), new Servant(10));
		String cardName = match.getFloor(CardType.BUILDING, 0).getCard().getName();
		try{
		action.apply();
		}catch(NullPointerException e){
			
		}
		assertTrue(!player.getFamilyMembers().containsKey(Color.BLACK));
		assertTrue(match.getFloor(CardType.BUILDING, 0).getCard() == null);
		assertTrue(player.getDeckOfType(CardType.BUILDING).get(0).getName().equals(cardName));
		
	}
	
	@Test
	public void MarketActionTest() throws NotApplicableException{
		Action action = new MarketAction(player.getFamilyMember(Color.BLACK), match.getBoard()
				.getMarket().getMarktActionSpace("1"), 1);
		action.apply();
		assertTrue(!player.getFamilyMembers().containsKey(Color.BLACK));
		assertTrue(player.getResourceChest().getResourceInChest(ResourceType.COIN).getAmount() == 5);
		assertTrue(match.getBoard().getMarket().getMarktActionSpace("1").getFamilyMember().getColor() == Color.BLACK);
	}

}
