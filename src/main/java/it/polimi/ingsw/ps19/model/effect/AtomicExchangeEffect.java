package it.polimi.ingsw.ps19.model.effect;

import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;

/**
 * The Class AtomicExchangeEffect is strictly related to the ResourcesExchangeEffect
 *
 * @author Mirko
 */
public class AtomicExchangeEffect extends Effect {
	

	ResourceChest resourcesOut;  
	ResourceChest resourcesIn;

	
	public AtomicExchangeEffect(ResourceChest resourcesOut,ResourceChest resourcesIn){
		this.resourcesOut=resourcesOut;
		this.resourcesIn=resourcesIn;
	}

	
	@Override
	public void applyEffect(Player player) {
		player.getResourceChest().subChest(resourcesOut);
		player.getResourceChest().addChest(resourcesIn);	
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
