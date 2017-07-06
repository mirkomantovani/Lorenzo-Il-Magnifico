package it.polimi.ingsw.ps19.model.effect;

import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.model.effect.leader.Disapplyable;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;

/**
 * This class implements the InstantResourceEffect that gives a certain amount
 * of a certain resource to a player. 
 * 
 * @author Jimmy
 *
 */
public class InstantResourcesEffect extends Effect implements Disapplyable{
	   
	/**
	 * 
	 */
	private static final long serialVersionUID = -6430388980538173536L;
	private ResourceChest effectResourceChest;	
	/**
	 * class constructor
	 * 
	 * @param effectResourceChest it contains all the resources that a player gets as the 
	 *                            result of the effect.  
	 */
	public InstantResourcesEffect(ResourceChest effectResourceChest){
		this.effectResourceChest = effectResourceChest;
	}	
	
	public InstantResourcesEffect(ResourceChest effectResourceChest,CouncilPrivilegeEffect councilPrivilegeEffect){
		this.effectResourceChest = effectResourceChest;
	}	
	
	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.effect.Effect#applyEffect()
	 */
	public void applyEffect(Player p) {
		System.out.println("instantres: applyingeffect");
		p.addResources(effectResourceChest);
		System.out.println("instantres: applied effect");
	}
	

	/* 
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * @author Jimmy
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("You gain:");
		builder.append(effectResourceChest.toString());
		
		return builder.toString();
	}

	@Override
	public void disapplyEffect(Player p) {
		// TODO Auto-generated method stub
		
	}
	
	
}
