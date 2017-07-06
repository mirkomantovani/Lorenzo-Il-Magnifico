package it.polimi.ingsw.ps19;

import java.io.Serializable;

import it.polimi.ingsw.ps19.model.effect.InstantResourcesEffect;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;

/**
 * The Class PersonalBonusTile.
 *
 * @author Jimmy
 */
public class PersonalBonusTile implements Serializable  {
		
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5558558088929570115L;
	
	/** The first instant resource chest. */
	InstantResourcesEffect firstInstantResourceChest;
	
	/** The second instant resource chest. */
	InstantResourcesEffect secondInstantResourceChest;
	
	/**
	 * Instantiates a new personal bonus tile.
	 *
	 * @param firstEffect the first effect
	 * @param secondEffect the second effect
	 */
	public PersonalBonusTile(InstantResourcesEffect firstEffect, InstantResourcesEffect secondEffect){
		this.firstInstantResourceChest=firstEffect;
		this.secondInstantResourceChest=secondEffect;
	}	
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return firstInstantResourceChest.toString() + " everytime you activate a production and "
				+ secondInstantResourceChest.toString() + " everytime you a activate a harvest" ;
	}
	
}
