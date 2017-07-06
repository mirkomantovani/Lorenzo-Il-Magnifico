package it.polimi.ingsw.ps19.model.area;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polimi.ingsw.ps19.Color;
import it.polimi.ingsw.ps19.Dice;
import it.polimi.ingsw.ps19.constant.CardConstants;
import it.polimi.ingsw.ps19.model.card.CardType;
import it.polimi.ingsw.ps19.model.card.DevelopmentCard;
import it.polimi.ingsw.ps19.model.deck.BuildingDeck;
import it.polimi.ingsw.ps19.model.deck.CharacterDeck;
import it.polimi.ingsw.ps19.model.deck.Deck;
import it.polimi.ingsw.ps19.model.deck.TerritoryDeck;
import it.polimi.ingsw.ps19.model.deck.VentureDeck;

public class Board implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8471586996479354156L;

	private Map<CardType,Tower> towers;
	
	private Church church;
	
	private CouncilPalace councilPalace;
	
	private Market market;
	
	private HarvestArea harvestArea;
	
	private ProductionArea productionArea;
	
	private static List militaryRequirementsForTerritories;
	
	private Deck<? extends DevelopmentCard> territoryCards;
	private Deck<? extends DevelopmentCard> buildingCards;
	private Deck<? extends DevelopmentCard> characterCards;
	private Deck<? extends DevelopmentCard> ventureCards;
	
	private int numberOfPlayers;
	
	
	private List<String> playerOrder;
	

	private Map<Dice, Integer> dices;

	

	public Board(int numberOfPlayers) throws FileNotFoundException, IOException{
		
		dices = new EnumMap<Dice, Integer>(Dice.class);
		
		dices.put(Dice.BLACK_DICE, Dice.BLACK_DICE.getUpperFaceValue());
		dices.put(Dice.ORANGE_DICE,Dice.ORANGE_DICE.getUpperFaceValue());
		dices.put(Dice.WHITE_DICE,Dice.WHITE_DICE.getUpperFaceValue());
		
		towers = new HashMap<>();
		
		militaryRequirementsForTerritories=BoardInitializer.playerBoardRequirementsForTerritory();
		
		territoryCards=new TerritoryDeck("src/main/resources/files/fileterritorycards.txt",CardConstants.DECK_LENGTH);
		buildingCards=new BuildingDeck("src/main/resources/files/filebuildingcards.txt",CardConstants.DECK_LENGTH);
		characterCards=new CharacterDeck("src/main/resources/files/filecharactercards.txt",CardConstants.DECK_LENGTH);
		ventureCards=new VentureDeck("src/main/resources/files/fileventurecards.txt",CardConstants.DECK_LENGTH);
				
		territoryCards.shuffleDeck();
		buildingCards.shuffleDeck();
		characterCards.shuffleDeck();
		ventureCards.shuffleDeck();
		
		
		for(int i=0;i<CardConstants.DECK_LENGTH;i++)
		
		towers.put(CardType.TERRITORY,new Tower(CardType.TERRITORY, territoryCards,BoardInitializer.territoryBonuses()));
		towers.put(CardType.BUILDING,new Tower(CardType.BUILDING, buildingCards,BoardInitializer.buildingBonuses()));
		towers.put(CardType.CHARACTER,new Tower(CardType.CHARACTER, characterCards,BoardInitializer.characterBonuses()));
		towers.put(CardType.VENTURE,new Tower(CardType.VENTURE, ventureCards,BoardInitializer.ventureBonuses()));
		
		church = new Church();
		councilPalace = new CouncilPalace();
		market = new Market(numberOfPlayers); //this method should return the number of players in the match
		//TODO the market should take the number of players in the match, not the magic number 4
		harvestArea = new HarvestArea();
		productionArea = new ProductionArea();
		
		
		this.numberOfPlayers = numberOfPlayers;
		
		this.playerOrder = new ArrayList<String>();
		

		
	}
	
	public Tower getTower(CardType cardType){
		return this.towers.get(cardType);
	}
	
	public void changeCardInTowers(){
		System.out.println("board: change cards in towers");
		for(int i=0;i<CardType.values().length-1;i++){
			this.getTower(CardType.values()[i]).changeCards();
		}
			
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
	
	/**
	 * Method needed for the TakeCard Action
	 * @author Mirko
	 * @param cardType
	 * @param index
	 * @return
	 */
	public Floor getFloor(CardType cardType, int index){
		return towers.get(cardType).getFloor(index);
		
	}
	
	public void setPlayerOrder(ArrayList<String> playerOrder){
		this.playerOrder=playerOrder;
		
	}
	
	public void rollDices() {
		for(int i=0;i<Dice.values().length;i++)
		Dice.values()[i].roll();
		
		dices.put(Dice.BLACK_DICE, Dice.BLACK_DICE.getUpperFaceValue());
		dices.put(Dice.ORANGE_DICE,Dice.ORANGE_DICE.getUpperFaceValue());
		dices.put(Dice.WHITE_DICE,Dice.WHITE_DICE.getUpperFaceValue());
		
	
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(">>>>>>>> The Board <<<<<<<< \n This is your world, let's try to impose your family "
				+ "as the most powerful! \n There are:\n ");
		
		for(Tower t : towers.values()){
		builder.append(t.toString());
		builder.append("\n");
		}
		builder.append(church.toString());
		builder.append("\n");
		builder.append(councilPalace.toString());
		builder.append("\n");
		builder.append(market.toString());
		builder.append("\n");
		builder.append(harvestArea.toString());
		builder.append("\n");
		builder.append(productionArea.toString());
		builder.append("\n The player order is : ");
		for(String p : playerOrder){
			builder.append(playerOrder.indexOf(p) + 1);
			builder.append(" - ");
			builder.append(p);
			builder.append("\t");
		}
		builder.append("\n The dices are: \n");

		
		builder.append(Dice.BLACK_DICE.getColor().toString() + " dice, with an action value of " + dices.get(Dice.BLACK_DICE) + "\n");
		builder.append(Dice.ORANGE_DICE.getColor().toString() + " dice, with an action value of " + dices.get(Dice.ORANGE_DICE) + "\n");
		builder.append(Dice.WHITE_DICE.getColor().toString() + " dice, with an action value of " + dices.get(Dice.WHITE_DICE) + "\n");
		
		return builder.toString();
	}

	public ArrayList<String> getPlayerOrder() {
		return (ArrayList<String>) playerOrder;
	}

	public Map<Dice, Integer> getDices() {
		return dices;
	}
	
	
	
	
	
	
}
