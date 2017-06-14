package it.polimi.ingsw.ps19.model.effect;

import it.polimi.ingsw.ps19.Color;
import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.constant.CardConstants;
import it.polimi.ingsw.ps19.model.action.Action;
import it.polimi.ingsw.ps19.model.action.IndustrialAction;
import it.polimi.ingsw.ps19.model.area.HarvestArea;
import it.polimi.ingsw.ps19.model.area.IndustrialArea;
import it.polimi.ingsw.ps19.model.card.TerritoryCard;
import it.polimi.ingsw.ps19.model.deck.DeckCreator;

public class ActionTest {

	
	public static void main(String[] args){
		Player player = new Player("Jimmy", "Blue");
		TerritoryCard[] territoryDeck = null;
		IndustrialArea industrialArea;
		try {
			territoryDeck = DeckCreator.createTerritoryCardDeck("src/main/resources/files/fileterritorycards.txt", CardConstants.DECK_LENGTH);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(territoryDeck[0].toString());
		System.out.println("\n\n\n\n\n\n\n");
		System.out.println("Player's info: " + player.toString());
		player.addCard(territoryDeck[0]);
		
		industrialArea = new HarvestArea();
		
		industrialArea.getSingleActionSpace().setFamilyMember(player.getFamilyMember(Color.BLACK));
		Action action = new IndustrialAction(player.getFamilyMember(Color.BLACK), industrialArea);
		
		
	}
	
}
