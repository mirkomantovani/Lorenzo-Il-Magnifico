package it.polimi.ingsw.ps19;

import java.util.ArrayList;

import it.polimi.ingsw.ps19.model.card.BuildingCard;
import it.polimi.ingsw.ps19.model.card.CardType;
import it.polimi.ingsw.ps19.model.card.CharacterCard;
import it.polimi.ingsw.ps19.model.card.DevelopmentCard;
import it.polimi.ingsw.ps19.model.card.TerritoryCard;
import it.polimi.ingsw.ps19.model.card.VentureCard;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;

/**
 * @author matteo
 *
 */
public class Player {
	
	String name;
	
	Color color;
	
	FamilyMember orangeMember;
	FamilyMember blackMember;
	FamilyMember whiteMember;
	FamilyMember neutralMember;
	
	ResourceChest resources;
	
	ArrayList<TerritoryCard> territoryDeck;
	ArrayList<BuildingCard>  buildingDeck;
	ArrayList<CharacterCard> characterDeck;
	ArrayList<VentureCard> ventureDeck;
	
	private int harvestModification; //it means the increase/decrease of the Harvest action value for the specific player
	private int productionModification; 
	
	private boolean excommunicationStatusPeriod1; //true if the player is excommunicated for the first period
	private boolean excommunicationStatusPeriod2;
	private boolean excommunicationStatusPeriod3; 
	
	public Player(String name, Color color){
		
		resources = new ResourceChest();
		orangeMember = new FamilyMember(Dice.ORANGE_DICE);
		blackMember = new FamilyMember(Dice.BLACK_DICE);
		whiteMember = new FamilyMember(Dice.WHITE_DICE);
		neutralMember = new FamilyMember(Dice.NEUTRAL_DICE);
		territoryDeck = new ArrayList<TerritoryCard>();
		buildingDeck = new ArrayList<BuildingCard>();
		characterDeck = new ArrayList<CharacterCard>();
		ventureDeck = new ArrayList<VentureCard>();
		
		harvestModification = 0;
		productionModification = 0;
		
		excommunicationStatusPeriod1 = false;
		excommunicationStatusPeriod2 = false;
		excommunicationStatusPeriod3 = false;
			
		
	}
	
	/**
	 * This method adds a cart of a generic Type to the correct Deck of the player
	 * @author Mirko
	 * @param card
	 */
	public void addCard(DevelopmentCard card){
		card.setPlayer(this);
		switch(card.getCardType()){
		case BUILDING:
			buildingDeck.add((BuildingCard) card);	
			break;
		case TERRITORY:
			territoryDeck.add((TerritoryCard) card);
			break;
		case VENTURE:
			ventureDeck.add((VentureCard) card);
			break;
		case CHARACTER:
			characterDeck.add((CharacterCard) card);
			break;
		default:
			new Exception("Invalid card type");
		}
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public ResourceChest getResources() {
		return resources;
	}


	public ArrayList<TerritoryCard> getTerritoryDeck() {
		return territoryDeck;
	}


	public ArrayList<CharacterCard> getCharacterDeck() {
		return characterDeck;
	}


	public ArrayList<VentureCard> getVentureDeck() {
		return ventureDeck;
	}

	public ArrayList<BuildingCard> getBuildingDeck() {
		return buildingDeck;
	}
	

	public int getHarvestModification() {
		return harvestModification;
	}

	public void setHarvestModification(int harvestModification) {
		this.harvestModification = harvestModification;
	}

	public int getProductionModification() {
		return productionModification;
	}

	public void setProductionModification(int productionModification) {
		this.productionModification = productionModification;
	}

	public boolean isExcommunicationStatusPeriod1() {
		return excommunicationStatusPeriod1;
	}

	public void setExcommunicationStatusPeriod1(boolean excommunicationStatusPeriod1) {
		this.excommunicationStatusPeriod1 = excommunicationStatusPeriod1;
	}

	public boolean isExcommunicationStatusPeriod2() {
		return excommunicationStatusPeriod2;
	}

	public void setExcommunicationStatusPeriod2(boolean excommunicationStatusPeriod2) {
		this.excommunicationStatusPeriod2 = excommunicationStatusPeriod2;
	}

	public boolean isExcommunicationStatusPeriod3() {  //What does it mean Teo?
		return excommunicationStatusPeriod3;
	}

	public void setExcommunicationStatusPeriod3(boolean excommunicationStatusPeriod3) {
		this.excommunicationStatusPeriod3 = excommunicationStatusPeriod3;
	}

	/**
	 * 
	 * @return
	 */
	public ResourceChest getResourceChest() {
		
		return this.resources;
	}
	
	/*
	
	private void harvestAction(FamilyMember f){
		int actionValue = harvestModification + f.getDice().getUpperFaceValue();
		
		for(TerritoryCard c : territoryDeck){
			if(c.canActivateHarvestWith(actionValue)){
				//metodo azione raccolto
			}
		}
		//metodo che distribuisce le risorse bonus
	} */
	
	/**
	 * @author Jimmy
	 * This method returns the arrayList associated with the cardType
	 * 
	 * @param cardType
	 * @return
	 * 
	 * 
	 */
	public ArrayList<? extends DevelopmentCard> getRightArrayList(CardType cardType){
		switch(cardType){
		case TERRITORY:
			return this.getTerritoryDeck();
		case BUILDING:
			return this.getBuildingDeck();
		case CHARACTER: 
			return this.getCharacterDeck();
		case VENTURE:
			return this.getVentureDeck();
			default:
				return null;
		}
	}
	
	
	
	
}
