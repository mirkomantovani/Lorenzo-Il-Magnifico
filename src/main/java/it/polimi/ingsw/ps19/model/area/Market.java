package it.polimi.ingsw.ps19.model.area;


import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import it.polimi.ingsw.ps19.model.effect.CouncilPrivilegeEffect;
import it.polimi.ingsw.ps19.model.effect.InstantResourcesEffect;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;

/**
 * The Class Market.
 * This class represents the market area, this is made by four different single action spaces
 * where you can place one familiar to get a specific resource bonus
 *
 * @author matteo
 */
public class Market implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5295807604863580003L;
	
	/** The market. */
	private Map<String,SingleActionSpace> market; // the market slot are ordered from the left to the right 
	
	/** The players in the match. */
	private int playersInTheMatch;
												 
 												/**
 												 * Instantiates a new market.
 												 *
 												 * @param playersInTheMatch the players in the match
 												 */
 												// as you can find them in the game board
	public Market(int playersInTheMatch){
	
		ResourceChest resourceFirstMarket = new ResourceChest(5,0,0,0,0,0,0);
		ResourceChest resourceSecondMarket = new ResourceChest(0,0,0,5,0,0,0);
		ResourceChest resourceThirdMarket = new ResourceChest(2,0,0,0,0,0,3);
		
		market = new LinkedHashMap<String,SingleActionSpace>();
		
		market.put("1",new SingleActionSpace(1,new InstantResourcesEffect(resourceFirstMarket)));
		market.put("2", new SingleActionSpace(1, new InstantResourcesEffect(resourceSecondMarket)));
		if(playersInTheMatch == 4){
		market.put("3",new SingleActionSpace(1, new InstantResourcesEffect(resourceThirdMarket)));
		market.put("4", new SingleActionSpace(1, new CouncilPrivilegeEffect(2)));
		}
		this.playersInTheMatch = playersInTheMatch;
	}


	/**
	 * Gets the markt action space.
	 *
	 * @param name the name
	 * @return the markt action space
	 */
	public SingleActionSpace getMarktActionSpace(String name) {
		return this.market.get(name);
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°The Market°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°\n\n");
		
		if(market.get("1")!= null)
			builder.append("Cost: " + market.get("1").getActionValueRequired() + "\n\n");
		for(SingleActionSpace marketSlot: market.values()){
			if(marketSlot != null){
				if(marketSlot.getEffect()!=null)
					builder.append(marketSlot.getEffect().toString() + "\n");
			
				if(marketSlot.isOccupied())
					builder.append(marketSlot.toString() + "\n");
			}
		}
		
		builder.append("\n°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°\n\n");
		
		
		return builder.toString();
	}
	
	

}
