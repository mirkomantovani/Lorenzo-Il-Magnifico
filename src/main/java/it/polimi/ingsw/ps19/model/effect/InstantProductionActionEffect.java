package it.polimi.ingsw.ps19.model.effect;


import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.model.effect.leader.Disapplyable;


/**
 * The Class InstantProductionActionEffect.
 *
 * @author matteo
 */
public class InstantProductionActionEffect extends Effect implements Disapplyable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3257137657687177488L;
	
	/** The production action value. */
	int productionActionValue;

	/**
	 * Instantiates a new instant production action effect.
	 *
	 * @param productionActionValue the production action value
	 */
	public InstantProductionActionEffect(int productionActionValue){
		this.productionActionValue = productionActionValue;
	}
	
	
	/* (non-Javadoc)
	 * for each buildingCard of a player, is activated the ProductionEffect according to the production Value of the card
	 * @see it.polimi.ingsw.ps19.model.effect.Effect#applyEffect()
	 */
	@Override
	public void applyEffect(Player p) {
		//TODO
		/*
		for(BuildingCard c : card.getPlayer().getBuildingDeck()){
			if(c.getPermanentEffect() instanceof ProductionEffect && c.canActivateProductionWith(productionActionValue + c.getPlayer().getProductionModification())){
				new ProductionEffect(c.getPermanentEffect()).applyEffect();
			}
		} */
		
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("You can activate a production with an action value of ");
		builder.append(productionActionValue);
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
