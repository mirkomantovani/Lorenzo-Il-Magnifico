package it.polimi.ingsw.ps19.model.effect;

import it.polimi.ingsw.ps19.Resource;
import it.polimi.ingsw.ps19.ResourceChest;

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




	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.effect.Effect#applyEffect()
	 */
	/**
	 * @param resourceChest is the resource chest (probably of the player) that I have to add resources to
	 */
	
//	public void applyEffect(ResourceChest resourceChest) {
//		resourceChest.subResource(resourceOut1);  //no, essendo l'effetto relazionato a una carta e la carta relazionata
//	al player possiamo benissimo prenderlo da queste relazione il chest a cui deve essere applicata
//	}

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

}
