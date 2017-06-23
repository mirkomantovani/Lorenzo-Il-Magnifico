package it.polimi.ingsw.ps19.model.effect;


import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.model.effect.leader.Disapplyable;

/**
 * @author matteo
 *
 */
public class InstantHarvestActionEffect extends Effect implements Disapplyable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7562317170470979450L;
	private int harvestActionValue;
	
	public InstantHarvestActionEffect(int harvestActionValue) {
		this.harvestActionValue = harvestActionValue;
		
	}

	/* (non-Javadoc)
	 * for each TerritoryCard of a player, is activated the HarvestEffect according to the harvest Value of the card
	 * @see it.polimi.ingsw.ps19.model.effect.Effect#applyEffect()
	 */
	@Override
	public void applyEffect(Player p) {
		
		//TODO
		/*

		for(TerritoryCard c : card.getPlayer().getTerritoryDeck()){
			if(c.getPermanentEffect() instanceof HarvestEffect && c.canActivateHarvestWith(harvestActionValue + c.getPlayer().getHarvestModification())){
				new ProductionEffect(c.getPermanentEffect()).applyEffect();
			}
		}
		
		*/
	
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("You can activate a harvest with an action value of ");
		builder.append(harvestActionValue);
		return builder.toString();
	}

	@Override
	public void disapplyEffect(Player p) {
		// TODO Auto-generated method stub
		
	}
	
	

}
