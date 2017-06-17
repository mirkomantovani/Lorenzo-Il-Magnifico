package it.polimi.ingsw.ps19.model.area;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import it.polimi.ingsw.ps19.model.effect.CouncilPrivilegeEffect;
import it.polimi.ingsw.ps19.model.effect.InstantResourcesEffect;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;

/**
 * @author matteo
 *
 */
public class Market implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5295807604863580003L;
	private Map<String,SingleActionSpace> market; // the market slot are ordered from the left to the right 
	private int playersInTheMatch;
												 // as you can find them in the game board
	public Market(int playersInTheMatch){
	
		ResourceChest resourceFirstMarket = new ResourceChest(5,0,0,0,0,0,0);
		ResourceChest resourceSecondMarket = new ResourceChest(0,0,0,5,0,0,0);
		ResourceChest resourceThirdMarket = new ResourceChest(2,0,0,0,0,0,3);
		
		market = new HashMap<String,SingleActionSpace>();
		
		market.put("firstMarket",new SingleActionSpace(1,new InstantResourcesEffect(resourceFirstMarket)));
		market.put("secondMarket", new SingleActionSpace(1, new InstantResourcesEffect(resourceSecondMarket)));
		if(playersInTheMatch == 4){
		market.put("thirdMarket",new SingleActionSpace(1, new InstantResourcesEffect(resourceThirdMarket)));
		market.put("fourthMarket", new SingleActionSpace(1, new CouncilPrivilegeEffect(2)));
		}
		this.playersInTheMatch = playersInTheMatch;
	}


	public SingleActionSpace getMarktActionSpace(String name) {
		return this.market.get(name);
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("---- The Market ---- \n This is the market, by placing into this area you "
				+ "should have a familar with a value of " + market.get("firstMarket").getActionValueRequired() +""
				+ " and you'll gain "
				+ "some resources: \n First market slot: ");
		builder.append(market.get("firstMarket").getEffect().toString());
		
		builder.append("\n Second market slot: ");
		builder.append(market.get("secondMarket").getEffect().toString());
		if (playersInTheMatch == 4){
			builder.append("\n Third market slot: ");
			builder.append(market.get("thirdMarket").getEffect().toString());
			builder.append("\n Fourth market slot: ");
			builder.append(market.get("thirdMarket").getEffect().toString());
		}
		
		
		
		return builder.toString();
	}
	
	

}
