package it.polimi.ingsw.ps19.model.effect;

import it.polimi.ingsw.ps19.Player;

/**
 * @author matteo
 *
 */
public class ProductionBonusEffect extends Effect {
	
	int value;
	
	public ProductionBonusEffect(int value){
		this.value = value;
	}

	@Override
	public void applyEffect(Player p) {
		p.getBonuses().setProductionVariation(value);
		
	}
	
	@Override
	public String toString() {
		if(value >= 0)
			return "You gain a + " + value + " to your production value";
		else
			return "You gain a " + value + " to your production value";
	}
	

}
