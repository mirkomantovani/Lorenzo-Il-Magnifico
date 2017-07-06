package it.polimi.ingsw.ps19.model.area;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import it.polimi.ingsw.ps19.model.effect.CouncilPrivilegeEffect;
import it.polimi.ingsw.ps19.model.effect.InstantResourcesEffect;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;

/**
 * The Class Market.
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
		
		market = new HashMap<String,SingleActionSpace>();
		
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
		builder.append("---- The Market ---- \n This is the market, by placing into this area you "
				+ "should have a familar with a value of " + market.get("1").getActionValueRequired() +""
				+ " and you'll gain "
				+ "some resources: \n First market slot: ");
		builder.append(market.get("1").getEffect().toString());
		if(market.get("1").isOccupied())
			builder.append(market.get("1").toString());
		
		builder.append("\n Second market slot: ");
		builder.append(market.get("2").getEffect().toString());
		if(market.get("2").isOccupied())
			builder.append(market.get("2").toString());
		if (playersInTheMatch == 4){
			builder.append("\n Third market slot: ");
			builder.append(market.get("3").getEffect().toString());
			if(market.get("3").isOccupied())
				builder.append(market.get("3").toString());
			builder.append("\n Fourth market slot: ");
			builder.append(market.get("4").getEffect().toString());
			if(market.get("4").isOccupied())
				builder.append(market.get("4").toString());
		}
		
		
		
		return builder.toString();
	}
	
	

}
