package it.polimi.ingsw.ps19.model.effect;

import it.polimi.ingsw.ps19.Player;

/**
 * All the CharacterCards activate a flag variable in the bonuses related to a player
 * so they can be considered as "immediate" because what they do is just set a flag 
 * when acquired hence when its immediateEffect activates.
 * 
 * @author Jimmy
 *
 */
public class CharacterImmediateEffect extends Effect{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -855007535102848648L;
	Effect immediateEffect;
	CouncilPrivilegeEffect immediatePrivilegeEffect;
	
	
	//TODO DA TESTARE!!!!!!!!!!
	public CharacterImmediateEffect(Effect immediateEffect){
		this.immediateEffect = immediateEffect;
		
	}
	
	public CharacterImmediateEffect(CouncilPrivilegeEffect immediateEffectWithChoice){
		this.immediatePrivilegeEffect = immediateEffectWithChoice;
		
	}
	
	@Override
	public void applyEffect(Player p) {
		immediateEffect.applyEffect(p);   
		immediatePrivilegeEffect.applyEffect(p); //This line activates the permanent effect
						//so that the related bonuses can be set
						//and effect permanently the player's game 
	}
	


	
	@Override
	public String toString() {	
		if(immediateEffect!=null)
			return immediateEffect.toString();
		else
			return immediatePrivilegeEffect.toString();
	}
}
