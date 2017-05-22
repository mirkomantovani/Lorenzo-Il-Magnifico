package it.polimi.ingsw.ps19.model.effect;

/**
 * @author Jimmy
 *
 */
public class RaiseHarvestValueEffect extends Effect{
	
	int raiseValue;
	
	public RaiseHarvestValueEffect(int raiseValue) {
		this.raiseValue = raiseValue;
	}

	
	public void applyEffect() {
		this.getAssociatedPlayer().getBonuses().setHarvestVariation(raiseValue); 
	}
	
	@Override
	public String toString() {
		if(raiseValue >= 0)
			return "You gain a + " + raiseValue + " to your harvest value";
		else
			return "You gain a " + raiseValue + " to your harvest value";
	}
	
	
}
