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
	   
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6430388980538173536L;
	
	/** The effect resource chest. */
	private ResourceChest effectResourceChest;	
	
	/** The council privilege effect. */
	private CouncilPrivilegeEffect councilPrivilegeEffect;
	
	/**
	 * class constructor.
	 *
	 * @param effectResourceChest it contains all the resources that a player gets as the
	 *                            result of the effect.
	 */
	public InstantResourcesEffect(ResourceChest effectResourceChest){
		this.effectResourceChest = effectResourceChest;
	}	
	
	/**
	 * Instantiates a new instant resources effect.
	 *
	 * @param effectResourceChest the effect resource chest
	 * @param councilPrivilegeEffect the council privilege effect
	 */
	public InstantResourcesEffect(ResourceChest effectResourceChest,CouncilPrivilegeEffect councilPrivilegeEffect){
		this.effectResourceChest = effectResourceChest;
		this.councilPrivilegeEffect=councilPrivilegeEffect;
	}	
	
	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.effect.Effect#applyEffect()
	 */
	public void applyEffect(Player p) {
		System.out.println("instantres: applyingeffect");
		p.addResources(effectResourceChest);
		System.out.println("instantres: applied effect");
		if(councilPrivilegeEffect!=null){
			councilPrivilegeEffect.applyEffect(p);
		}
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

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.effect.leader.Disapplyable#disapplyEffect(it.polimi.ingsw.ps19.Player)
	 */
	@Override
	public void disapplyEffect(Player p) {
		// TODO Auto-generated method stub
		
	}
	
	
}
