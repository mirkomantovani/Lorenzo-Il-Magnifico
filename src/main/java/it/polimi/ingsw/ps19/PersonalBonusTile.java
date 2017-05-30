package it.polimi.ingsw.ps19;

import it.polimi.ingsw.ps19.model.effect.InstantResourcesEffect;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;

/**
 * @author Jimmy
 *
 */
public class PersonalBonusTile {
		
	InstantResourcesEffect firstInstantResourceChest;
	InstantResourcesEffect secondInstantResourceChest;
	
	public PersonalBonusTile(InstantResourcesEffect firstEffect, InstantResourcesEffect secondEffect){
		this.firstInstantResourceChest=firstEffect;
		this.secondInstantResourceChest=secondEffect;
	}	
	
	
	@Override
	public String toString() {
		return firstInstantResourceChest.toString() + " everytime you activate a production and "
				+ secondInstantResourceChest.toString() + " everytime you a activate a harvest" ;
	}
	
}
