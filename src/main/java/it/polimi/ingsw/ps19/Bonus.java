package it.polimi.ingsw.ps19;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polimi.ingsw.ps19.model.card.CardType;
import it.polimi.ingsw.ps19.model.resource.Resource;

/**
 * @author Mirko
 *
 */
public class Bonus {
	 
	 private int harvestVariation; //it means the increase/decrease of the Harvest action value for the specific player
	 private int productionVariation; // "  "  "  "    //in production/harvest action
	 private int cardCostCoinDiscount = 0; //This is the amount of a discount if the card you would take costs coins
	 private int churchSupportBonus = -1;  //This is the amount of military Points you gain when you support the church (sisto IV effect)
	 
	 private boolean noFloorBonus;
	 private boolean characterCardsDiscount; //This boolean is true if "DAMA"'s effect is active.
	 private boolean buildingCardsDiscount; //This boolean is true if "COSTRUTTORE"'s effect is active.
	 private boolean discountOccupiedTower; // true if you haven't to pay the cost to place in an occupied tower
	 private boolean noMilitaryPointsRequiredForTerritories; // if true you don't have military points required to take a territoryCard
	 private boolean doubleResourcesFromCards; //True if you have to gain the resources taken from card twice
	
	 //Controlled in controller
		 private boolean skipRoundActive;
		 
		 //Controlled in MarketAction
		 private boolean noMarketActionActive;
		 
		 /**
		 * If you have a variation for a certain card type whenever you buy a card of that card type
		 * you instantly sum the cardActionValueVariation to your action value
		 */
		private Map<CardType,Integer> cardActionValueVariation; 
		 
		 /**
		 * First 5 excommunication tiles, whenever you get resources from an action space effect
		 * or a development card you have to subtract
		 * Controlled in InstantResourceEffect
		 */
		private List<Resource> resourceMalus;   //in instantresource
		
		/**
		 * Can only be for territory, venture or character, if you have this boolean set to true
		 * you don't get the final victory points for that specific card type
		 * Controlled in controller-Final Points Count?
		 * 
		 */
		private Map<CardType,Boolean> NoCardTypeFinalPoints;
		
		/**
		 * 13th excommunication tile, the effect sets the divider to be different than 1
		 * (the original excomm tile would set it to 2 but can be customize
		 * TODO problem: if servantsDivider is not equal to 1 the player should onyl be allowed to choose
		 * a multiple of servantsDivider (it wouldn't have sense to waste servants)
		 */
		private int servantsDivider;
		 
		 

		/**
		  * The constructor has to be called at the creation of the player.
		  * N.B. it also adds an entry for the ANY of CardType, can be useful 
		  * @author Mirko
		 * 
		 */
		public Bonus() {
			
			resourceMalus=new ArrayList<>();
			NoCardTypeFinalPoints=new HashMap<>();
			cardActionValueVariation=new HashMap<>();
			this.servantsDivider=1;
			
			int i=0;
			
			for(i=0;i<CardType.values().length;i++)
				cardActionValueVariation.put(CardType.values()[i], 0);
				NoCardTypeFinalPoints.put(CardType.values()[i], false);
			}
	 
	 
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

	
	public boolean isSkipRoundActive() {
		return skipRoundActive;
	}

	public void setSkipRoundActive(boolean skipRound) {
		this.skipRoundActive = skipRound;
	}
	
	public boolean isNoMarketActionActive() {
		return noMarketActionActive;
	}

	public void setNoMarketActionActive(boolean noMarketActionActive) {
		this.noMarketActionActive = noMarketActionActive;
	}
	
	 /**
	 * @param cardType
	 * @return the boolean variable of the specified card type
	 */
	public boolean getNoCardTypeFinalPoints(CardType cardType) {
		return NoCardTypeFinalPoints.get(cardType);
	}

	public void setNoCardTypeFinalPoints(CardType cardType) {
		this.NoCardTypeFinalPoints.put(cardType, true);
	}
	
	/**
	 * @return the action value variation bases on the amount of servants passed and the
	 * servantsDivider of the player
	 */
	public int getActionValueVariationBasedOnServants(int servants) {
		return (int)(servants/this.servantsDivider);
	}
	
	public int getServantsDivider(){
		return this.servantsDivider;
	}

	/**
	 * Should only be accessed by SetServantsDividerEffect
	 * @param servantsDivider
	 */
	public void setServantsDivider(int servantsDivider) {
		this.servantsDivider = servantsDivider;

	}
	 
	
	
	   
	 
}

