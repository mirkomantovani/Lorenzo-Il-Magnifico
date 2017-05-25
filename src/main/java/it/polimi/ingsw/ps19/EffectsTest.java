package it.polimi.ingsw.ps19;

import java.io.IOException;
import java.lang.invoke.ConstantCallSite;

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
		
		card=buildingDeck.getCard(2);
		
		System.out.println(player1.getResourceChest().toString());
		distributeResources(player1,10);
		System.out.println(player1.getResourceChest().toString());
		
		for(int i=0;i<CardConstants.DECK_LENGTH;i++){
		card=buildingDeck.getCard(i);
		buyCardAction(player1,card);
		System.out.println(player1.getResourceChest().toString());
		}
	}
	
	private static void buyCardAction(Player player, DevelopmentCard card) {
		//IMPORTANT the player.addCard(card); has to be the first thing to do, otherwise when the immediate
		//effect is applied the associated player of the card is null and a NullPointerException would be thrown
		player.addCard(card);
		System.out.println("You bought the card"+card.toString());
		player.getResourceChest().subChest(card.getCost());
		card.getImmediateEffect().applyEffect(player1);

	
	}

	private static void distributeResources(Player player,int m) {
		ResourceChest resourceChest=new ResourceChest(10*m,10*m,10*m,10*m,10*m,10*m,10*m);
		player.getResourceChest().addChest(resourceChest);
		
	}

}