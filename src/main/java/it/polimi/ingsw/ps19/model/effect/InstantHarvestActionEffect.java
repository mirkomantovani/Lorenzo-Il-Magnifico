package it.polimi.ingsw.ps19.model.effect;


import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.model.effect.leader.Disapplyable;

/**
 * The Class InstantHarvestActionEffect.
 *
 * @author matteo
 */
public class InstantHarvestActionEffect extends Effect implements Disapplyable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7562317170470979450L;
	
	/** The harvest action value. */
	private int harvestActionValue;
	
	/**
	 * Instantiates a new instant harvest action effect.
	 *
	 * @param harvestActionValue the harvest action value
	 */
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("You can activate a harvest with an action value of ");
		builder.append(harvestActionValue);
		return builder.toString();
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.effect.leader.Disapplyable#disapplyEffect(it.polimi.ingsw.ps19.Player)
	 */
	@Override
	public void disapplyEffect(Player p) {
		// TODO Auto-generated method stub
		
	}
	
	

}
