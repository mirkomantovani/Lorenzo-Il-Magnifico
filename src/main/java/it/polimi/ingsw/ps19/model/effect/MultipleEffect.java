package it.polimi.ingsw.ps19.model.effect;

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
		firstEffect.setCard(this.card);
		secondEffect.setCard(this.card);
	}
	
	public MultipleEffect(Effect firstEffect, CouncilPrivilegeEffect secondEffectWithChoice){
		this.firstEffect = firstEffect;
		this.secondEffectWithChoice = secondEffectWithChoice;
		firstEffect.setCard(this.card);
		secondEffectWithChoice.setCard(this.card);
	}

	public void applyEffect() {
		firstEffect.applyEffect();
		secondEffect.applyEffect();
	}
	
	public void applyEffect(int choice){
		firstEffect.applyEffect();
		secondEffectWithChoice.applyEffect(choice);
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
