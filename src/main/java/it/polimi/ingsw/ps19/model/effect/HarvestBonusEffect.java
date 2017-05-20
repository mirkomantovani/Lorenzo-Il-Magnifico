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
		card.getPlayer().setHarvestModification(value);
		
	}
	
	

}
