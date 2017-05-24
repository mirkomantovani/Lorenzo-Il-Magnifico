package it.polimi.ingsw.ps19.model.area;


import it.polimi.ingsw.ps19.model.effect.CouncilPrivilegeEffect;
import it.polimi.ingsw.ps19.model.effect.InstantResourcesEffect;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;

/**
 * @author matteo
 *
 */
public class Market {
	
	private ActionSpace firstMarket;
	private ActionSpace secondMarket;
	private ActionSpace thirdMarket;
	private ActionSpace fourthMarket;
	
	public Market(int playersInTheMatch){
	
		ResourceChest resourceFirstMarket = new ResourceChest(5,0,0,0,0,0,0);
		ResourceChest resourceSecondMarket = new ResourceChest(0,0,0,5,0,0,0);
		ResourceChest resourceThirdMarket = new ResourceChest(2,0,0,0,0,0,3);
		
		firstMarket = new ActionSpace(1,new InstantResourcesEffect(resourceFirstMarket));
		secondMarket = new ActionSpace(1, new InstantResourcesEffect(resourceSecondMarket));
		if(playersInTheMatch == 4){
		thirdMarket = new ActionSpace(1, new InstantResourcesEffect(resourceThirdMarket));
		fourthMarket = new ActionSpace(1, new CouncilPrivilegeEffect(2));
		}
	}
	
	public ActionSpace getFirstMarket() {
		return firstMarket;
	}
	
	public ActionSpace getSecondMarket() {
		return secondMarket;
	}

	public ActionSpace getThirdMarket() {
		return thirdMarket;
	}

	public ActionSpace getFourthMarket() {
		return fourthMarket;
	}

	
}
