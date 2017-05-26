package it.polimi.ingsw.ps19;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polimi.ingsw.ps19.model.card.CardType;
import it.polimi.ingsw.ps19.model.resource.Resource;

/**
 * @author Jimmy
 *
 */
public class Bonus {
	 
	 private int harvestVariation; //it means the increase/decrease of the Harvest action value for the specific player
	 private int productionVariation; // "  "  "  "    //in production/harvest action
	 private int cardCostCoinDiscount = 0; //This is the amount of a discount if the card you would take costs coins
	 private int churchSupportBonus = -1;  //This is the amount of military Points you gain when you support the church (sisto IV effect)
	 
	 private Map<CardType,Integer> cardActionValueVariation; 
	 
	 private boolean noFloorBonus;
	 private boolean characterCardsDiscount; //This boolean is true if "DAMA"'s effect is active.
	 private boolean buildingCardsDiscount; //This boolean is true if "COSTRUTTORE"'s effect is active.
	 private boolean discountOccupiedTower; // true if you haven't to pay the cost to place in an occupied tower
	 
	 
	 
	 public int setChurchSupportBonus(int amount) {
		return churchSupportBonus = amount;
	}

	public int getCardCostCoinDiscount() {
		return cardCostCoinDiscount;
	}

	public void setCardCostCoinDiscount(int cardCostCoinDiscount) {
		this.cardCostCoinDiscount = cardCostCoinDiscount;
	}

	public Map<CardType, Integer> getCardActionValueVariation() {
		return cardActionValueVariation;
	}

	public void setCardActionValueVariation(Map<CardType, Integer> cardActionValueVariation) {
		this.cardActionValueVariation = cardActionValueVariation;
	}

	public boolean isDiscountOccupiedTower() {
		return discountOccupiedTower;
	}

	public void setDiscountOccupiedTower(boolean discountOccupiedTower) {
		this.discountOccupiedTower = discountOccupiedTower;
	}

	public boolean isNoMilitaryPointsRequiredForTerritories() {
		return noMilitaryPointsRequiredForTerritories;
	}

	public void setNoMilitaryPointsRequiredForTerritories(boolean noMilitaryPointsRequiredForTerritories) {
		this.noMilitaryPointsRequiredForTerritories = noMilitaryPointsRequiredForTerritories;
	}

	public boolean isDoubleResourcesFromCards() {
		return doubleResourcesFromCards;
	}

	public void setDoubleResourcesFromCards(boolean doubleResourcesFromCards) {
		this.doubleResourcesFromCards = doubleResourcesFromCards;
	}

	public List<Resource> getResourceMalus() {
		return resourceMalus;
	}

	public void setResourceMalus(List<Resource> resourceMalus) {
		this.resourceMalus = resourceMalus;
	}
	private boolean noMilitaryPointsRequiredForTerritories; // if true you don't have military points required to take a territoryCard
	 private boolean doubleResourcesFromCards; //True if you have to gain the resources taken from card twice
	 
	 //excommunication
	 private List<Resource> resourceMalus;   //in instantresource
	 
	 
	 /**
	  * The constructor has to be called at the creation of the player.
	  * N.B. it also adds an entry for the ANY of CardType, can be useful 
	  * @author Mirko
	 * 
	 */
	public Bonus() {
		cardActionValueVariation=new HashMap<>();
		for(int i=0;i<CardType.values().length;i++)
			cardActionValueVariation.put(CardType.values()[i], 0);
		}
	 
	 /**
	  *This method is the one used to access the HashMap containing the variations based 
	  *on the CardType of the card
	  *This is needed primarily if not exclusively for the TakeCardAction class
	  * @author Mirko
	 * @param cardType
	 * @return
	 */
	public int getCardTypeActionVariation(CardType cardType){  
		 return this.cardActionValueVariation.get(cardType);
	 }
	
	/**
	 * An effect that need to modify a variation on a CardType only needs to call this method
	 * passing the CardType and the !VARIATION!
	 * @author Mirko
	 * @param cardType
	 * @param variation
	 * @return
	 */
	public void addCardTypeActionVariation(CardType cardType,int variation){
		this.cardActionValueVariation.put(cardType,
				this.cardActionValueVariation.get(cardType)+variation);
	}


	public int getHarvestVariation() {
		return harvestVariation;
	}
	public void setHarvestVariation(int harvestVariation) {
		this.harvestVariation = harvestVariation;
	}
	public int getProductionVariation() {
		return productionVariation;
	}
	public void setProductionVariation(int productionVariation) {
		this.productionVariation = productionVariation;
	}
	
	public boolean isNoFloorBonus() {
		return noFloorBonus;
	}
	public void setNoFloorBonus(boolean noFloorBonus) {
		this.noFloorBonus = noFloorBonus;
	}
	public boolean isCharacterCardsDiscount() {
		return characterCardsDiscount;
	}
	public void setCharacterCardsDiscount(boolean characterCardsDiscount) {
		this.characterCardsDiscount = characterCardsDiscount;
	}
	public boolean isBuildingCardsDiscount() {
		return buildingCardsDiscount;
	}
	public void setBuildingCardsDiscount(boolean buildingCardsDiscount) {
		this.buildingCardsDiscount = buildingCardsDiscount;
	}
	 
	   
	 
}

