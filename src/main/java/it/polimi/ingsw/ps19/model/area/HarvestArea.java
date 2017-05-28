package it.polimi.ingsw.ps19.model.area;

import it.polimi.ingsw.ps19.model.effect.HarvestBonusEffect;

public class HarvestArea extends IndustrialArea{
	
	public HarvestArea(){
		super();
		//The "MALUS" costant is defined in IndustrialArea
		this.multipleSlot = new SingleActionSpace(SLOT_COST, new HarvestBonusEffect(MALUS));
	}
	
}
