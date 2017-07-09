package it.polimi.ingsw.ps19.action;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.ps19.Color;
import it.polimi.ingsw.ps19.Match;
import it.polimi.ingsw.ps19.MatchFullException;
import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.constant.CardConstants;
import it.polimi.ingsw.ps19.exception.NotApplicableException;
import it.polimi.ingsw.ps19.model.action.Action;
import it.polimi.ingsw.ps19.model.action.CouncilPalaceAction;
import it.polimi.ingsw.ps19.model.action.IndustrialAction;
import it.polimi.ingsw.ps19.model.action.MarketAction;
import it.polimi.ingsw.ps19.model.action.TakeCardAction;
import it.polimi.ingsw.ps19.model.area.Market;
import it.polimi.ingsw.ps19.model.card.BuildingCard;
import it.polimi.ingsw.ps19.model.card.CardType;
import it.polimi.ingsw.ps19.model.card.DevelopmentCard;
import it.polimi.ingsw.ps19.model.deck.DeckCreator;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;
import it.polimi.ingsw.ps19.model.resource.ResourceType;
import it.polimi.ingsw.ps19.model.resource.Servant;

/**
 * The Class ActionTest.
 */
/**
 * @author matteo, Jimmy
 *
 */
public class ActionTest {
	
	/** The match. */
	private Match match;
	
	/** The player. */
	private Player player;
	
	/** Another player. */
	private Player coolPlayer;
	
	/** Yet another player. */
	private Player anotherPlayer;
	
	/** Hopefully the last player created for test. */
	private Player playerX;
	
	/**
	 * Sets the test up.
	 *
	 * @throws MatchFullException the match full exception
	 */
	@Before
	public void setUp() throws MatchFullException{
		
		DevelopmentCard[] territoryCards = null;
		DevelopmentCard[] buildingCards = null;
		DevelopmentCard[] characterCards = null;
		DevelopmentCard[] ventureCards = null;
		try {
			territoryCards = DeckCreator.createTerritoryCardDeck("src/main/resources/files/fileterritorycards.txt", CardConstants.DECK_LENGTH);
			buildingCards = DeckCreator.createBuildingCardDeck("src/main/resources/files/filebuildingcards.txt", CardConstants.DECK_LENGTH);
			characterCards = DeckCreator.createCharacterCardDeck("src/main/resources/files/filecharactercards.txt", CardConstants.DECK_LENGTH);
			ventureCards = DeckCreator.createVentureCardDeck("src/main/resources/files/fileventurecards.txt", CardConstants.DECK_LENGTH);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		player = new Player("matteo","blue");
		coolPlayer = new Player("Jimmy", "red");
		anotherPlayer = new Player("Mirko", "yellow");
		playerX = new Player("MisterX", "green");
		match = new Match(4, null);
		match.addPlayer(player);
		match.addPlayer(coolPlayer);
		match.addPlayer(anotherPlayer);
		match.addPlayer(playerX);
		player.addFamilyMembers();
		coolPlayer.addFamilyMembers();
		anotherPlayer.addFamilyMembers();
		playerX.addFamilyMembers();
		match.getBoard().changeCardInTowers();
		match.getBoard().setMarket(new Market(2));
		//the following cards are given in order to test the harvest and production action
		//Territory:
		//id 1: +1 coin, id 2: +1 wood, id 3: +1 coin +1 servant, id 4: +2 stones
		//Building
		//id 1: +1 coin foreach yellow card, id 2: +1 coin foreach green card, id 3: +1 vp foreach purple card, id 4: 1 vp foreach character 
		//id 24: +1 military +2 servants, id 23: +3 military +1 servant, id 22: +1 coin +1 faith point, id 21: +4 vp +1 stone
		for(int i = 0; i < 4; i++){
			playerX.addCard(territoryCards[CardConstants.DECK_LENGTH-1-i]);
			coolPlayer.addCard(territoryCards[i]);
			anotherPlayer.addCard(buildingCards[i]);
		}
		anotherPlayer.addCard(territoryCards[4]);
		anotherPlayer.addCard(characterCards[0]);
		anotherPlayer.addCard(ventureCards[0]);
	
	}
	
	/**
	 * Take card action test.
	 *
	 * @throws NotApplicableException the not applicable exception
	 */
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
		assertEquals(match.getFloor(CardType.BUILDING, 0).getCard(), null);
		assertEquals(player.getDeckOfType(CardType.BUILDING).get(0).getName(),cardName);
		
	}
	
	/**
	 * Market action test.
	 *
	 * @throws NotApplicableException the not applicable exception
	 */
	@Test
	public void MarketActionTest() throws NotApplicableException{
		Action action = new MarketAction(player.getFamilyMember(Color.BLACK), match.getBoard()
				.getMarket().getMarktActionSpace("1"), 1);
		try{
		action.apply();
		}catch(NullPointerException e){
			
		}
		assertTrue(!player.getFamilyMembers().containsKey(Color.BLACK));
		assertEquals(player.getResourceChest().getResourceInChest(ResourceType.COIN).getAmount(), 5);
		assertEquals(match.getBoard().getMarket().getMarktActionSpace("1").getFamilyMember().getColor(),Color.BLACK);
	}
	
	/**
	 * Council palace test.
	 *
	 * @throws NotApplicableException the not applicable exception
	 */
	@Test
	public void CouncilPalaceTest() throws NotApplicableException {
		Action action = new CouncilPalaceAction(player.getFamilyMember(Color.BLACK), match.getBoard().getCouncilPalace(), 10);
		try{
			action.apply();
		}catch(NullPointerException e){
			
		}
		assertTrue(!player.getFamilyMembers().containsKey(Color.BLACK));
		assertEquals(player.getResourceChest().getResourceInChest(ResourceType.COIN).getAmount(), 1);
		assertEquals(player.getCouncilPrivilege(), 1);
		assertEquals(match.getBoard().getCouncilPalace().getMembers().get(0).getColor(), Color.BLACK);
		
	}
	
