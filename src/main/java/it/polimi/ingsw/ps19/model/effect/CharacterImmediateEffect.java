package it.polimi.ingsw.ps19.model.effect;

import it.polimi.ingsw.ps19.model.Player;

/**
 * All the CharacterCards activate a flag variable in the bonuses related to a player
 * so they can be considered as "immediate" because what they do is just set a flag 
 * when acquired hence when its immediateEffect activates.
 * 
 * @author Jimmy
 *
 */
public class CharacterImmediateEffect extends Effect{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -855007535102848648L;
	
	/** The immediate effect. */
	Effect immediateEffect;
	
	/** The immediate privilege effect. */
	CouncilPrivilegeEffect immediatePrivilegeEffect;
	
	
	/**
	 * Instantiates a new character immediate effect.
	 *
	 * @param immediateEffect the immediate effect
	 */
	//TODO DA TESTARE!!!!!!!!!!
	public CharacterImmediateEffect(Effect immediateEffect){
		this.immediateEffect = immediateEffect;
		
	}
	
	/**
	 * Instantiates a new character immediate effect.
	 *
	 * @param immediateEffectWithChoice the immediate effect with choice
	 */
	public CharacterImmediateEffect(CouncilPrivilegeEffect immediateEffectWithChoice){
		this.immediatePrivilegeEffect = immediateEffectWithChoice;
		
	}
	
	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.effect.Effect#applyEffect(it.polimi.ingsw.ps19.Player)
	 */
	@Override
	public void applyEffect(Player p) {
		if(immediateEffect!=null)
		immediateEffect.applyEffect(p);   
		if(immediatePrivilegeEffect!=null)
		immediatePrivilegeEffect.applyEffect(p); //This line activates the permanent effect
						//so that the related bonuses can be set
						//and effect permanently the player's game 
	}
	


	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {	
		if(immediateEffect!=null)
			return immediateEffect.toString();
		else
			return immediatePrivilegeEffect.toString();
	}
}
