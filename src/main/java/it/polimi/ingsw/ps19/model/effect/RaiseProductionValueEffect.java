package it.polimi.ingsw.ps19.model.effect;

public class RaiseProductionValueEffect extends Effect{
	 int raiseValue;
	 
	 public RaiseProductionValueEffect(int raiseValue){
		 this.raiseValue = raiseValue;
	}

	
	public void applyEffect() {
		this.getAssociatedPlayer().getBonuses().setProductionVariation(raiseValue);
	}
	
	@Override
	public String toString() {
		if(raiseValue >= 0)
			return "You gain a + " + raiseValue + " to your production value";
		else
			return "You gain a " + raiseValue + " to your production value";
	}
	
}
