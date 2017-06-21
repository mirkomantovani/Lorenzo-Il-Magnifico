package it.polimi.ingsw.ps19;

import java.io.IOException;

import it.polimi.ingsw.ps19.constant.CardConstants;
import it.polimi.ingsw.ps19.exception.NotApplicableException;
import it.polimi.ingsw.ps19.model.action.Action;
import it.polimi.ingsw.ps19.model.action.CouncilPalaceAction;
import it.polimi.ingsw.ps19.model.action.IndustrialAction;
import it.polimi.ingsw.ps19.model.action.MarketAction;
import it.polimi.ingsw.ps19.model.action.TakeCardAction;
import it.polimi.ingsw.ps19.model.area.Board;
import it.polimi.ingsw.ps19.model.card.CardType;
import it.polimi.ingsw.ps19.model.card.DevelopmentCard;
import it.polimi.ingsw.ps19.model.deck.BuildingDeck;
import it.polimi.ingsw.ps19.model.deck.CharacterDeck;
import it.polimi.ingsw.ps19.model.deck.TerritoryDeck;
import it.polimi.ingsw.ps19.model.deck.VentureDeck;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;
import it.polimi.ingsw.ps19.model.resource.Servant;

public class ActionTest {
	
	public static void main(String[] args) throws IOException{
		
		Player player1 = new Player("Teo","Rosso");
		Player player2 = new Player("Mirko","Blu");
		Player player3 = new Player("Jimmo","Verde");
		Player player4 = new Player("Talete","Nero");
		distributeResources(player1, 10);
		distributeResources(player2, 10);

		Board board = new Board(4);
		
		//teo errore non metti nella tower le carte giuste
		System.out.println(board.getTower(CardType.TERRITORY).toString());
		System.out.println(board.getTower(CardType.BUILDING).toString());
		System.out.println(board.getTower(CardType.CHARACTER).toString());
		System.out.println(board.getTower(CardType.VENTURE).toString());
//		board.getTower(CardType.BUILDING).toString();
		
//		System.out.println("-press 1 to take a card \n-press 2 to do a market action");
//		
//		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
//		
//		if(Integer.parseInt(console.readLine()) == 1){
		
		Dice.ORANGE_DICE.getRandomFaceValue();
		Dice.WHITE_DICE.getRandomFaceValue();
		Dice.BLACK_DICE.getRandomFaceValue();
//		Dice.NEUTRAL_DICE.getRandomFaceValue();
		
			Action action=new TakeCardAction(player2.getFamilyMembers().get(Color.ORANGE),
					board.getTower(CardType.BUILDING).getFloors().get(3),new Servant(7));
			//carta residenza non funziona, non paga il costo di 2 stone
			
			try {
				action.apply();
			} catch (NotApplicableException e) {
				System.out.println("Not applicable");
				e.printStackTrace();
			}
			
			System.out.println(player2.getDeckOfType(CardType.BUILDING).get(0).toString());
			System.out.println(player2.getResourceChest().toString());
			
//		}
			Action action2=new TakeCardAction(player2.getFamilyMembers().get(Color.WHITE),
					board.getTower(CardType.TERRITORY).getFloors().get(1),new Servant(0));
			
			try {
				action2.apply();
			} catch (NotApplicableException e) {
				System.out.println("porco dio non applicable");
			}
		
			
//			System.out.println(player1.getDeckOfType(CardType.TERRITORY).get(1).toString());
//			System.out.println(player1.getResourceChest().toString());
			
			System.out.println("\n\n" + player1.getName() +  "'s cards:\n");
			
			player1.addCard(board.getTower(CardType.TERRITORY).getFloors().get(3).getCard());
			player1.addCard(board.getTower(CardType.TERRITORY).getFloors().get(2).getCard());
			player1.addCard(board.getTower(CardType.TERRITORY).getFloors().get(1).getCard());
			
			for(DevelopmentCard card : player1.getDeckOfType(CardType.TERRITORY))
				System.out.println(card);
			
//			Action action3 = new IndustrialAction(player1.getFamilyMembers().get(Color.BLACK), board.getHarvestArea(), board.getHarvestArea().getSingleActionSpace());
			
			System.out.println("\n\nActivating harvest effect:\n");
//			try {
//				action3.apply();
//			} catch (NotApplicableException e) {
//				e.printStackTrace();
//				
//			}
			

//			Action action6 = new MarketAction(player1.getFamilyMembers().get(Color.BLACK),board.getMarket().getMarktActionSpace("firstMarket"));
//			System.out.println("\n\nActivating market effect:\n");
//			try {
//				//action6.apply();
//			} catch (NotApplicableException e) {
//				e.printStackTrace();
//				
//			}

			
//			Action action5 = new CouncilPalaceAction(player1.getFamilyMembers().get(Color.ORANGE),board.getCouncilPalace());
//			System.out.println("\n\nActivating council palace effect:\n");
//			try {
//				action5.apply();
//			} catch (NotApplicableException e) {
//				e.printStackTrace();
//				
//			}
			
			
			System.out.println(player1.getResourceChest().toString());
			
			//Testing production effects
			player2.addCard(board.getTower(CardType.BUILDING).getFloors().get(0).getCard());
			player2.addCard(board.getTower(CardType.BUILDING).getFloors().get(1).getCard());
			player2.addCard(board.getTower(CardType.BUILDING).getFloors().get(2).getCard());
			
//			System.out.println(player2.getDeckOfType(CardType.BUILDING).get(0).toString());
//			System.out.println(player2.getDeckOfType(CardType.BUILDING).get(1).toString());
//			System.out.println(player2.getDeckOfType(CardType.BUILDING).get(2).toString());
			
//			player2.getDeckOfType(CardType.BUILDING).get(0).getPermanentEffect().applyEffect(player2);
			
//Action action4 = new IndustrialAction(player2.getFamilyMembers().get(Color.BLACK), board.getProductionArea(), board.getProductionArea().getSingleActionSpace());
			System.out.println("black dice value:"+player2.getFamilyMembers().get(Color.BLACK).getActionValue());
			System.out.println("\n\nActivating production effect:\n");
//			try {
//				action4.apply();
//			} catch (NotApplicableException e) {
//				System.out.println("Production action not applicable");
//				e.printStackTrace();
//				
//			}
			
			System.out.println(player2.getResourceChest().toString());
			

			
	}
	
	private static void distributeResources(Player player,int m) {
		ResourceChest resourceChest=new ResourceChest(10*m,10*m,10*m,10*m,10*m,10*m,10*m);
		player.getResourceChest().addChest(resourceChest);
		
	}
}
