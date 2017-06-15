package it.polimi.ingsw.ps19;

import it.polimi.ingsw.ps19.constant.CardConstants;
import it.polimi.ingsw.ps19.exception.NotApplicableException;
import it.polimi.ingsw.ps19.model.action.IndustrialAction;
import it.polimi.ingsw.ps19.model.area.HarvestArea;
import it.polimi.ingsw.ps19.model.area.IndustrialArea;
import it.polimi.ingsw.ps19.model.area.ProductionArea;
import it.polimi.ingsw.ps19.model.card.BuildingCard;
import it.polimi.ingsw.ps19.model.card.TerritoryCard;
import it.polimi.ingsw.ps19.model.deck.DeckCreator;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;

public class IndustrialActionTest {

	
	public static void main(String[] args){
		Player player = new Player("Jimmy", "Blue");
		Player player2 = new Player("Teo", "Red");
		TerritoryCard[] territoryDeck = null;
		BuildingCard[] buildingDeck = null;
		IndustrialArea industrialArea;
		IndustrialArea secondIndustrialArea;	
		try {
			territoryDeck = DeckCreator.createTerritoryCardDeck("src/main/resources/files/fileterritorycards.txt", CardConstants.DECK_LENGTH);
			buildingDeck = DeckCreator.createBuildingCardDeck("src/main/resources/files/filebuildingcards.txt", CardConstants.DECK_LENGTH);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		player.addResources(new ResourceChest(0,0,0,0,0,0,0));
		player.getFamilyMember("black").setActionValueVariation(6);
		player.getFamilyMember("orange").setActionValueVariation(6);
		player.getFamilyMember("white").setActionValueVariation(6);
		player.getFamilyMember("neutral").setActionValueVariation(6);
		player.addCard(territoryDeck[0]);
		player.addCard(territoryDeck[1]);
		player.addCard(buildingDeck[0]);
		player.addCard(buildingDeck[1]);	
		player2.addResources(new ResourceChest(10,10,10,10,10,10,10));
		player2.getFamilyMember("black").setActionValueVariation(6);
		player2.getFamilyMember("orange").setActionValueVariation(6);
		player2.getFamilyMember("white").setActionValueVariation(6);
		player2.getFamilyMember("neutral").setActionValueVariation(6);
		player2.addCard(territoryDeck[0]);
		player2.addCard(territoryDeck[1]);
		player2.addCard(buildingDeck[0]);
		player2.addCard(buildingDeck[1]);
		
		
		industrialArea = new HarvestArea();
		secondIndustrialArea = new ProductionArea();
		industrialArea.getMultipleActionSpace().setFamilyMember(player.getFamilyMember(Color.BLACK));
		industrialArea.getMultipleActionSpace().setFamilyMember(player.getFamilyMember(Color.NEUTRAL));
		secondIndustrialArea.getSingleActionSpace().setFamilyMember(player.getFamilyMember(Color.WHITE));
		IndustrialAction action = new IndustrialAction(player.getFamilyMember(Color.BLACK), industrialArea, industrialArea.getMultipleActionSpace());
		IndustrialAction action2 = new IndustrialAction(player.getFamilyMember(Color.NEUTRAL), industrialArea, industrialArea.getMultipleActionSpace());
		
		//IndustrialAction action3 = new IndustrialAction(player.getFamilyMember(Color.ORANGE), secondIndustrialArea, secondIndustrialArea.getSingleActionSpace());

		try {
			action.apply();
			action2.apply();
		} catch (NotApplicableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(player.toString());
		
	}
	
}
