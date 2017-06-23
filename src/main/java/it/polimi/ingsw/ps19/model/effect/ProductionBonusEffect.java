package it.polimi.ingsw.ps19.model.effect;

import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.model.effect.leader.Disapplyable;

/**
 * @author matteo
 *
 */
public class ProductionBonusEffect extends Effect implements Disapplyable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -546425703104740489L;
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

	@Override
	public void disapplyEffect(Player p) {
		// TODO Auto-generated method stub
		
	}
	

}
