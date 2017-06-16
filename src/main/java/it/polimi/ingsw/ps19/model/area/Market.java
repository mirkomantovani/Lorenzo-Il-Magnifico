package it.polimi.ingsw.ps19.model.area;


import java.util.HashMap;
import java.util.Map;

import it.polimi.ingsw.ps19.model.effect.CouncilPrivilegeEffect;
import it.polimi.ingsw.ps19.model.effect.InstantResourcesEffect;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;

/**
 * @author matteo
 *
 */
public class Market {
	
	private Map<String,SingleActionSpace> market; // the market slot are ordered from the left to the right 
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
	}


	public SingleActionSpace getMarktActionSpace(String name) {
		return this.market.get(name);
	}
	

}
