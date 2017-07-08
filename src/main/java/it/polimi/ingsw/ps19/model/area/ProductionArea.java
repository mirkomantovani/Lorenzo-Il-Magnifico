package it.polimi.ingsw.ps19.model.area;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.model.card.CardType;
import it.polimi.ingsw.ps19.model.card.DevelopmentCard;
import it.polimi.ingsw.ps19.model.effect.ProductionBonusEffect;

/**
 * The Class ProductionArea.
 *
 * @author Jimmy
 */
public class ProductionArea extends IndustrialArea{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3586015416509643408L;

	/**
	 * Instantiates a new production area.
	 */
	public ProductionArea(){
		super();
		//The "MALUS" costant is defined in IndustrialArea
		this.multipleSlot = new MultipleActionSpace(SLOT_COST, new ProductionBonusEffect(MALUS));
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.area.IndustrialArea#getPlayerCards(it.polimi.ingsw.ps19.Player)
	 */
	@Override
	public List<DevelopmentCard> getPlayerCards(Player player) {
		return player.getDeckOfType(CardType.BUILDING);
	}
	
	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.area.IndustrialArea#toString()
	 */
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("\n°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°The Harvest Area°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°\n\n");
		builder.append(super.toString());
		builder.append("\n°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°\n\n");

		
		return builder.toString();
	}
	
}
