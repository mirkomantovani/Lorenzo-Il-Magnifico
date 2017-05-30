package it.polimi.ingsw.ps19;

import java.io.IOException;

import it.polimi.ingsw.ps19.model.action.Action;
import it.polimi.ingsw.ps19.model.action.IndustrialAction;
import it.polimi.ingsw.ps19.model.action.NotApplicableException;
import it.polimi.ingsw.ps19.model.action.TakeCardAction;
import it.polimi.ingsw.ps19.model.area.Board;
import it.polimi.ingsw.ps19.model.card.CardConstants;
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

		Board board = new Board(new TerritoryDeck("src/main/resources/files/fileterritorycards.txt",CardConstants.DECK_LENGTH),
								new BuildingDeck("src/main/resources/files/filebuildingcards.txt",CardConstants.DECK_LENGTH),
								new CharacterDeck("src/main/resources/files/filecharactercards.txt",CardConstants.DECK_LENGTH),
								new VentureDeck("src/main/resources/files/fileventurecards.txt",CardConstants.DECK_LENGTH));
		
		
//		System.out.println("-press 1 to take a card \n-press 2 to do a market action");
//		
//		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
//		
//		if(Integer.parseInt(console.readLine()) == 1){
		
		Dice.ORANGE_DICE.getRandomFaceValue();
		Dice.WHITE_DICE.getRandomFaceValue();
		Dice.BLACK_DICE.getRandomFaceValue();
//		Dice.NEUTRAL_DICE.getRandomFaceValue();
		
			Action action=new TakeCardAction(player1.getFamilyMembers().get(Color.ORANGE),
					board.getTowers().get(0).getFloors().get(0),new Servant(0));
			
			try {
				action.apply();
			} catch (NotApplicableException e) {
				System.out.println("Not applicable");
				e.printStackTrace();
			}
			
			System.out.println(player1.getDeckOfType(CardType.TERRITORY).get(0).toString());
			System.out.println(player1.getResourceChest().toString());
			
//		}
			Action action2=new TakeCardAction(player1.getFamilyMembers().get(Color.WHITE),
					board.getTowers().get(0).getFloors().get(1),new Servant(0));
			
			try {
				action2.apply();
			} catch (NotApplicableException e) {
				System.out.println("porco dio non applicable");
			}
		
			
//			System.out.println(player1.getDeckOfType(CardType.TERRITORY).get(1).toString());
//			System.out.println(player1.getResourceChest().toString());
			
			System.out.println("\n\n" + player1.getName() +  "'s cards:\n");
			
			player1.addCard(board.getTowers().get(0).getFloors().get(3).getCard());
			player1.addCard(board.getTowers().get(0).getFloors().get(2).getCard());
			player1.addCard(board.getTowers().get(0).getFloors().get(1).getCard());
			
			for(DevelopmentCard card : player1.getDeckOfType(CardType.TERRITORY))
				System.out.println(card);
			
			Action action3 = new IndustrialAction(player1.getFamilyMembers().get(Color.BLACK), board.getHarvestArea());
			
			System.out.println("\n\nActivating harvest effect:\n");
			try {
				action3.apply();
			} catch (NotApplicableException e) {
				e.printStackTrace();
				
			}
			
			System.out.println(player1.getResourceChest().toString());

			
	}
	
	private static void distributeResources(Player player,int m) {
		ResourceChest resourceChest=new ResourceChest(10*m,10*m,10*m,10*m,10*m,10*m,10*m);
		player.getResourceChest().addChest(resourceChest);
		
	}
}
