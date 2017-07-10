package it.polimi.ingsw.ps19;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polimi.ingsw.ps19.exception.CardTypeException;
import it.polimi.ingsw.ps19.model.card.CardType;
import it.polimi.ingsw.ps19.model.resource.Resource;

/**
 * This class represents the various bonuses that a player has and accumulates over time
 * during the gameplay
 *
 * @author Mirko
 */
public class Bonus implements Serializable {
	 
	 /** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3821153835423383883L;
	
	/** The harvest variation. */
	private int harvestVariation; //it means the increase/decrease of the Harvest action value for the specific player
	 
 	/** The production variation. */
 	private int productionVariation; // "  "  "  "    //in production/harvest action
	 
 	/** The card cost coin discount. */
 	private int cardCostCoinDiscount = 0; //This is the amount of a discount if the card you would take costs coins
	 
 	/** The church support bonus. */
 	private int churchSupportBonus = -1;  //This is the amount of military Points you gain when you support the church (sisto IV effect)
	 
	 /** The no floor bonus. */
 	private boolean noFloorBonus;
	 
 	/** The character cards discount. */
 	private boolean characterCardsDiscount; //This boolean is true if "DAMA"'s effect is active.
	 
 	/** The building cards discount. */
 	private boolean buildingCardsDiscount; //This boolean is true if "COSTRUTTORE"'s effect is active.
	 
 	/** The discount occupied tower. */
 	private boolean discountOccupiedTower; // true if you haven't to pay the cost to place in an occupied tower
	 
 	/** The no military points required for territories. */
 	private boolean noMilitaryPointsRequiredForTerritories; // if true you don't have military points required to take a territoryCard
	 
 	/** The double resources from cards. */
 	private boolean doubleResourcesFromCards; //True if you have to gain the resources taken from card twice
	
	 /** The skip round active. */
 	//Controlled in controller
		 private boolean skipRoundActive;
		 
		 /** The no market action active. */
 		//Controlled in MarketAction
		 private boolean noMarketActionActive;
		 
		 /** If you have a variation for a certain card type whenever you buy a card of that card type you instantly sum the cardActionValueVariation to your action value. */
		private Map<CardType,Integer> cardActionValueVariation; 
		 
		 /** First 5 excommunication tiles, whenever you get resources from an action space effect or a development card you have to subtract Controlled in InstantResourceEffect. */
		private List<Resource> resourceMalus;   //in instantresource
		
		/** Can only be for territory, venture or character, if you have this boolean set to true you don't get the final victory points for that specific card type Controlled in controller-Final Points Count?. */
		private Map<CardType,Boolean> noCardTypeFinalPoints;
		
		/** 13th excommunication tile, the effect sets the divider to be different than 1 (the original excomm tile would set it to 2 but can be customize */
		private int servantsDivider;
		 
		 

		/**
		  * The constructor has to be called at the creation of the player.
		  * N.B. it also adds an entry for the ANY of CardType, can be useful 
		  * @author Mirko
		 * 
		 */
		public Bonus() {
			
			resourceMalus=new ArrayList<>();
			noCardTypeFinalPoints=new HashMap<>();
			cardActionValueVariation=new HashMap<>();
			this.servantsDivider=1;
			
			int i=0;
			
			for(i=0;i<CardType.values().length - 1;i++){
				cardActionValueVariation.put(CardType.values()[i], 0);
				noCardTypeFinalPoints.put(CardType.values()[i], false);
			}
			}
	 
	 
	 /**
 	 * Sets the church support bonus.
 	 *
 	 * @param amount the new church support bonus
 	 */
 	public void setChurchSupportBonus(int amount) {
		 churchSupportBonus = amount;
	}

	/**
	 * Gets the card cost coin discount.
	 *
	 * @return the card cost coin discount
	 */
	public int getCardCostCoinDiscount() {
		return cardCostCoinDiscount;
	}

	/**
	 * Sets the card cost coin discount.
	 *
	 * @param cardCostCoinDiscount the new card cost coin discount
	 */
	public void setCardCostCoinDiscount(int cardCostCoinDiscount) {
		this.cardCostCoinDiscount = cardCostCoinDiscount;
	}

