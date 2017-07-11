	package it.polimi.ingsw.ps19.model.effect;

import it.polimi.ingsw.ps19.model.Player;
import it.polimi.ingsw.ps19.model.effect.leader.Disapplyable;

/**
 * The Class CouncilPrivilegeEffect.
 *
 * @author matteo
 */
public class CouncilPrivilegeEffect extends Effect implements Disapplyable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2209211330966096453L;
	
	
	/** The privilege amount. */
	int privilegeAmount;

	/**
	 * Instantiates a new council privilege effect.
	 *
	 * @param privilegeAmount the privilege amount
	 */
	public CouncilPrivilegeEffect(int privilegeAmount) {
		this.privilegeAmount = privilegeAmount;
	}


		
	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.effect.Effect#applyEffect(it.polimi.ingsw.ps19.Player)
	 */
	public void applyEffect(Player p){
		System.out.println("applying privilege");
		p.setCouncilPrivilege(privilegeAmount);
		System.out.println("privilege applied");
	}
	



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("You can take " + privilegeAmount + " privileges");
		return builder.toString();
	}



	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.effect.leader.Disapplyable#disapplyEffect(it.polimi.ingsw.ps19.Player)
	 */
	@Override
	public void disapplyEffect(Player p) {
		// TODO Auto-generated method stub
		
	}
		
		
		
	}	
