package it.polimi.ingsw.ps19.model.effect;

import it.polimi.ingsw.ps19.Coin;
import it.polimi.ingsw.ps19.FaithPoint;
import it.polimi.ingsw.ps19.MilitaryPoint;
import it.polimi.ingsw.ps19.Resource;
import it.polimi.ingsw.ps19.Servant;
import it.polimi.ingsw.ps19.Stone;
import it.polimi.ingsw.ps19.Wood;

import java.util.Random;


/**
 * @author matteo
 *
 */
public class CouncilPrivilegeEffect extends Effect {
	
	public void applyEffect(Resource choice) {
		if(choice instanceof Coin){	
		this.card.getPlayer().getResourceChest().getCoins().add(2);
		} else if(choice instanceof Stone || choice instanceof Wood){
				this.card.getPlayer().getResourceChest().getStones().add(1);
				this.card.getPlayer().getResourceChest().getWoods().add(1);
			}else if(choice instanceof FaithPoint){
				this.card.getPlayer().getResourceChest().getFaithPoint().add(1);
				}else if(choice instanceof Servant){
					this.card.getPlayer().getResourceChest().getServants().add(2);
					}else if(choice instanceof MilitaryPoint){
						this.card.getPlayer().getResourceChest().getMilitaryPoint().add(2);
						}
		
		
	}
	
	public void applyEffect(Resource choice, Resource choice2) {
		if(choice != choice2){
			
			if(choice instanceof Coin || choice2 instanceof Coin){	
				this.card.getPlayer().getResourceChest().getCoins().add(2);
				} else if(choice instanceof Stone || choice instanceof Wood || choice2 instanceof Stone || choice2 instanceof Wood){
					this.card.getPlayer().getResourceChest().getStones().add(1);
					this.card.getPlayer().getResourceChest().getWoods().add(1);
					}else if(choice instanceof FaithPoint || choice2 instanceof FaithPoint){
						this.card.getPlayer().getResourceChest().getFaithPoint().add(1);
						}else if(choice instanceof Servant || choice2 instanceof Servant){
							this.card.getPlayer().getResourceChest().getServants().add(2);
							}else if(choice instanceof MilitaryPoint || choice2 instanceof MilitaryPoint){
								this.card.getPlayer().getResourceChest().getMilitaryPoint().add(2);
								}
		}
	}

	@Override
	public void applyEffect() {
		
		Random random = new Random();
		
		int choice = random.nextInt(4);
		
		if(choice == 0){	
		this.card.getPlayer().getResourceChest().getCoins().add(2);
		} else if(choice == 1){
				this.card.getPlayer().getResourceChest().getStones().add(1);
				this.card.getPlayer().getResourceChest().getWoods().add(1);
			}else if(choice == 2){
				this.card.getPlayer().getResourceChest().getFaithPoint().add(1);
				}else if(choice == 3){
					this.card.getPlayer().getResourceChest().getServants().add(2);
					}else if(choice == 4){
						this.card.getPlayer().getResourceChest().getMilitaryPoint().add(2);
						}
		
		
		
	}

}
