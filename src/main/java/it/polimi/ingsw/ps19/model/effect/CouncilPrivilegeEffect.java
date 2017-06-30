	package it.polimi.ingsw.ps19.model.effect;

import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.model.effect.leader.Disapplyable;

/**
 * @author matteo
 *
 */
public class CouncilPrivilegeEffect extends Effect implements Disapplyable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2209211330966096453L;
	private final int PRIVILEGE_COIN = 2;
	private final int PRIVILEGE_WOOD = 1;
	private final int PRIVILEGE_STONE = 1;
	private final int PRIVILEGE_FAITHPOINT = 1;
	private final int PRIVILEGE_SERVANT = 2;
	private final int PRIVILEGE_MILITARYPOINT = 2;
	
	int privilegeAmount;

	public CouncilPrivilegeEffect(int privilegeAmount) {
		this.privilegeAmount = privilegeAmount;
	}


		
	public void applyEffect(Player p){
		p.setCouncilPrivilege(privilegeAmount);
	}
	



	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("You can take " + privilegeAmount + " privileges");
		return builder.toString();
	}



	@Override
	public void disapplyEffect(Player p) {
		// TODO Auto-generated method stub
		
	}
		
		
		
	}	