	/**
	 * Gets the card action value variation.
	 *
	 * @return the card action value variation
	 */
	public Map<CardType, Integer> getCardActionValueVariation() {
		return cardActionValueVariation;
	}

	/**
	 * Sets the card action value variation.
	 *
	 * @param cardActionValueVariation the card action value variation
	 */
	public void setCardActionValueVariation(Map<CardType, Integer> cardActionValueVariation) {
		this.cardActionValueVariation = cardActionValueVariation;
	}

	/**
	 * Checks if is discount occupied tower.
	 *
	 * @return true, if is discount occupied tower
	 */
	public boolean isDiscountOccupiedTower() {
		return discountOccupiedTower;
	}

	/**
	 * Sets the discount occupied tower.
	 *
	 * @param discountOccupiedTower the new discount occupied tower
	 */
	public void setDiscountOccupiedTower(boolean discountOccupiedTower) {
		this.discountOccupiedTower = discountOccupiedTower;
	}

	/**
	 * Checks if is no military points required for territories.
	 *
	 * @return true, if is no military points required for territories
	 */
	public boolean isNoMilitaryPointsRequiredForTerritories() {
		return noMilitaryPointsRequiredForTerritories;
	}

	/**
	 * Sets the no military points required for territories.
	 *
	 * @param noMilitaryPointsRequiredForTerritories the new no military points required for territories
	 */
	public void setNoMilitaryPointsRequiredForTerritories(boolean noMilitaryPointsRequiredForTerritories) {
		this.noMilitaryPointsRequiredForTerritories = noMilitaryPointsRequiredForTerritories;
	}

	/**
	 * Checks if is double resources from cards.
	 *
	 * @return true, if is double resources from cards
	 */
	public boolean isDoubleResourcesFromCards() {
		return doubleResourcesFromCards;
	}

	/**
	 * Sets the double resources from cards.
	 *
	 * @param doubleResourcesFromCards the new double resources from cards
	 */
	public void setDoubleResourcesFromCards(boolean doubleResourcesFromCards) {
		this.doubleResourcesFromCards = doubleResourcesFromCards;
	}

	/**
	 * Gets the resource malus.
	 *
	 * @return the resource malus
	 */
	public List<Resource> getResourceMalus() {
		return resourceMalus;
	}

	/**
	 * Sets the resource malus.
	 *
	 * @param resourceMalus the new resource malus
	 */
	public void setResourceMalus(List<Resource> resourceMalus) {
		this.resourceMalus = resourceMalus;
	}
	
	 
	 /**
 	 * This method is the one used to access the HashMap containing the variations based 
 	 * on the CardType of the card
 	 * This is needed primarily if not exclusively for the TakeCardAction class.
 	 *
 	 * @author Mirko
 	 * @param cardType the card type
 	 * @return the card type action variation
 	 */
	public int getCardTypeActionVariation(CardType cardType){  
		 return this.cardActionValueVariation.get(cardType);
	 }
	
	/**
	 * An effect that need to modify a variation on a CardType only needs to call this method
	 * passing the CardType and the !VARIATION!.
	 *
	 * @author Mirko
	 * @param cardType the card type
	 * @param variation the variation
	 */
	public void addCardTypeActionVariation(CardType cardType,int variation){
		this.cardActionValueVariation.put(cardType,
				this.cardActionValueVariation.get(cardType)+variation);
	}


	/**
	 * Gets the harvest variation.
	 *
	 * @return the harvest variation
	 */
	public int getHarvestVariation() {
		return harvestVariation;
	}
	
	/**
	 * Sets the harvest variation.
	 *
	 * @param harvestVariation the new harvest variation
	 */
	public void setHarvestVariation(int harvestVariation) {
		this.harvestVariation = harvestVariation;
	}
	
	/**
	 * Gets the production variation.
	 *
	 * @return the production variation
	 */
	public int getProductionVariation() {
		return productionVariation;
	}
	
	/**
	 * Sets the production variation.
	 *
	 * @param productionVariation the new production variation
	 */
	public void setProductionVariation(int productionVariation) {
		this.productionVariation = productionVariation;
	}
	
