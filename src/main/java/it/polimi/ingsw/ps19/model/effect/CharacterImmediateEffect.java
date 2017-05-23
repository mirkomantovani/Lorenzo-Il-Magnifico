package it.polimi.ingsw.ps19.model.effect;

/**
 * All the CharacterCards activate a flag variable in the bonuses related to a player
 * so they can be considered as "immediate" because what they do is just set a flag 
 * when acquired hence when its immediateEffect activates.
 * 
 * @author Jimmy
 *
 */
public class CharacterImmediateEffect extends CardEffect{
	
	CardEffect immediateEffect;
	CouncilPrivilegeEffect immediateEffectWithChoice;
	
	
	//TODO DA TESTARE!!!!!!!!!!
	public CharacterImmediateEffect(CardEffect immediateEffect){
		this.immediateEffect = immediateEffect;
		immediateEffect.setCard(this.card);
	}
	
	public CharacterImmediateEffect(CouncilPrivilegeEffect immediateEffectWithChoice){
		this.immediateEffectWithChoice = immediateEffectWithChoice;
		immediateEffectWithChoice.setCard(this.card);
	}
	
	public void applyEffect() {
		immediateEffect.applyEffect();   
		this.getCard().getPermanentEffect().applyEffect(); //This line activates the permanent effect
														   //so that the related bonuses can be set
														   //and effect permanently the player's game 
	}
	
	public void applyEffect(int choice){
		immediateEffectWithChoice.applyEffect(choice);
		if(this.getCard().getPermanentEffect() != null)
			this.getCard().getPermanentEffect().applyEffect();
	}

	
	@Override
	public String toString() {	
		if(immediateEffect!=null)
			return immediateEffect.toString();
		else
			return immediateEffectWithChoice.toString();
	}
}
