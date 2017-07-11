package it.polimi.ingsw.ps19.model.effect;

import it.polimi.ingsw.ps19.model.Player;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;

/**
 * The Class AtomicExchangeEffect is strictly related to the ResourcesExchangeEffect.
 *
 * @author Mirko
 */
public class AtomicExchangeEffect extends Effect {
	

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6641224133172886151L;
	
	/** The resources out. */
	ResourceChest resourcesOut;  
	
	/** The resources in. */
	ResourceChest resourcesIn;

	
	/**
	 * Instantiates a new atomic exchange effect.
	 *
	 * @param resourcesOut the resources out
	 * @param resourcesIn the resources in
	 */
	public AtomicExchangeEffect(ResourceChest resourcesOut,ResourceChest resourcesIn){
		this.resourcesOut=resourcesOut;
		this.resourcesIn=resourcesIn;
	}

	
	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.effect.Effect#applyEffect(it.polimi.ingsw.ps19.Player)
	 */
	@Override
	public void applyEffect(Player player) {
		System.out.println("atomic exchange effect: applying it");
		player.addResources(resourcesIn);
		player.subResources(resourcesOut);	
		System.out.println("atomic exchange effect: applied");
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		
		StringBuilder string=new StringBuilder(" Pay");
		string.append(this.resourcesOut.toString());
		

		string.append(" and get");
		string.append(this.resourcesIn.toString());
		
		return string.toString();
		
	}

}
