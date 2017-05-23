package it.polimi.ingsw.ps19.model.effect;


/**
 * @author matteo
 *
 */
public class ProductionBonusEffect extends CardEffect {
	
	int value;
	
	public ProductionBonusEffect(int value){
		this.value = value;
	}

	@Override
	public void applyEffect() {
		card.getPlayer().getBonuses().setProductionVariation(value);
		
	}
	
	@Override
	public String toString() {
		if(value >= 0)
			return "You gain a + " + value + " to your production value";
		else
			return "You gain a " + value + " to your production value";
	}
	

}