	@Test
	public void SingleHarvestActionTest() throws NotApplicableException {
		Action harvestAction = new IndustrialAction(coolPlayer.getFamilyMember(Color.ORANGE), match.getBoard().getHarvestArea(), match.getBoard().getHarvestArea().getSingleActionSpace(), 10);
		coolPlayer.addResources(new ResourceChest(0,0,0,10,0,0,0));
		System.out.println("\n\n\n\n\nOrange family member : " + (coolPlayer.getFamilyMember(Color.ORANGE).getActionValue() + 10));
		System.out.println("Jimmy's resources: " + coolPlayer.getResourceChest().toString());

		try{
			harvestAction.apply();
		}catch(NullPointerException e){

		}
		
		System.out.println("Jimmy's resources: " + coolPlayer.getResourceChest().toString());
		assertTrue(!coolPlayer.getFamilyMembers().containsKey(Color.ORANGE));
		assertEquals(coolPlayer.getResourceChest().getResourceInChest(ResourceType.COIN).getAmount(), 2);
		assertEquals(coolPlayer.getResourceChest().getResourceInChest(ResourceType.WOOD).getAmount(), 1);
		assertEquals(coolPlayer.getResourceChest().getResourceInChest(ResourceType.SERVANT).getAmount(), 1);
		assertEquals(coolPlayer.getResourceChest().getResourceInChest(ResourceType.STONE).getAmount(), 2);
		//I don't find very useful to test if other resources are still at 0 because i'm testing the action and not the effects
	}
	
	@Test
	public void SingleProductionActionTest() throws NotApplicableException {
		Action productionAction = new IndustrialAction(anotherPlayer.getFamilyMember(Color.ORANGE), match.getBoard().getProductionArea(), match.getBoard().getHarvestArea().getSingleActionSpace(), 10);
		anotherPlayer.addResources(new ResourceChest(0,0,0,10,0,0,0));
		System.out.println("\n\n\n\n\nOrange family member : " + (anotherPlayer.getFamilyMember(Color.ORANGE).getActionValue() + 10));
		System.out.println("Mirko's resources: " + anotherPlayer.getResourceChest().toString());

		try{
			productionAction.apply();
		}catch(NullPointerException e){

		}
		
		System.out.println("Mirko's resources: " + anotherPlayer.getResourceChest().toString());
		assertTrue(!anotherPlayer.getFamilyMembers().containsKey(Color.ORANGE));
		assertEquals(anotherPlayer.getResourceChest().getResourceInChest(ResourceType.COIN).getAmount(), 5);
		assertEquals(anotherPlayer.getResourceChest().getResourceInChest(ResourceType.VICTORYPOINT).getAmount(), 2);	
	}

	@Test
	public void MultipleHarvestActionTest() throws NotApplicableException{
		//I need here to put some other familiar to check the control
		match.getBoard().getHarvestArea().getMultipleActionSpace().setFamilyMember(player.getFamilyMember(Color.BLACK));
		match.getBoard().getHarvestArea().getMultipleActionSpace().setFamilyMember(player.getFamilyMember(Color.NEUTRAL));
		match.getBoard().getHarvestArea().getMultipleActionSpace().setFamilyMember(coolPlayer.getFamilyMember(Color.BLACK));
		match.getBoard().getHarvestArea().getMultipleActionSpace().setFamilyMember(coolPlayer.getFamilyMember(Color.NEUTRAL));
		match.getBoard().getHarvestArea().getMultipleActionSpace().setFamilyMember(anotherPlayer.getFamilyMember(Color.BLACK));
		match.getBoard().getHarvestArea().getMultipleActionSpace().setFamilyMember(anotherPlayer.getFamilyMember(Color.NEUTRAL));
		match.getBoard().getHarvestArea().getMultipleActionSpace().setFamilyMember(playerX.getFamilyMember(Color.ORANGE));

		
		
		Action harvestAction = new IndustrialAction(playerX.getFamilyMember(Color.NEUTRAL), match.getBoard().getHarvestArea(), match.getBoard().getHarvestArea().getMultipleActionSpace(), 10);
		playerX.addResources(new ResourceChest(0,0,0,10,0,0,0));
		
		try{
			harvestAction.apply();
		}catch(NullPointerException e){
			
		}
		
		//the player should have: 4 military 3 servants 1 coin 1 faith point 4 vp 1 stone 
		System.out.println("MisterX's resources: " + playerX.getResourceChest().toString());
		System.out.println(match.getBoard().getHarvestArea().getMultipleActionSpace().toString());
		assertTrue(!playerX.getFamilyMembers().containsKey(Color.NEUTRAL));
		assertEquals(playerX.getResourceChest().getResourceInChest(ResourceType.MILITARYPOINT).getAmount(), 4);
		assertEquals(playerX.getResourceChest().getResourceInChest(ResourceType.SERVANT).getAmount(), 3);
		assertEquals(playerX.getResourceChest().getResourceInChest(ResourceType.COIN).getAmount(), 1);
		assertEquals(playerX.getResourceChest().getResourceInChest(ResourceType.FAITHPOINT).getAmount(), 1);
		assertEquals(playerX.getResourceChest().getResourceInChest(ResourceType.VICTORYPOINT).getAmount(), 4);
		assertEquals(playerX.getResourceChest().getResourceInChest(ResourceType.STONE).getAmount(), 1);


	}
}
