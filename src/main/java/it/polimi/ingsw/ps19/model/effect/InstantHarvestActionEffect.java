package it.polimi.ingsw.ps19.model.effect;


import it.polimi.ingsw.ps19.model.card.BuildingCard;
import it.polimi.ingsw.ps19.model.card.TerritoryCard;

/**
 * @author matteo
 *
 */
public class InstantHarvestActionEffect extends Effect {
	
	int harvestActionValue;
	
	public InstantHarvestActionEffect(int harvestActionValue) {
		this.harvestActionValue = harvestActionValue;
		
	}

	/* (non-Javadoc)
	 * for each TerritoryCard of a player, is activated the HarvestEffect according to the harvest Value of the card
	 * @see it.polimi.ingsw.ps19.model.effect.Effect#applyEffect()
	 */
	@Override
	public void applyEffect() {
		
		//TODO
		/*

		for(TerritoryCard c : card.getPlayer().getTerritoryDeck()){
			if(c.getPermanentEffect() instanceof HarvestEffect && c.canActivateHarvestWith(harvestActionValue + c.getPlayer().getHarvestModification())){
				new ProductionEffect(c.getPermanentEffect()).applyEffect();
			}
		}
		
		*/
	
	}

}
