package it.polimi.ingsw.ps19.model.effect;

import it.polimi.ingsw.ps19.Player;

/**
 * This effect takes two effects and apply them in a row
 * 
 * @author Jimmy
 *
 */
public class MultipleEffect extends Effect {

	Effect firstEffect;
	Effect secondEffect;
	CouncilPrivilegeEffect secondEffectWithChoice;
	
	
	//TODO DA TESTARE!!!!!!
	
	public MultipleEffect(Effect firstEffect, Effect secondEffect){
		this.firstEffect = firstEffect;
		this.secondEffect = secondEffect;	

	}
	
	public MultipleEffect(Effect firstEffect, CouncilPrivilegeEffect secondEffectWithChoice){
		this.firstEffect = firstEffect;
		this.secondEffectWithChoice = secondEffectWithChoice;
	}

	public void applyEffect(Player p) {
		firstEffect.applyEffect(p);
		secondEffect.applyEffect(p);
	}
	
	public void applyEffect(int choice,Player p){
		firstEffect.applyEffect(p);
		secondEffectWithChoice.applyEffect(choice,p);
	}
	
	
	
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append(firstEffect.toString() + " and ");
		if(secondEffect != null)
			string.append(secondEffect.toString());
		else
			string.append(secondEffectWithChoice.toString());
		return string.toString();
	}

}
