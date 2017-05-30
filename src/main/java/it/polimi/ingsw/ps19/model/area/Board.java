package it.polimi.ingsw.ps19.model.area;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polimi.ingsw.ps19.model.card.CardType;
import it.polimi.ingsw.ps19.model.card.DevelopmentCard;
import it.polimi.ingsw.ps19.model.deck.Deck;

public class Board {
	
	private Map<CardType,Tower> towers;
	
	private Church church;
	
	private CouncilPalace councilPalace;
	
	private Market market;
	
	private HarvestArea harvestArea;
	
	private ProductionArea productionArea;
	
	private static List militaryRequirementsForTerritories;
	
	

	public Board(Deck<? extends DevelopmentCard> territoryCards, Deck<? extends DevelopmentCard> buildingCards, Deck<? extends DevelopmentCard> characterCards, Deck<? extends DevelopmentCard> ventureCards) throws FileNotFoundException, IOException{
		
		towers = new HashMap<>();
		
		militaryRequirementsForTerritories=BoardInitializer.playerBoardRequirementsForTerritory();
				
		towers.put(CardType.TERRITORY,new Tower(CardType.TERRITORY, territoryCards,BoardInitializer.territoryBonuses()));
		towers.put(CardType.BUILDING,new Tower(CardType.BUILDING, buildingCards,BoardInitializer.buildingBonuses()));
		towers.put(CardType.CHARACTER,new Tower(CardType.CHARACTER, characterCards,BoardInitializer.characterBonuses()));
		towers.put(CardType.VENTURE,new Tower(CardType.VENTURE, ventureCards,BoardInitializer.ventureBonuses()));
		
		church = new Church();
		councilPalace = new CouncilPalace();
	//TODO	market = new Market(Match.getPlayers()); //this method should return the number of players in the match
		harvestArea = new HarvestArea();
		productionArea = new ProductionArea();
		
	}
	
	public Tower getTower(CardType cardType){
		return this.towers.get(cardType);
	}



	public Church getChurch() {
		return church;
	}

	public void setChurch(Church church) {
		this.church = church;
	}

	public CouncilPalace getCouncilPalace() {
		return councilPalace;
	}

	public void setCouncilPalace(CouncilPalace councilPalace) {
		this.councilPalace = councilPalace;
	}

	public Market getMarket() {
		return market;
	}

	public void setMarket(Market market) {
		this.market = market;
	}

	public IndustrialArea getHarvestArea() {
		return harvestArea;
	}

	public void setHarvestArea(HarvestArea harvestArea) {
		this.harvestArea = harvestArea;
	}

	public ProductionArea getProductionArea() {
		return productionArea;
	}

	public void setProductionArea(ProductionArea productionArea) {
		this.productionArea = productionArea;
	}
	
	public static List getMilitaryRequirementsForTerritories() {
		return militaryRequirementsForTerritories;
	}
	
}