	/**
	 * Checks if is no floor bonus.
	 *
	 * @return true, if is no floor bonus
	 */
	public boolean isNoFloorBonus() {
		return noFloorBonus;
	}
	
	/**
	 * Sets the no floor bonus.
	 *
	 * @param noFloorBonus the new no floor bonus
	 */
	public void setNoFloorBonus(boolean noFloorBonus) {
		this.noFloorBonus = noFloorBonus;
	}
	
	/**
	 * Checks if is character cards discount.
	 *
	 * @return true, if is character cards discount
	 */
	public boolean isCharacterCardsDiscount() {
		return characterCardsDiscount;
	}
	
	/**
	 * Sets the character cards discount.
	 *
	 * @param characterCardsDiscount the new character cards discount
	 */
	public void setCharacterCardsDiscount(boolean characterCardsDiscount) {
		this.characterCardsDiscount = characterCardsDiscount;
	}
	
	/**
	 * Checks if is building cards discount.
	 *
	 * @return true, if is building cards discount
	 */
	public boolean isBuildingCardsDiscount() {
		return buildingCardsDiscount;
	}
	
	/**
	 * Sets the building cards discount.
	 *
	 * @param buildingCardsDiscount the new building cards discount
	 */
	public void setBuildingCardsDiscount(boolean buildingCardsDiscount) {
		this.buildingCardsDiscount = buildingCardsDiscount;
	}

	
	/**
	 * Checks if is skip round active.
	 *
	 * @return true, if is skip round active
	 */
	public boolean isSkipRoundActive() {
		return skipRoundActive;
	}

	/**
	 * Sets the skip round active.
	 *
	 * @param skipRound the new skip round active
	 */
	public void setSkipRoundActive(boolean skipRound) {
		this.skipRoundActive = skipRound;
	}
	
	/**
	 * Checks if is no market action active.
	 *
	 * @return true, if is no market action active
	 */
	public boolean isNoMarketActionActive() {
		return noMarketActionActive;
	}

	/**
	 * Sets the no market action active.
	 *
	 * @param noMarketActionActive the new no market action active
	 */
	public void setNoMarketActionActive(boolean noMarketActionActive) {
		this.noMarketActionActive = noMarketActionActive;
	}
	
	 /**
 	 * Gets the no card type final points.
 	 *
 	 * @param cardType the card type
 	 * @return the boolean variable of the specified card type
 	 */
	public boolean getNoCardTypeFinalPoints(CardType cardType) {
		return noCardTypeFinalPoints.get(cardType);
	}

	/**
	 * Sets the no card type final points.
	 *
	 * @param cardType the new no card type final points
	 */
	public void setNoCardTypeFinalPoints(CardType cardType) {
		this.noCardTypeFinalPoints.put(cardType, true);
	}
	
	/**
	 * Gets the action value variation based on servants.
	 *
	 * @param servants the servants
	 * @return the action value variation bases on the amount of servants passed and the
	 * servantsDivider of the player
	 */
	public int getActionValueVariationBasedOnServants(int servants) {
		return (int)(servants/this.servantsDivider);
	}
	
	/**
	 * Gets the servants divider.
	 *
	 * @return the servants divider
	 */
	public int getServantsDivider(){
		return this.servantsDivider;
	}

	/**
	 * Should only be accessed by SetServantsDividerEffect.
	 *
	 * @param servantsDivider the new servants divider
	 */
	public void setServantsDivider(int servantsDivider) {
		this.servantsDivider = servantsDivider;

	}
	
	/**
	 * This returns production variation or harvest variation based on the cardType passed.
	 *
	 * @author Mirko
	 * @param cardType the card type
	 * @return the activation variation
	 */
	public int getActivationVariation(CardType cardType){
		if(cardType==CardType.BUILDING)
			return this.productionVariation;
			
		if(cardType==CardType.TERRITORY)
			return this.harvestVariation;
		throw new CardTypeException();
		
	}


	/**
	 * Gets the church support bonus.
	 *
	 * @return the church support bonus
	 */
	public int getChurchSupportBonus() {
		// TODO Auto-generated method stub
		return churchSupportBonus;
	}
	 
	
	
	   
	 
}

