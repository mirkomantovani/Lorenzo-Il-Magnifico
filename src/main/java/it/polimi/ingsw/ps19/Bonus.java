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
	 
	 int harvestVariation; //it means the increase/decrease of the Harvest action value for the specific player
	 int productionVariation; // "  "  "  "    //in production/harvest action
	 
	 private Map<CardType,Integer> actionValueVariation;
	 
	 boolean noFloorBonus;
	 boolean characterCardsDiscount; //This boolean is true if "DAMA"'s effect is active.
	 boolean buildingCardsDiscount;  //This boolean is true if "COSTRUTTORE"'s effect is active.
	 
	 //excommunication
	 List<Resource> resourceMalus;   //in instantresource
	 
	 
	 /**
	  * The constructor has to be called at the creation of the player.
	  * N.B. it also adds an entry for the ANY of CardType, can be useful 
	  * @author Mirko
	 * 
	 */
	public Bonus() {
		actionValueVariation=new HashMap<>();
		for(int i=0;i<CardType.values().length;i++)
			actionValueVariation.put(CardType.values()[i], 0);
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
		 return this.actionValueVariation.get(cardType);
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
		this.actionValueVariation.put(cardType,
				this.actionValueVariation.get(cardType)+variation);
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

