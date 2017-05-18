package it.polimi.ingsw.ps19.model.effect;


import it.polimi.ingsw.ps19.model.card.BuildingCard;


/**
 * @author matteo
 *
 */
public class InstantProductionActionEffect extends Effect {
	
	int productionActionValue;

	public InstantProductionActionEffect(int productionActionValue){
		this.productionActionValue = productionActionValue;
	}
	
	
	/* (non-Javadoc)
	 * for each buildingCard of a player, is activated the ProductionEffect according to the production Value of the card
	 * @see it.polimi.ingsw.ps19.model.effect.Effect#applyEffect()
	 */
	@Override
	public void applyEffect() {
		//TODO
		/*
		for(BuildingCard c : card.getPlayer().getBuildingDeck()){
			if(c.getPermanentEffect() instanceof ProductionEffect && c.canActivateProductionWith(productionActionValue + c.getPlayer().getProductionModification())){
				new ProductionEffect(c.getPermanentEffect()).applyEffect();
			}
		} */
		
	}
	
	
	

}
