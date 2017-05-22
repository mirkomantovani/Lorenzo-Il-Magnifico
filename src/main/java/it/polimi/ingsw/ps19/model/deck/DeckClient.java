package it.polimi.ingsw.ps19.model.deck;

import java.io.IOException;

import it.polimi.ingsw.ps19.model.card.BuildingCard;
import it.polimi.ingsw.ps19.model.card.CardConstants;
import it.polimi.ingsw.ps19.model.card.CharacterCard;
import it.polimi.ingsw.ps19.model.card.LeaderCard;
import it.polimi.ingsw.ps19.model.card.VentureCard;


//solo per prova, da cancellare
/**
 * @author Mirko
 *
 */
public class DeckClient {
	

	private static Deck<BuildingCard> buildingDeck;
	private static Deck<VentureCard> ventureDeck;
	private static Deck<CharacterCard> characterDeck;
	private static LeaderDeck leaderDeck;
	
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		long start = System.currentTimeMillis();

	
		
		//la linea 292 del file building è un 8 non 5 ma ho messo 5 perchè non possiamo trattare la privilege come risorsa ancora
		try {
			buildingDeck=new BuildingDeck("src/main/resources/files/filebuildingcards.txt", CardConstants.DECK_LENGTH);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("There was a fucking Error");
		}
		long end = System.currentTimeMillis();
		
		System.out.println("Time complexity: "+(end-start)+"ms");
		
		System.out.println("Building Deck built successfully");
		buildingDeck.printCardInfo(0);
		System.out.println(buildingDeck.getCard(7).toString());
//		buildingDeck.getCard(0).toString();
		
		start = System.currentTimeMillis();
		
		try{
			
			ventureDeck=new VentureDeck("src/main/resources/files/fileventurecards.txt", CardConstants.DECK_LENGTH);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("There was a fucking Error");
		}
		 end = System.currentTimeMillis();
		
		System.out.println("Time complexity: "+(end-start)+"ms");
		
		System.out.println("Venture Deck built successfully!");
		ventureDeck.printCardInfo(0);
		System.out.println(ventureDeck.getCard(2).toString());
		

		
		System.out.println("\n\nCharacters:\n");
		start = System.nanoTime();
		
		try{
			
			characterDeck=new CharacterDeck("src/main/resources/files/filecharactercards.txt", CardConstants.DECK_LENGTH);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("There was a fucking Error");
		}
		end = System.nanoTime();
			
			for(int i = 0; i< characterDeck.length(); i++ ){
				System.out.println(characterDeck.getCard(i).toString());
			}
		 
		System.out.println("Time complexity: "+(end-start)+"ns");
		
		System.out.println("Character Deck built successfully!");
		
		start = System.currentTimeMillis();

		try{
			
			leaderDeck=new LeaderDeck("src/main/resources/files/fileleadercards.txt", CardConstants.LEADER_DECK_LENGTH);
		}catch (IOException e) {
				e.printStackTrace();
				System.out.println("There was a fucking Error");
			}
		end = System.currentTimeMillis();
		
		System.out.println("Time complexity: "+(end-start)+"ms");
		
		System.out.println("Leader Deck built successfully!");
	}
	
	
	
	
}
