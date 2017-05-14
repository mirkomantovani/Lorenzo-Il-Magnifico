package it.polimi.ingsw.ps19.model.effect;

import it.polimi.ingsw.ps19.Coin;
import it.polimi.ingsw.ps19.FaithPoint;
import it.polimi.ingsw.ps19.MilitaryPoint;
import it.polimi.ingsw.ps19.Resource;
import it.polimi.ingsw.ps19.ResourceChest;
import it.polimi.ingsw.ps19.Servant;
import it.polimi.ingsw.ps19.Stone;
import it.polimi.ingsw.ps19.Wood;


/**
 * @author matteo
 *
 */
public class CouncilPrivilegeEffect extends Effect {


	@Override
	public void applyEffect(ResourceChest chest, Resource choose) {
		if(choose instanceof Coin){	
		chest.getCoins().add(2);
		} else if(choose instanceof Stone || choose instanceof Wood){
				chest.getStones().add(1);
				chest.getWoods().add(1);
			}else if(choose instanceof FaithPoint){
				chest.getFaithPoint().add(1);
				}else if(choose instanceof Servant){
					chest.getServants().add(2);
					}else if(choose instanceof MilitaryPoint){
						chest.getMilitaryPoint().add(2);
						}
		
	}
	
	
	
	
//costruttore privato, tanto la pergamena ha quei valori fissi, poi io farei un metodo public che sulla base della scelta 
// aggiunge una o l'altra cosa

}
