package it.polimi.ingsw.ps19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import it.polimi.ingsw.ps19.model.action.TakeCardAction;
import it.polimi.ingsw.ps19.model.area.Board;
import it.polimi.ingsw.ps19.model.card.CardConstants;
import it.polimi.ingsw.ps19.model.deck.BuildingDeck;
import it.polimi.ingsw.ps19.model.deck.CharacterDeck;
import it.polimi.ingsw.ps19.model.deck.TerritoryDeck;
import it.polimi.ingsw.ps19.model.deck.VentureDeck;

public class ActionTest {
	
	public static void main(String[] args) throws IOException{
		
		Player player1 = new Player("Rosso","Teo");
		Player player2 = new Player("Blu","Mirko");
		Player player3 = new Player("Verde","Jimmo");
		Player player4 = new Player("Nero","Talete");

		Board board = new Board(new TerritoryDeck("C:/Users/matteo/ProjectMJM/src/main/resources/files/fileterritorycards.txt",CardConstants.DECK_LENGTH),
								new BuildingDeck("C:/Users/matteo/ProjectMJM/src/main/resources/files/filebuildingcards.txt",CardConstants.DECK_LENGTH),
								new CharacterDeck("C:/Users/matteo/ProjectMJM/src/main/resources/files/filecharactercards.txt",CardConstants.DECK_LENGTH),
								new VentureDeck("C:/Users/matteo/ProjectMJM/src/main/resources/files/fileventurecards.txt",CardConstants.DECK_LENGTH));
		
		System.out.println("-press 1 to take a card \n-press 2 to do a market action");
		
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		
		if(Integer.parseInt(console.readLine()) == 1){
			new TakeCardAction(player1.getFamilyMembers().get(Color.ORANGE),board.getTowers().get(0).getFloors().get(0),null);
			
		}
		
		

		
		
	}
}
