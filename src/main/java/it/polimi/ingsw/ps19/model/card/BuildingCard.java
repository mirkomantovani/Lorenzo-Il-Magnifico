package it.polimi.ingsw.ps19.model.card;

import it.polimi.ingsw.ps19.Period;
import it.polimi.ingsw.ps19.model.effect.Effect;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;
import it.polimi.ingsw.ps19.model.effect.ProductionEffect;
import it.polimi.ingsw.ps19.model.effect.ResourcesExchangeEffect;

/**
 * The Class BuildingCard.
 *
 * @author Mirko
 */
public class BuildingCard extends DevelopmentCard {
	
	private int productionActivationCost;

	/**
	 * Instantiates a new building card.
	 *
	 * @param id the id
	 * @param name the name
	 * @param period the period
	 * @param cost the cost
	 * @param immediateEffect the immediate effect
	 * @param permanentEffect the permanent effect
	 */
	
	public BuildingCard(int id, String name, Period period, ResourceChest cost, Effect immediateEffect,
			Effect permanentEffect, int productionActivationCost) {

		super(id, name, period, cost, immediateEffect, permanentEffect);
		this.productionActivationCost = productionActivationCost;
		this.cardType=CardType.BUILDING;
	}

	/**
	 * Can activate production with a certain production value
	 *
	 * @param productionValue the production value
	 * @return true, if the production effect of this card can be activated with the given production Value
	 */
	public boolean canActivateProductionWith(int productionValue){
    	return productionValue>productionActivationCost;
	}
	
	@Override
	public String toString() {	
    	StringBuilder string = new StringBuilder();
    	string.append(super.toString() + "\nProduction cost: " + productionActivationCost + "\nProduction effect: ");
    	if(this.permanentEffect!=null)
    		string.append(permanentEffect.toString());
    	string.append("\n");
    	
    	
    	return string.toString();
	}

	@Override
	public int getActivationCost() {
		return this.productionActivationCost;
	}
	
	public boolean hasProductionChoice(){
		ProductionEffect productionEffect=
		(ProductionEffect)this.getPermanentEffect();
		if(productionEffect.isResourcesExchangeEffect()){
			if(productionEffect.getResourcesExchangeEffect().hasAlternativeEffect())
				return true;
		}
		return false;
			
	}
	
	
	
	public String[] getProductionChoice(){
		String[] choices = null;
		
		ProductionEffect productionEffect=
		(ProductionEffect)this.getPermanentEffect();
		if(productionEffect.isResourcesExchangeEffect()){
			ResourcesExchangeEffect resourcesExchangeEffect=productionEffect.getResourcesExchangeEffect();
			choices=new String[2];
			choices[0]=resourcesExchangeEffect.getNormalEffectToString();
			choices[1]=resourcesExchangeEffect.getNormalEffectToString();
//			
		}

		return choices;  //won't be equal to null if we check with method hasProductionChoice
		//before calling this one
			
	}
	
	
}
