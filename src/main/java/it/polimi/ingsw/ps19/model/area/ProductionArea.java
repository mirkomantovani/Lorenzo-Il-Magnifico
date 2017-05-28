package it.polimi.ingsw.ps19.model.area;

import java.util.ArrayList;

import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.model.card.CardType;
import it.polimi.ingsw.ps19.model.card.DevelopmentCard;
import it.polimi.ingsw.ps19.model.effect.ProductionBonusEffect;

/**
 * @author Jimmy
 *
 */
public class ProductionArea extends IndustrialArea{
	
	public ProductionArea(){
		super();
		//The "MALUS" costant is defined in IndustrialArea
		this.multipleSlot = new SingleActionSpace(SLOT_COST, new ProductionBonusEffect(MALUS));
	}

	@Override
	public ArrayList<? extends DevelopmentCard> getPlayerCards(Player player) {
		return player.getRightArrayList(CardType.BUILDING);
	}
	
}
