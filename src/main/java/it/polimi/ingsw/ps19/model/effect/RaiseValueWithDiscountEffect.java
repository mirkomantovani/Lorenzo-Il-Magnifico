package it.polimi.ingsw.ps19.model.effect;

import it.polimi.ingsw.ps19.model.Player;
import it.polimi.ingsw.ps19.model.card.CardType;

/**
 * The Class RaiseValueWithDiscountEffect.
 *
 * @author Jimmy
 */
public class RaiseValueWithDiscountEffect extends Effect{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6683191125876080358L;
	
	/** The raise amount. */
	private int raiseAmount;
	
	/** The card type. */
	private CardType cardType;
	
	/** The building cards bonus. */
	private boolean buildingCardsBonus;
	
	/** The character cards bonus. */
	private boolean characterCardsBonus;
	
	
	/**
	 * Instantiates a new raise value with discount effect.
	 *
	 * @param raiseAmount the raise amount
	 * @param cardType the card type
	 * @param buildingCardsBonus the building cards bonus
	 * @param characterCardsBonus the character cards bonus
	 */
	public RaiseValueWithDiscountEffect(int raiseAmount, CardType cardType, boolean buildingCardsBonus, boolean characterCardsBonus){
		this.raiseAmount = raiseAmount;
		this.cardType = cardType;
		this.buildingCardsBonus = buildingCardsBonus;
		this.characterCardsBonus = characterCardsBonus;
	}


	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.effect.Effect#applyEffect(it.polimi.ingsw.ps19.Player)
	 */
	public void applyEffect(Player p) {
				p.getBonuses().addCardTypeActionVariation(cardType, raiseAmount);
		
				
				if(buildingCardsBonus == true)
					p.getBonuses().setBuildingCardsDiscount(true);
				else if(characterCardsBonus == true)
					p.getBonuses().setCharacterCardsDiscount(true);
			    //Non è possibile che entrambi siano a true, ma è possibile che entrambi siano a false
				//In tal caso il metodo termina senza fare nulla
			
			}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		if(raiseAmount >= 0)
			string.append("You get a + " + raiseAmount + " value for a " + cardType.toString().toLowerCase() + " card");
		else
			string.append("You get a " + raiseAmount + " value for a " + cardType.toString().toLowerCase() + " card");
		
		if(buildingCardsBonus == true)
			string.append(" and you receive one wood and one stone discount on its cost");
		else if(characterCardsBonus == true)
			string.append(" and you receive one coin discount on its costs");

		return string.toString();
	}
		
		
		
		
	
	
	
}
