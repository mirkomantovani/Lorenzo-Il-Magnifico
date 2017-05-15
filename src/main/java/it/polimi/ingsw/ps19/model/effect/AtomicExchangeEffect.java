package it.polimi.ingsw.ps19.model.effect;

import it.polimi.ingsw.ps19.model.resource.Resource;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;

/**
 * The Class AtomicExchangeEffect is strictly related to the ResourcesExchangeEffect
 *
 * @author Mirko
 */
public class AtomicExchangeEffect extends Effect {
	
	Resource resourceOut1;
	Resource resourceOut2;
	Resource resourceOut3;
	Resource resourceIn1;
	Resource resourceIn2;
	
	public AtomicExchangeEffect(Resource resourceOut1, Resource resourceOut2, Resource resourceOut3,
			Resource resourceIn1, Resource resourceIn2) {
		this.resourceOut1 = resourceOut1;
		this.resourceOut2 = resourceOut2;
		this.resourceOut3 = resourceOut3;
		this.resourceIn1 = resourceIn1;
		this.resourceIn2 = resourceIn2;
	}

	@Override
	public void applyEffect() {
		
		if(resourceOut1!=null)
			this.card.getPlayer().getResourceChest().subResource(resourceOut1);
		if(resourceOut1!=null)
			this.card.getPlayer().getResourceChest().subResource(resourceOut1);
		if(resourceOut1!=null)
			this.card.getPlayer().getResourceChest().subResource(resourceOut1);
		if(resourceOut1!=null)
			this.card.getPlayer().getResourceChest().addResource(resourceOut1);
		if(resourceOut1!=null)
			this.card.getPlayer().getResourceChest().addResource(resourceOut1);
		
	}
	
	@Override
	public String toString(){
		
		StringBuilder string=new StringBuilder(" Pay");
		
		string.append(resourceOut1.toString());
		if(resourceOut2!=null)string.append(" and"+resourceOut2.toString());
		if(resourceOut3!=null)string.append(" and"+resourceOut3.toString());
		string.append(" and get");
		string.append(resourceIn1.toString());
		if(resourceIn2!=null)string.append(" and"+resourceIn2.toString());
		
		return string.toString();
		
	}

}
