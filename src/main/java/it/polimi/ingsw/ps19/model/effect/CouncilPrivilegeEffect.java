	package it.polimi.ingsw.ps19.model.effect;

	import java.util.ArrayList;

	import it.polimi.ingsw.ps19.model.resource.Coin;
	import it.polimi.ingsw.ps19.model.resource.FaithPoint;
	import it.polimi.ingsw.ps19.model.resource.MilitaryPoint;
	import it.polimi.ingsw.ps19.model.resource.Resource;
	import it.polimi.ingsw.ps19.model.resource.Servant;
	import it.polimi.ingsw.ps19.model.resource.Stone;
	import it.polimi.ingsw.ps19.model.resource.Wood;

/**
 * @author matteo
 *
 */
public class CouncilPrivilegeEffect extends Effect {
	
	private final int PRIVILEGE_COIN = 2;
	private final int PRIVILEGE_WOOD = 1;
	private final int PRIVILEGE_STONE = 1;
	private final int PRIVILEGE_FAITHPOINT = 1;
	private final int PRIVILEGE_SERVANT = 2;
	private final int PRIVILEGE_MILITARYPOINT = 2;
	
	private ArrayList<Resource> choice;
	

	public CouncilPrivilegeEffect(ArrayList<Resource> choices) {
		this.choice = choices;
	}


	/**
	 * @author matteo
	 *
	 */
		@Override
		public void applyEffect() {
			for (Resource res : choice){
				
				if(res instanceof Coin){	
					this.card.getPlayer().getResourceChest().getCoins().add(PRIVILEGE_COIN);
				} else if(res instanceof Stone || res instanceof Wood){
						this.card.getPlayer().getResourceChest().getStones().add(PRIVILEGE_STONE);
						this.card.getPlayer().getResourceChest().getWoods().add(PRIVILEGE_WOOD);
					}else if(res instanceof FaithPoint){
						this.card.getPlayer().getResourceChest().getFaithPoint().add(PRIVILEGE_FAITHPOINT);
						}else if(res instanceof Servant){
							this.card.getPlayer().getResourceChest().getServants().add(PRIVILEGE_SERVANT);
							}else if(res instanceof MilitaryPoint){
								this.card.getPlayer().getResourceChest().getMilitaryPoint().add(PRIVILEGE_MILITARYPOINT);
								}
			     }
			
		}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("You can take one of these: 2 coins, 1 stone & 1 wood, 2 servants, 1 faithPoints, 2 militaryPoints");
		return builder.toString();
	}
		
		
		
	}
