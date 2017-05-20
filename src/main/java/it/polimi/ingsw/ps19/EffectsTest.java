package it.polimi.ingsw.ps19;

import java.io.IOException;

import it.polimi.ingsw.ps19.model.card.BuildingCard;
import it.polimi.ingsw.ps19.model.card.CardConstants;
import it.polimi.ingsw.ps19.model.card.DevelopmentCard;
import it.polimi.ingsw.ps19.model.deck.BuildingDeck;
import it.polimi.ingsw.ps19.model.deck.Deck;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;

public class EffectsTest {
	
	private static Deck<BuildingCard> buildingDeck;
	private static Player player1;
	private static DevelopmentCard card;

	public static void main(String[] args) {
		
		player1=new Player("Mirko", Color.RED);

		try {
			buildingDeck=new BuildingDeck("src/main/resources/files/filebuildingcards.txt", CardConstants.DECK_LENGTH);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("There was a fucking Error");
		}
		
		distributeResources(player1);
		buyCardAction(player1,card);
	}
	
	private static void buyCardAction(Player player, DevelopmentCard card) {
		player.getResourceChest().subChest(card.getCost());
		card.getImmediateEffect().applyEffect();
		player.
		
	
	}

	private static void distributeResources(Player player) {
		ResourceChest resourceChest=new ResourceChest(10,10,10,10,10,10,10);
		player.getResourceChest().pourInto(resourceChest);
		
	}

}
