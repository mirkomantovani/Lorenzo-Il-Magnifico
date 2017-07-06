package it.polimi.ingsw.ps19.model.area;

import java.util.List;

import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.model.card.CardType;
import it.polimi.ingsw.ps19.model.card.DevelopmentCard;
import it.polimi.ingsw.ps19.model.effect.Effect;
import it.polimi.ingsw.ps19.model.effect.HarvestBonusEffect;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;

/**
 * The Class HarvestArea.
 *
 * @author Jimmy
 */
public class HarvestArea extends IndustrialArea{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 813593501545744521L;
	
	/** The personal effect. */
	Effect personalEffect;
	
	/**
	 * Instantiates a new harvest area.
	 */
	public HarvestArea(){
		super();
		//The "MALUS" constant is defined in IndustrialArea
		this.multipleSlot = new MultipleActionSpace(SLOT_COST, new HarvestBonusEffect(MALUS));
		
		
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.area.IndustrialArea#getPlayerCards(it.polimi.ingsw.ps19.Player)
	 */
	@Override
	public List<DevelopmentCard> getPlayerCards(Player player) {
			
		return player.getDeckOfType(CardType.TERRITORY);
	}
	
	
	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.area.IndustrialArea#toString()
	 */
	public String toString(){
		return "-------------------- The harvest area --------------------" +
				super.toString();
	}

}
