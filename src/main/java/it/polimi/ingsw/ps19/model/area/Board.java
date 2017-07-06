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

/**
 * The Class Board.
 */
public class Board implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8471586996479354156L;

	/** The towers. */
	private Map<CardType,Tower> towers;
	
	/** The church. */
	private Church church;
	
	/** The council palace. */
	private CouncilPalace councilPalace;
	
	/** The market. */
	private Market market;
	
	/** The harvest area. */
	private HarvestArea harvestArea;
	
	/** The production area. */
	private ProductionArea productionArea;
	
	/** The military requirements for territories. */
	private static List militaryRequirementsForTerritories;
	
	/** The territory cards. */
	private Deck<? extends DevelopmentCard> territoryCards;
	
	/** The building cards. */
	private Deck<? extends DevelopmentCard> buildingCards;
	
	/** The character cards. */
	private Deck<? extends DevelopmentCard> characterCards;
	
	/** The venture cards. */
	private Deck<? extends DevelopmentCard> ventureCards;
	
	/** The number of players. */
	private int numberOfPlayers;
	
	
	/** The player order. */
	private List<String> playerOrder;
	

	/** The dices. */
	private Map<Dice, Integer> dices;

	

	/**
	 * Instantiates a new board.
	 *
	 * @param numberOfPlayers the number of players
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
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
	
	/**
	 * Gets the tower.
	 *
	 * @param cardType the card type
	 * @return the tower
	 */
	public Tower getTower(CardType cardType){
		return this.towers.get(cardType);
	}
	
	/**
	 * Change card in towers.
	 */
	public void changeCardInTowers(){
		System.out.println("board: change cards in towers");
		for(int i=0;i<CardType.values().length-1;i++){
			this.getTower(CardType.values()[i]).changeCards();
		}
			
	}



	/**
	 * Gets the church.
	 *
	 * @return the church
	 */
	public Church getChurch() {
		return church;
	}

	/**
	 * Sets the church.
	 *
	 * @param church the new church
	 */
	public void setChurch(Church church) {
		this.church = church;
	}

	/**
	 * Gets the council palace.
	 *
	 * @return the council palace
	 */
	public CouncilPalace getCouncilPalace() {
		return councilPalace;
	}

	/**
	 * Sets the council palace.
	 *
	 * @param councilPalace the new council palace
	 */
	public void setCouncilPalace(CouncilPalace councilPalace) {
		this.councilPalace = councilPalace;
	}

	/**
	 * Gets the market.
	 *
	 * @return the market
	 */
	public Market getMarket() {
		return market;
	}

	/**
	 * Sets the market.
	 *
	 * @param market the new market
	 */
	public void setMarket(Market market) {
		this.market = market;
	}

	/**
	 * Gets the harvest area.
	 *
	 * @return the harvest area
	 */
	public IndustrialArea getHarvestArea() {
		return harvestArea;
	}

	/**
	 * Sets the harvest area.
	 *
	 * @param harvestArea the new harvest area
	 */
	public void setHarvestArea(HarvestArea harvestArea) {
		this.harvestArea = harvestArea;
	}

	/**
	 * Gets the production area.
	 *
	 * @return the production area
	 */
	public ProductionArea getProductionArea() {
		return productionArea;
	}

	/**
	 * Sets the production area.
	 *
	 * @param productionArea the new production area
	 */
	public void setProductionArea(ProductionArea productionArea) {
		this.productionArea = productionArea;
	}
	
	/**
	 * Gets the military requirements for territories.
	 *
	 * @return the military requirements for territories
	 */
	public static List getMilitaryRequirementsForTerritories() {
		return militaryRequirementsForTerritories;
	}
	
	/**
	 * Method needed for the TakeCard Action.
	 *
	 * @author Mirko
	 * @param cardType the card type
	 * @param index the index
	 * @return the floor
	 */
	public Floor getFloor(CardType cardType, int index){
		return towers.get(cardType).getFloor(index);
		
	}
	
	/**
	 * Sets the player order.
	 *
	 * @param playerOrder the new player order
	 */
	public void setPlayerOrder(ArrayList<String> playerOrder){
		this.playerOrder=playerOrder;
		
	}
	
	/**
	 * Roll dices.
	 */
	public void rollDices() {
		for(int i=0;i<Dice.values().length;i++)
		Dice.values()[i].roll();
		
		dices.put(Dice.BLACK_DICE, Dice.BLACK_DICE.getUpperFaceValue());
		dices.put(Dice.ORANGE_DICE,Dice.ORANGE_DICE.getUpperFaceValue());
		dices.put(Dice.WHITE_DICE,Dice.WHITE_DICE.getUpperFaceValue());
		
	
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
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

	/**
	 * Gets the player order.
	 *
	 * @return the player order
	 */
	public ArrayList<String> getPlayerOrder() {
		return (ArrayList<String>) playerOrder;
	}

	/**
	 * Gets the dices.
	 *
	 * @return the dices
	 */
	public Map<Dice, Integer> getDices() {
		return dices;
	}
	
	
	
	
	
	
}
