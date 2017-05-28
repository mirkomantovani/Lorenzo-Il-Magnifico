package it.polimi.ingsw.ps19.model.area;

import it.polimi.ingsw.ps19.model.effect.ProductionBonusEffect;

public class ProductionArea extends IndustrialArea{
	
	public ProductionArea(){
		super();
		//The "MALUS" costant is defined in IndustrialArea
		this.multipleSlot = new SingleActionSpace(SLOT_COST, new ProductionBonusEffect(MALUS));
	}
	
}
