package it.polimi.ingsw.ps19.model.effect;


/**
 * @author matteo
 *
 */
public class HarvestBonusEffect extends Effect {
	
	int value;
	
	public HarvestBonusEffect(int value){
	this.value = value;	
	}

	@Override
	public void applyEffect() {
		card.getPlayer().getBonuses().setHarvestVariation(value);
		
	}
	
	@Override
	public String toString() {
		if(value >= 0)
			return "You gain a + " + value + " to your harvest value";
		else
			return "You gain a " + value + " to your harvest value";
	}
	

}
