package it.polimi.ingsw.ps19.model.effect;

import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;

/**
 * The Class AtomicExchangeEffect is strictly related to the ResourcesExchangeEffect
 *
 * @author Mirko
 */
public class AtomicExchangeEffect extends Effect {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 6641224133172886151L;
	ResourceChest resourcesOut;  
	ResourceChest resourcesIn;

	
	public AtomicExchangeEffect(ResourceChest resourcesOut,ResourceChest resourcesIn){
		this.resourcesOut=resourcesOut;
		this.resourcesIn=resourcesIn;
	}

	
	@Override
	public void applyEffect(Player player) {
		player.addResources(resourcesIn);
		player.subResources(resourcesOut);	
	}
	
	@Override
	public String toString(){
		
		StringBuilder string=new StringBuilder(" Pay");
		string.append(this.resourcesOut.toString());
		

		string.append(" and get");
		string.append(this.resourcesIn.toString());
		
		return string.toString();
		
	}

}
