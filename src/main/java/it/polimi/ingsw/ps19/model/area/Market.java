package it.polimi.ingsw.ps19.model.area;


import it.polimi.ingsw.ps19.model.effect.CouncilPrivilegeEffect;
import it.polimi.ingsw.ps19.model.effect.InstantResourcesEffect;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;

/**
 * @author matteo
 *
 */
public class Market {
	
	private SingleActionSpace firstMarket;
	private SingleActionSpace secondMarket;
	private SingleActionSpace thirdMarket;
	private SingleActionSpace fourthMarket;
	
	public Market(int playersInTheMatch){
	
		ResourceChest resourceFirstMarket = new ResourceChest(5,0,0,0,0,0,0);
		ResourceChest resourceSecondMarket = new ResourceChest(0,0,0,5,0,0,0);
		ResourceChest resourceThirdMarket = new ResourceChest(2,0,0,0,0,0,3);
		
		firstMarket = new SingleActionSpace(1,new InstantResourcesEffect(resourceFirstMarket));
		secondMarket = new SingleActionSpace(1, new InstantResourcesEffect(resourceSecondMarket));
		if(playersInTheMatch == 4){
		thirdMarket = new SingleActionSpace(1, new InstantResourcesEffect(resourceThirdMarket));
		fourthMarket = new SingleActionSpace(1, new CouncilPrivilegeEffect(2));
		}
	}
	
	public SingleActionSpace getFirstMarket() {
		return firstMarket;
	}
	
	public SingleActionSpace getSecondMarket() {
		return secondMarket;
	}

	public SingleActionSpace getThirdMarket() {
		return thirdMarket;
	}

	public SingleActionSpace getFourthMarket() {
		return fourthMarket;
	}
	
}
