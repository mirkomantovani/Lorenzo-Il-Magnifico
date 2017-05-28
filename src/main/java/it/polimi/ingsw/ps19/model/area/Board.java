package it.polimi.ingsw.ps19.model.area;

import java.util.ArrayList;

import it.polimi.ingsw.ps19.model.card.CardType;
import it.polimi.ingsw.ps19.model.card.DevelopmentCard;
import it.polimi.ingsw.ps19.model.deck.Deck;

public class Board {
	
	private ArrayList<Tower> towers;
	
	private Church church;
	
	private CouncilPalace councilPalace;
	
	private Market market;
	
	private HarvestArea harvestArea;
	
	private ProductionArea productionArea;
	
	public Board(Deck<? extends DevelopmentCard> territoryCards, Deck<? extends DevelopmentCard> buildingCards, Deck<? extends DevelopmentCard> characterCards, Deck<? extends DevelopmentCard> ventureCards){
		
		towers = new ArrayList<Tower>();
		
		towers.add(new Tower(CardType.TERRITORY, territoryCards));
		towers.add(new Tower(CardType.CHARACTER, characterCards));
		towers.add(new Tower(CardType.BUILDING, buildingCards));
		towers.add(new Tower(CardType.VENTURE, ventureCards));
		
//		church = new Church(null,null,null);
		councilPalace = new CouncilPalace();
	//TODO	market = new Market(Match.getPlayers()); //this method should return the number of players in the match
		harvestArea = new HarvestArea();
		productionArea = new ProductionArea();
		
	}

	public ArrayList<Tower> getTowers() {
		return towers;
	}

	public void setTowers(ArrayList<Tower> towers) {
		this.towers = towers;
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
	
}
