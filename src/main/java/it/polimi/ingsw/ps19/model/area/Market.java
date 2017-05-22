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
	
 // this chest will be filled with the resources you can take in a  market spot
	
	
	public Market(int playersInTheMatch){
	
		ResourceChest resources = new ResourceChest(5,0,0,0,0,0,0); // this chest will be filled with the resources you can take in a  market spot
		
		firstMarket = new ActionSpace(1,new InstantResourcesEffect(resources));
		resources.getCoins().setAmount(0);
		resources.getServants().setAmount(5);
		secondMarket = new ActionSpace(1, new InstantResourcesEffect(resources));
		if(playersInTheMatch == 4){
		resources.getCoins().setAmount(2);
		resources.getMilitaryPoint().setAmount(3);
		thirdMarket = new ActionSpace(1, new InstantResourcesEffect(resources));
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
